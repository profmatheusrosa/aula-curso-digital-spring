package tech.ada.java.json;

// Importações para trabalhar com JSON
import java.io.IOException;                        // Exceção para erros de entrada/saída (I/O)
import java.net.URL;                              // Classe para representar URLs (endereços web)
import java.util.Scanner;                         // Classe para ler dados de entrada

// Importações do Jackson - Biblioteca para serialização/desserialização de JSON
import com.fasterxml.jackson.databind.ObjectMapper;  // Mapeador de objetos Jackson
import com.fasterxml.jackson.databind.JsonNode;     // Classe para representar estruturas JSON

// Importações de classes do projeto
import tech.ada.java.Post;                           // Classe entidade Post

// Importações do HttpClient - Cliente HTTP moderno (Java 11+)
import java.net.http.HttpClient;                     // Cliente HTTP para fazer requisições
import java.net.http.HttpRequest;                    // Requisição HTTP configurável
import java.net.http.HttpResponse;                   // Resposta HTTP do servidor
import java.net.URI;                                 // URI para endereços web (mais moderno que URL)

/**
 * Classe de demonstração para leitura de dados JSON de uma API REST.
 *
 * Esta classe é um exemplo prático de como:
 * 1. Conectar a uma URL remota
 * 2. Ler dados JSON da resposta
 * 3. Processar e exibir os dados
 *
 * Neste exemplo, conectamos à API pública JSONPlaceholder que fornece dados fictícios para testes.
 */
public class JsonReaderDemo {

    /**
     * Método principal (ponto de entrada) da aplicação.
     *
     * @param args argumentos de linha de comando (não utilizados neste exemplo)
     */
    public static void main(String[] args) {
        // Chama o método para fazer uma leitura de dados JSON usando Scanner
        //lerComScanner();

        // Chama o método para fazer uma leitura de dados JSON usando HttpClient (mais moderno)
        lerComHttpClient();
    }

    /**
     * Método que demonstra como ler dados JSON de uma API REST usando Scanner
     * e converter para um objeto Post usando Jackson.
     *
     * Funcionamento:
     * 1. Cria uma URL que aponta para uma API pública (dummyjson.com)
     * 2. Abre uma conexão com a URL usando Scanner
     * 3. Lê todos os dados JSON como uma string única
     * 4. Converte a string JSON para um objeto Post usando Jackson
     * 5. Exibe o JSON bruto e o objeto Post convertido no console
     * 6. Captura exceções de I/O (erro de conexão, timeout, etc)
     *
     * A URL utilizada é: https://dummyjson.com/posts/1
     * Esta API pública retorna um objeto JSON com informações sobre uma postagem
     *
     * Exemplo de resposta JSON da API:
     * {
     *   "id": 1,
     *   "title": "His mother had always taught him...",
     *   "body": "His mother had always taught him...",
     *   "userId": 5,
     *   "tags": ["history", "french"],
     *   "reactions": {"likes": 192, "dislikes": 25}
     * }
     */
    private static void lerComScanner() {
        // Try-catch para capturar exceções de I/O (erros de conexão, leitura, etc)
        try (
            // Cria um Scanner que lê dados da URL especificada
            // openStream() retorna um InputStream que o Scanner pode ler
            // new URL cria objeto URL apontando para a API pública
            Scanner scanner = new Scanner(new URL("https://dummyjson.com/posts/1").openStream())
        ) {
            // Metodo useDelimiter altera o delimitador padrão (espaço em branco)
            // para "\\Z" que significa "fim do arquivo"
            // Assim ele lê tudo de uma vez como uma única string contínua
            String json = scanner.nextLine();

            // Converte a string JSON para um objeto Post usando Jackson
            // Esta é a conversão JSON → Objeto Java (Desserialização)
            Post post = convertJsonToPost(json);

            // Exibe o JSON bruto (como recebido da API)
            System.out.println(json);

            // Exibe um quebra-linha para separar a saída
            System.out.println();

            // Exibe o objeto Post convertido com seus atributos
            System.out.println(post);

            // Catch para capturar erros de I/O (IOException)
            // Erros possíveis:
            // - IOException: conexão recusada, URL inválida, timeout, sem internet
            // - JsonMappingException: erro ao fazer parsing do JSON
        } catch (IOException ex) {
            // Exibe a mensagem de erro
            // System.err escreve em vermelho no console indicando erro
            System.err.println(ex.getMessage());
        }
    }

    // ...existing code...

