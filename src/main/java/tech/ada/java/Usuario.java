package tech.ada.java;

// Importações da biblioteca Lombok - biblioteca que gera código boilerplate automaticamente
import lombok.AllArgsConstructor; // Gera construtor com todos os atributos como parâmetros
import lombok.Data;               // Gera getters, setters, toString, equals e hashCode
import lombok.NoArgsConstructor;  // Gera construtor vazio (sem parâmetros)

// Importações da biblioteca padrão do Java
import java.time.LocalDate; // Classe para trabalhar com datas (ano, mês, dia)
import java.util.UUID;      // Classe para gerar identificadores únicos universais

/**
 * Classe que representa um usuário no sistema.
 *
 * Utiliza anotações do Lombok para gerar automaticamente métodos comuns
 * como getters, setters, construtores e toString.
 */
@Data                        // Anotação Lombok que gera: getters, setters, toString, equals e hashCode
@NoArgsConstructor           // Anotação Lombok que gera um construtor vazio: Usuario()
@AllArgsConstructor          // Anotação Lombok que gera um construtor com todos os parâmetros
public class Usuario {

    // Atributo: Identificador único do usuário (UUID - Universal Unique Identifier)
    // UUID é um identificador único que evita conflitos em sistemas distribuídos
    private UUID uuid;

    // Atributo: Nome completo do usuário
    // Pode conter o primeiro nome, nome do meio e sobrenome
    private String nome;

    // Atributo: Endereço de email do usuário
    // Deve ser único para cada usuário e usado para comunicação
    private String email;

    // Atributo: Data de nascimento do usuário (date of birth)
    // Utiliza LocalDate que armazena apenas a data (sem hora)
    // Formato: YYYY-MM-DD
    private LocalDate dob;

    /**
     * Método que converte o objeto Usuario em uma representação textual legível.
     *
     * @return String contendo todos os atributos do usuário formatados
     *
     * Exemplo de retorno:
     * Usuario{uuid=550e8400-e29b-41d4-a716-446655440000, nome='João Silva',
     *         email='joao@example.com', dob=1990-05-15}
     */
    @Override // Anotação que indica que este método sobrescreve um método da classe pai
    public String toString() {
        // Constrói uma string formatada com todos os atributos do usuário
        return "Usuario{" +
                "uuid=" + uuid +                           // Adiciona o UUID
                ", nome='" + nome + '\'' +                 // Adiciona o nome entre aspas simples
                ", email='" + email + '\'' +               // Adiciona o email entre aspas simples
                ", dob=" + dob +                           // Adiciona a data de nascimento
                '}';                                       // Fecha a chave
    }

}

