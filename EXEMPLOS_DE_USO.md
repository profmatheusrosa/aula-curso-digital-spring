# 💻 EXEMPLOS DE USO

## 1. Usando a Classe Usuario

### Exemplo 1: Criar usuário simples
```java
Usuario usuario = new Usuario(
    UUID.randomUUID(),
    "João Silva",
    "joao@example.com",
    LocalDate.of(1990, 5, 15)
);

System.out.println(usuario.getNome());    // "João Silva"
System.out.println(usuario.getEmail());   // "joao@example.com"
System.out.println(usuario);
// Saída: Usuario{uuid=..., nome='João Silva', email='joao@example.com', dob=1990-05-15}
```

### Exemplo 2: Comparar usuários
```java
Usuario user1 = new Usuario(
    UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
    "João",
    "joao@example.com",
    LocalDate.of(1990, 5, 15)
);

Usuario user2 = new Usuario(
    UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
    "João",
    "joao@example.com",
    LocalDate.of(1990, 5, 15)
);

if (user1.equals(user2)) {
    System.out.println("Usuários são iguais!");
}
```

---

## 2. Usando a Classe Postagem

### Exemplo 1: Criar postagem simples
```java
Postagem post = new Postagem(
    "Aprendendo Spring Boot",
    "Spring Boot torna muito fácil criar aplicações!"
);

System.out.println("UUID: " + post.getUuid());
System.out.println("Data: " + post.getDataCriacao());
System.out.println("Autor: " + post.getAutor());  // null
```

### Exemplo 2: Criar postagem com autor
```java
Usuario autor = new Usuario(
    UUID.randomUUID(),
    "Maria Silva",
    "maria@example.com",
    LocalDate.of(1995, 3, 20)
);

Postagem post = new Postagem(
    "Meu Primeiro Post",
    "Este é meu primeiro post!",
    autor
);

System.out.println("Autor: " + post.getAutor().getNome());  // "Maria Silva"
```

### Exemplo 3: Modificar postagem
```java
Postagem post = new Postagem("Título Antigo", "Corpo antigo");

post.setTitulo("Título Novo");
post.setCorpo("Novo conteúdo!");

System.out.println(post.getTitulo());  // "Título Novo"
```

---

## 3. Usando a Classe Post

### Exemplo 1: Criar post simples
```java
Post post = new Post(
    1L,
    "Título da Postagem",
    "Corpo da postagem com conteúdo...",
    5L,
    1000L,
    new String[]{"java", "spring", "backend"},
    null
);

System.out.println("ID: " + post.getId());
System.out.println("Título: " + post.getTitle());
System.out.println("Visualizações: " + post.getViews());
System.out.println("Tags: " + java.util.Arrays.toString(post.getTags()));
```

### Exemplo 2: Post com reações
```java
Reaction reaction = new Reaction(1500L, 50L);

Post post = new Post(
    1L,
    "Título",
    "Conteúdo",
    5L,
    1000L,
    new String[]{"java", "spring"},
    reaction
);

System.out.println("Curtidas: " + post.getReactions().getLikes());      // 1500
System.out.println("Descurtidas: " + post.getReactions().getDislikes()); // 50
```

### Exemplo 3: Modificar post
```java
Post post = new Post();
post.setId(1L);
post.setTitle("Meu Post");
post.setBody("Conteúdo...");
post.setUserId(5L);
post.setViews(0L);
post.setTags(new String[]{"novo"});

System.out.println(post);
```

---

## 4. Usando a Classe Reaction

### Exemplo 1: Criar reação
```java
Reaction reaction = new Reaction(1500L, 50L);

System.out.println("Likes: " + reaction.getLikes());       // 1500
System.out.println("Dislikes: " + reaction.getDislikes()); // 50
System.out.println(reaction);
// Saída: Reaction{likes=1500, dislikes=50}
```

### Exemplo 2: Incrementar contadores
```java
Reaction reaction = new Reaction(100L, 10L);

reaction.setLikes(reaction.getLikes() + 1);      // Aumentar curtidas
reaction.setDislikes(reaction.getDislikes() + 1); // Aumentar descurtidas

System.out.println(reaction.getLikes());    // 101
System.out.println(reaction.getDislikes()); // 11
```

---

## 5. Usando JsonReaderDemo

### Exemplo 1: Executar demonstração
```java
public class Main {
    public static void main(String[] args) {
        // Chama a demonstração de leitura JSON
        JsonReaderDemo.main(args);
    }
}
```

### Exemplo 2: Esperado do console
```
{
  "id": 1,
  "title": "His mother had always taught him...",
  "body": "His mother had always taught him...",
  "userId": 5,
  "tags": ["important", "love"],
  "reactions": 192
}
```

---

## 6. Exemplo Completo - Integração