    /**
     * Método que demonstra como ler dados JSON de uma API REST usando HttpClient moderno.
     *
     * HttpClient é a forma RECOMENDADA desde Java 11 para fazer requisições HTTP.
     * É mais poderosa e flexível que Scanner com URL.openStream().
     *
     * Funcionamento:
     * 1. Cria um HttpClient (cliente HTTP reutilizável)
     * 2. Cria uma HttpRequest com a URL desejada
     * 3. Envia a requisição de forma assíncrona
     * 4. Recebe a resposta e extrai o corpo (body) como string
     * 5. Converte a string JSON para um objeto Post usando Jackson
     * 6. Exibe o JSON bruto e o objeto Post convertido
     * 7. Trata exceções que podem ocorrer
     *
     * A URL utilizada é: https://dummyjson.com/posts/1
     * Esta API pública retorna um objeto JSON com informações sobre uma postagem
     *
     * Vantagens do HttpClient sobre Scanner:
     * ✓ API moderna e intuitiva
     * ✓ Suporta HTTP/2 (mais rápido que HTTP/1.1)
     * ✓ Requisições assíncronas (não bloqueia a thread)
     * ✓ Configuração flexível de headers, timeouts, redirects
     * ✓ Melhor para aplicações com muitas requisições
     *
     * Exemplo de resposta JSON da API:
     * {
     *   "id": 1,
     *   "title": "His mother had always taught him...",
     *   "body": "His mother had always taught him...",
     *   "userId": 5,
     *   "tags": ["history", "french"],
     *   "reactions": {"likes": 192, "dislikes": 25}
     * }
     */
    private static void lerComHttpClient() {
        // Try-catch para capturar exceções de I/O e interrupção
        try {
            // Cria um cliente HTTP que pode ser reutilizado
            // newBuilder() cria um novo construtor para customizar o cliente
            // build() finaliza a construção e retorna a instância
            HttpClient client = HttpClient.newBuilder().build();

            // Cria uma requisição HTTP GET
            // newBuilder() cria um construtor configurável
            HttpRequest request = HttpRequest.newBuilder()
                // Define a URI (Uniform Resource Identifier) - endereço do recurso
                // URI.create() cria a URI a partir de uma string
                .uri(URI.create("https://dummyjson.com/posts/1"))
                // build() finaliza a construção da requisição
                .build();

            // Envia a requisição de forma assíncrona e aguarda a resposta
            // sendAsync() é assíncrono mas aqui usamos .join() para esperar
            // BodyHandlers.ofString() converte o corpo da resposta em String
            // thenApply() transforma a resposta usando a função lambda
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                // thenApply() é executado quando a resposta chega
                // stringHttpResponse recebe a resposta HTTP com body como String
                .thenApply(stringHttpResponse -> {
                    // Extrai o corpo (body) da resposta HTTP
                    // body() retorna a string JSON que recebemos da API
                    String json = stringHttpResponse.body();

                    // Converte JSON para objeto Post
                    try {
                        Post post = convertJsonToPost(json);

                        // Exibe o JSON bruto
                        System.out.println(json);

                        // Exibe quebra-linha
                        System.out.println();

                        // Exibe o objeto Post convertido
                        System.out.println(post);
                    } catch (IOException ex) {
                        System.err.println("Erro ao converter JSON: " + ex.getMessage());
                    }

                    // Retorna null (não usamos o retorno)
                    return null;
                })
                // thenAccept() executa a ação final (não retorna nada)
                .thenAccept(v -> System.out.println())
                // join() aguarda a conclusão de toda a cadeia assíncrona
                // Bloqueia a thread até que a requisição e processamento terminem
                .join();

        } catch (Exception ex) {
            // Captura qualquer exceção que possa ocorrer
            // Inclui: InterruptedException, IOException, etc
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Método que converte uma string JSON para um objeto Post usando Jackson.
     *
     * Jackson é uma biblioteca poderosa para serialização/desserialização de JSON.
     * Este método realiza a conversão inversa: JSON → Objeto Java
     *
     * Etapas do processo:
     * 1. Cria um ObjectMapper do Jackson
     * 2. Chama readValue() passando a string JSON e a classe Post
     * 3. Jackson mapeia automaticamente os campos JSON para os atributos da classe
     * 4. Retorna um objeto Post preenchido com os dados do JSON
     *
     * @param json String contendo um objeto JSON válido
     * @return Objeto Post preenchido com os dados do JSON
     *
     * @throws IOException Se houver erro ao fazer parsing do JSON
     * @throws com.fasterxml.jackson.databind.JsonMappingException Se houver erro ao mapear JSON para Post
     *
     * Exemplo:
     * String json = "{\"id\":1,\"title\":\"Título\",\"body\":\"Corpo\"}";
     * Post post = convertJsonToPost(json);  // Post(id=1, title="Título", body="Corpo")
     */
    // Método que realiza a conversão de JSON para objeto Post usando Jackson
    private static Post convertJsonToPost(String json) throws IOException {
        // Cria uma instância de ObjectMapper do Jackson
        // ObjectMapper é a classe principal para serialização/desserialização
        // Ele lida com toda a conversão entre JSON e objetos Java
        ObjectMapper mapper = new ObjectMapper();

        // Converte a string JSON para um objeto Post
        // readValue() faz a desserialização
        // Parâmetros:
        // 1. json: String contendo o JSON a ser convertido
        // 2. Post.class: Classe destino da conversão
        // Jackson usa reflection para mapear os campos JSON aos atributos da classe
        // Os nomes dos campos JSON devem corresponder aos atributos da classe Post
        return mapper.readValue(json, Post.class);
    }

}

