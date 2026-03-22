package tech.ada.java.cursospring.api.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private UUID uuid;
    @NotBlank
    private String nome;
    @Email
    private String email;
    @Past
    private LocalDate dob;

    public Usuario(UUID uuid, String nome, String email, LocalDate dob) {
        this.uuid = uuid;
        this.nome = nome;
        this.email = email;
        this.dob = dob;
    }

}
