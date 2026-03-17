package tech.ada.java.cursospring.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final List<Usuario> usuarioList = new ArrayList<>();

    @GetMapping("/dummy")
    public Usuario dummyUsuario() {
        return new Usuario(UUID.randomUUID(), "Jessé", "jesse.haniel@gmail.com", LocalDate.now());
    }

    // CRUD - Create, Read, Update, Delete
    // HTTP - POST, GET, PUT, PATCH, DELETE

    @GetMapping
    public List<Usuario> listarTodos() {
        return this.usuarioList;
    }

    @GetMapping("/{uuid}")
    public Usuario buscarPorUuid(@PathVariable UUID uuid) {
        return usuarioList.stream()
                .filter(usuario -> usuario.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }

    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        this.usuarioList.add(usuario);
        return usuario;
    }

    @PostMapping("/create-dummy")
    public Usuario createDummy() {
        Usuario dummy = new Usuario(UUID.randomUUID(), "Dummy", "dummy@example.com", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuarioNovo) {
        Usuario usuario = this.buscarPorUuid(id);
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioNovo);
        return usuarioNovo;
    }

    @PatchMapping("/{uuid}")
    public Usuario alterarNome(@PathVariable UUID uuid, @NotNull @RequestBody Usuario usuarioAlterado) {
        Usuario usuario = this.buscarPorUuid(uuid);
        usuario.setNome(usuarioAlterado.getNome());
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioAlterado);
        return usuarioAlterado;
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        this.usuarioList.removeIf(usuario -> usuario.getUuid().equals(id));
    }

}
