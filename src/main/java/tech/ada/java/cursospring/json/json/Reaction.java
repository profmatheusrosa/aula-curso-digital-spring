package tech.ada.java.cursospring.json.json;

// Importações da biblioteca Lombok - biblioteca que gera código boilerplate automaticamente
import lombok.AllArgsConstructor; // Gera construtor com todos os atributos como parâmetros
import lombok.Data;               // Gera getters, setters, toString, equals e hashCode
import lombok.NoArgsConstructor;  // Gera construtor vazio (sem parâmetros)

/**
 * Classe que representa as reações de engajamento em uma postagem.
 *
 * Armazena contadores de diferentes tipos de reações que os usuários podem fazer
 * em relação a uma postagem (curtir, não curtir, etc).
 *
 * Cada postagem pode ter um objeto Reaction associado que rastreia o número
 * de usuários que reagiram de forma positiva (likes) ou negativa (dislikes).
 *
 * Utiliza anotações do Lombok para gerar automaticamente métodos comuns
 * como getters, setters, construtores e toString.
 */
@Data                        // Anotação Lombok que gera: getters, setters, toString, equals e hashCode
@NoArgsConstructor           // Anotação Lombok que gera um construtor vazio: Reaction()
@AllArgsConstructor          // Anotação Lombok que gera um construtor com todos os parâmetros
public class Reaction {

    // Atributo: Número de curtidas (likes) da postagem
    // Armazena quantos usuários clicaram no botão "gostei" da postagem
    // Número inteiro que começa em 0 e aumenta conforme usuários curtem
    // Exemplo: 1500 usuários curtiram esta postagem
    private Long likes;

    // Atributo: Número de não-curtidas (dislikes) da postagem
    // Armazena quantos usuários clicaram no botão "não gostei" da postagem
    // Número inteiro que começa em 0 e aumenta conforme usuários descurtam
    // Exemplo: 50 usuários não gostaram desta postagem
    // Nota: Nem todas as plataformas implementam dislikes (YouTube removeu em 2021)
    private Long dislikes;

    /**
     * Método que converte o objeto Reaction em uma representação textual legível.
     *
     * @return String contendo todos os atributos da reação formatados
     *
     * Exemplo de retorno:
     * Reaction{likes=1500, dislikes=50}
     *
     * Este método simples mostra a contagem de reações de forma clara
     * e é útil para debugging e logging
     */
    @Override // Anotação que indica que este método sobrescreve um método da classe pai
    public String toString() {
        // Constrói uma string formatada com likes e dislikes
        // Formato: Reaction{likes=X, dislikes=Y}
        return "Reaction{" +
                "likes=" + likes +           // Adiciona o número de curtidas
                ", dislikes=" + dislikes +   // Adiciona o número de não-curtidas
                '}';                         // Fecha a chave
    }

}

