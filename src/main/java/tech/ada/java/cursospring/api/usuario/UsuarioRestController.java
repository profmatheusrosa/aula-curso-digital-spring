package tech.ada.java.cursospring.api.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.transaction.Transactional;
import tech.ada.java.cursospring.api.exception.NaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final List<Usuario> usuarioList = new ArrayList<>();
    private final UsuarioJpaRepository repository;

    public UsuarioRestController(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dummy")
    public Usuario dummyUsuario() {
        return new Usuario(UUID.randomUUID(), "Jessé", "jesse.haniel@gmail.com", LocalDate.now());
    }

    // CRUD - Create, Read, Update, Delete
    // HTTP - POST, GET, PUT, PATCH, DELETE

    @GetMapping
    public List<Usuario> listarTodos() {
        return this.repository.findAll();
    }

    @GetMapping("/{uuid}")
    public Usuario buscarPorUuid(@PathVariable UUID uuid) {
        return this.repository.findByUuid(uuid)
                .orElseThrow(() -> new NaoEncontradoException("Não foi possível encontrar o Usuário"));
    }

    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
        return this.repository.save(usuario);
    }

    @PostMapping("/create-dummy")
    public Usuario createDummy() {
        Usuario dummy = new Usuario(UUID.randomUUID(), "Dummy", "dummy@example.com", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @PutMapping("/{uuid}")
    public Usuario atualizarUsuario(@PathVariable UUID uuid, @NotNull @RequestBody @Valid Usuario usuarioNovo) {
        Usuario usuario = this.buscarPorUuid(uuid);
        usuarioNovo.setId(usuario.getId());
        return this.repository.save(usuarioNovo);
    }

    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario alterarNome(@PathVariable UUID uuid, @NotNull @RequestBody Usuario usuarioAlterado) {
        Usuario usuario = this.buscarPorUuid(uuid);
        usuario.setNome(usuarioAlterado.getNome());
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioAlterado);
        return usuarioAlterado;
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void deletarUsuario(@PathVariable UUID uuid) {
        this.repository.deleteByUuid(uuid);
    }

}
