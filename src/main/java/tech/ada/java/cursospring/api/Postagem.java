package tech.ada.java.cursospring.api;

// Importações da biblioteca Lombok - biblioteca que gera código boilerplate automaticamente
import lombok.Data; // Gera getters, setters, toString, equals e hashCode
import lombok.NoArgsConstructor; // Gera construtor vazio (sem parâmetros)

// Importações da biblioteca padrão do Java
import java.time.LocalDateTime; // Classe para trabalhar com data e hora (ano, mês, dia, hora, minuto, segundo)
import java.util.UUID; // Classe para gerar identificadores únicos universais

/**
 * Classe que representa uma postagem no sistema.
 *
 * Uma postagem contém informações sobre um conteúdo criado por um usuário,
 * incluindo título, corpo do texto, data de criação e referência ao autor.
 *
 * Utiliza anotações do Lombok para gerar automaticamente métodos comuns
 * como getters, setters, construtores e toString.
 *
 * NOTA IMPORTANTE: Não usamos @AllArgsConstructor aqui porque temos
 * construtores
 * customizados que fazem inicialização especial (gerar UUID automaticamente,
 * definir data como "agora", etc). Se usássemos @AllArgsConstructor, teríamos
 * duplicação de construtores e erro de compilação.
 */
@Data // Anotação Lombok que gera: getters, setters, toString, equals e hashCode
@NoArgsConstructor // Anotação Lombok que gera um construtor vazio: Postagem()
public class Postagem {

    // Atributo: Identificador único da postagem (UUID - Universal Unique
    // Identifier)
    // UUID é um identificador único que evita conflitos em sistemas distribuídos
    private UUID uuid;

    // Atributo: Título da postagem
    // Breve resumo ou assunto do conteúdo da postagem
    // Exemplo: "Minha primeira experiência com Spring Boot"
    private String titulo;

    // Atributo: Corpo ou conteúdo principal da postagem
    // Texto detalhado com o conteúdo completo da postagem
    // Pode conter múltiplos parágrafos
    private String corpo;

    // Atributo: Data e hora de criação da postagem
    // Utiliza LocalDateTime que armazena data e hora completas
    // Formato: YYYY-MM-DD HH:mm:ss
    // Exemplo: 2026-02-28 15:30:45
    private LocalDateTime dataCriacao;

    // Atributo: Referência ao usuário autor da postagem
    // Cada postagem é criada por um e apenas um usuário
    // Esta é uma relação de associação: muitas postagens podem pertencer a um
    // usuário
    private Usuario autor;

    /**
     * Construtor customizado que cria uma postagem com título, corpo e autor.
     * O UUID é gerado automaticamente e a data de criação é definida como o momento
     * atual.
     *
     * @param titulo o título da postagem
     * @param corpo  o conteúdo principal da postagem
     * @param autor  o usuário autor da postagem
     */
    public Postagem(String titulo, String corpo, Usuario autor) {
        this.uuid = UUID.randomUUID(); // Gera um UUID único automaticamente
        this.titulo = titulo; // Atribui o título fornecido
        this.corpo = corpo; // Atribui o corpo fornecido
        this.dataCriacao = LocalDateTime.now(); // Define a data/hora como agora
        this.autor = autor; // Atribui o autor fornecido
    }

    /**
     * Construtor customizado que cria uma postagem com todos os parâmetros.
     * Permite definir manualmente todos os atributos, incluindo UUID e data de
     * criação.
     *
     * @param uuid        o identificador único da postagem
     * @param titulo      o título da postagem
     * @param corpo       o conteúdo principal da postagem
     * @param dataCriacao a data e hora de criação da postagem
     * @param autor       o usuário autor da postagem
     */
    public Postagem(UUID uuid, String titulo, String corpo, LocalDateTime dataCriacao, Usuario autor) {
        this.uuid = uuid; // Atribui o UUID fornecido
        this.titulo = titulo; // Atribui o título fornecido
        this.corpo = corpo; // Atribui o corpo fornecido
        this.dataCriacao = dataCriacao; // Atribui a data de criação fornecida
        this.autor = autor; // Atribui o autor fornecido
    }

    /**
     * Construtor customizado que cria uma postagem apenas com título e corpo.
     * Útil para criar uma postagem sem autor no momento (pode ser atribuído
     * depois).
     * O UUID é gerado automaticamente e a data de criação é definida como o momento
     * atual.
     *
     * @param titulo o título da postagem
     * @param corpo  o conteúdo principal da postagem
     */
    public Postagem(String titulo, String corpo) {
        this.uuid = UUID.randomUUID(); // Gera um UUID único automaticamente
        this.titulo = titulo; // Atribui o título fornecido
        this.corpo = corpo; // Atribui o corpo fornecido
        this.dataCriacao = LocalDateTime.now(); // Define a data/hora como agora
        this.autor = null; // Inicialmente sem autor
    }

    /**
     * Método que converte o objeto Postagem em uma representação textual legível.
     *
     * @return String contendo todos os atributos da postagem formatados
     *
     *         Exemplo de retorno:
     *         Postagem{uuid=550e8400-e29b-41d4-a716-446655440000, titulo='Meu
     *         Post',
     *         corpo='Conteúdo...', dataCriacao=2026-02-28T15:30:45,
     *         autor=Usuario{...}}
     */
    @Override // Anotação que indica que este método sobrescreve um método da classe pai
    public String toString() {
        // Constrói uma string formatada com todos os atributos da postagem
        return "Postagem{" +
                "uuid=" + uuid + // Adiciona o UUID
                ", titulo='" + titulo + '\'' + // Adiciona o título entre aspas simples
                ", corpo='" + corpo + '\'' + // Adiciona o corpo entre aspas simples
                ", dataCriacao=" + dataCriacao + // Adiciona a data de criação
                ", autor=" + autor + // Adiciona o autor (usa toString de Usuario)
                '}'; // Fecha a chave
    }

}