```java
import java.time.LocalDate;
import java.util.UUID;

public class ExemploIntegrado {
    public static void main(String[] args) {
        
        // ===== CRIAR USUÁRIOS =====
        Usuario usuario1 = new Usuario(
            UUID.randomUUID(),
            "Alice Johnson",
            "alice@example.com",
            LocalDate.of(1992, 7, 15)
        );

        Usuario usuario2 = new Usuario(
            UUID.randomUUID(),
            "Bob Smith",
            "bob@example.com",
            LocalDate.of(1988, 12, 3)
        );

        System.out.println("===== USUÁRIOS CRIADOS =====");
        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println();

        // ===== CRIAR POSTAGENS CLÁSSICAS =====
        Postagem post1 = new Postagem(
            "Introdução ao Java",
            "Java é uma linguagem de programação orientada a objetos.",
            usuario1
        );

        Postagem post2 = new Postagem(
            "Spring Framework",
            "Spring oferece uma forma elegante de construir aplicações.",
            usuario1
        );

        System.out.println("===== POSTAGENS CLÁSSICAS =====");
        System.out.println(post1);
        System.out.println(post2);
        System.out.println();

        // ===== CRIAR POSTS EM REDE SOCIAL =====
        Reaction reaction1 = new Reaction(1500L, 50L);
        Post socialPost1 = new Post(
            1L,
            "Novo Framework",
            "Descobri um novo framework de Java!",
            usuario1.getUuid().toString().hashCode(),
            1000L,
            new String[]{"java", "framework"},
            reaction1
        );

        Reaction reaction2 = new Reaction(2000L, 100L);
        Post socialPost2 = new Post(
            2L,
            "Dica de Programação",
            "Use Lombok para reduzir código boilerplate!",
            usuario2.getUuid().toString().hashCode(),
            1500L,
            new String[]{"lombok", "produtividade"},
            reaction2
        );

        System.out.println("===== POSTS EM REDE SOCIAL =====");
        System.out.println(socialPost1);
        System.out.println(socialPost2);
        System.out.println();

        // ===== ACESSAR INFORMAÇÕES =====
        System.out.println("===== INFORMAÇÕES EXTRAÍDAS =====");
        System.out.println("Post 1 foi criado por: " + post1.getAutor().getNome());
        System.out.println("Email: " + post1.getAutor().getEmail());
        System.out.println("Visualizações Post Social 1: " + socialPost1.getViews());
        System.out.println("Curtidas Post Social 1: " + socialPost1.getReactions().getLikes());
        System.out.println("Descurtidas Post Social 2: " + socialPost2.getReactions().getDislikes());
        System.out.println();

        // ===== LISTAR POR USUÁRIO =====
        System.out.println("===== CONTEÚDO DE: " + usuario1.getNome() + " =====");
        System.out.println("Postagem: " + post1.getTitulo());
        System.out.println("Postagem: " + post2.getTitulo());
        System.out.println("Post Social: " + socialPost1.getTitle());
    }
}
```

---

## 7. Exemplo com Testes (JUnit)

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioTestExample {
    
    @Test
    void deveCriarUsuarioComDados() {
        // ARRANGE
        UUID uuid = UUID.randomUUID();
        String nome = "João Silva";
        
        // ACT
        Usuario usuario = new Usuario(uuid, nome, "joao@example.com", 
                                     LocalDate.of(1990, 5, 15));
        
        // ASSERT
        assertThat(usuario.getNome()).isEqualTo("João Silva");
        assertThat(usuario.getEmail()).isEqualTo("joao@example.com");
        assertThat(usuario.getDob()).isEqualTo(LocalDate.of(1990, 5, 15));
    }
    
    @Test
    void deveGerarUUIDAutomatico() {
        // ACT
        Postagem post = new Postagem("Título", "Corpo", null);
        
        // ASSERT
        assertThat(post.getUuid()).isNotNull();
        assertThat(post.getDataCriacao()).isNotNull();
    }
    
    @Test
    void deveCompararReacoes() {
        // ACT
        Reaction reaction1 = new Reaction(100L, 10L);
        Reaction reaction2 = new Reaction(100L, 10L);
        
        // ASSERT
        assertThat(reaction1).isEqualTo(reaction2);
    }
}
```

---

## Resumo de Funcionalidades

| Funcionalidade | Classe | Resultado |
|---|---|---|
| Criar usuário | Usuario | Objeto com UUID |
| Criar postagem | Postagem | Objeto com data automática |
| Criar post | Post | Objeto com métricas |
| Criar reação | Reaction | Objeto com likes/dislikes |
| Comparar objetos | Any | true/false |
| Exibir objeto | Any | String formatada |
| Acessar dados | Any | Getters funcionam |


