package tech.ada.java;

// Importações da biblioteca Lombok - biblioteca que gera código boilerplate automaticamente
import lombok.AllArgsConstructor; // Gera construtor com todos os atributos como parâmetros
import lombok.Data;               // Gera getters, setters, toString, equals e hashCode
import lombok.NoArgsConstructor;  // Gera construtor vazio (sem parâmetros)

/**
 * Classe que representa uma postagem na plataforma de redes sociais.
 *
 * Esta classe é semelhante a Postagem, mas com mais atributos relacionados a interações
 * e métricas de engajamento (curtidas, comentários, visualizações, tags, reações).
 *
 * Utiliza anotações do Lombok para gerar automaticamente métodos comuns
 * como getters, setters, construtores e toString.
 */
@Data                        // Anotação Lombok que gera: getters, setters, toString, equals e hashCode
@NoArgsConstructor           // Anotação Lombok que gera um construtor vazio: Post()
@AllArgsConstructor          // Anotação Lombok que gera um construtor com todos os parâmetros
public class Post {

    // Atributo: Identificador único da postagem (Long)
    // Tipo Long é usado quando o ID é gerado pelo banco de dados (auto-increment)
    // Diferente de UUID que é gerado no aplicativo
    private Long id;

    // Atributo: Título da postagem
    // Breve resumo ou assunto do conteúdo
    // Exemplo: "Meu Fim de Semana Incrível"
    private String title;

    // Atributo: Corpo ou conteúdo principal da postagem
    // Texto completo e detalhado com todo o conteúdo da postagem
    // Pode conter múltiplos parágrafos e formatações
    private String body;

    // Atributo: Identificador do usuário que criou a postagem
    // Referência ao ID do usuário no banco de dados
    // Usado para relacionar a postagem a um usuário específico
    private Long userId;

    // Atributo: Número de visualizações da postagem
    // Contador de quantas vezes a postagem foi visualizada
    // Começa em 0 e aumenta a cada visualização
    // Exemplo: 1500 visualizações
    private Long views;

    // Atributo: Array de strings contendo as tags/etiquetas da postagem
    // Tags ajudam na categorização e busca de postagens
    // Exemplo: ["java", "spring", "backend"]
    private String[] tags;

    // Atributo: Objeto de reação que contém métricas de engajamento
    // Relacionamento: Uma postagem pode ter várias reações (curtidas, deslike, etc)
    // O objeto Reaction armazena contadores de cada tipo de reação
    private Reaction reactions;

    /**
     * Método que converte o objeto Post em uma representação textual legível.
     *
     * @return String contendo todos os atributos da postagem formatados
     *
     * Exemplo de retorno:
     * Post{id=1, title='Meu Primeiro Post', body='Conteúdo...',
     *      userId=5, views=150, tags=[java, spring], reactions=Reaction{...}}
     */
    @Override // Anotação que indica que este método sobrescreve um método da classe pai
    public String toString() {
        // Constrói uma string formatada com todos os atributos da postagem
        return "Post{" +
                "id=" + id +                                // Adiciona o ID
                ", title='" + title + '\'' +                // Adiciona o título entre aspas
                ", body='" + body + '\'' +                  // Adiciona o corpo entre aspas
                ", userId=" + userId +                      // Adiciona o ID do usuário
                ", views=" + views +                        // Adiciona o número de visualizações
                ", tags=" + java.util.Arrays.toString(tags) +  // Converte array para string
                ", reactions=" + reactions +                // Adiciona o objeto de reações
                '}';                                        // Fecha a chave
    }

}

