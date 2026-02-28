# 📐 Relação Entre Classes

## Diagrama de Relacionamentos

```
┌─────────────────────────────────────┐
│            USUARIO                  │
├─────────────────────────────────────┤
│ - uuid: UUID                        │
│ - nome: String                      │
│ - email: String                     │
│ - dob: LocalDate                    │
└─────────────────────────────────────┘
           ▲
           │
           │ autor (1:N)
           │ Um usuário pode ter
           │ muitas postagens
           │
┌─────────────────────────────────────┐
│           POSTAGEM                  │
├─────────────────────────────────────┤
│ - uuid: UUID                        │
│ - titulo: String                    │
│ - corpo: String                     │
│ - dataCriacao: LocalDateTime        │
│ - autor: Usuario                    │
└─────────────────────────────────────┘


┌─────────────────────────────────────┐
│             POST                    │
├─────────────────────────────────────┤
│ - id: Long                          │
│ - title: String                     │
│ - body: String                      │
│ - userId: Long                      │
│ - views: Long                       │
│ - tags: String[]                    │
│ - reactions: Reaction               │
└─────────────────────────────────────┘
           │
           │ 1:1
           │ Uma postagem tem
           │ uma reação
           ▼
┌─────────────────────────────────────┐
│           REACTION                  │
├─────────────────────────────────────┤
│ - likes: Long                       │
│ - dislikes: Long                    │
└─────────────────────────────────────┘
           
           
┌─────────────────────────────────────┐
│       JSONREADERDEMO                │
├─────────────────────────────────────┤
│ - main(String[])                    │
│ - lerComScanner()                   │
│                                     │
│ Lê dados em formato JSON            │
└─────────────────────────────────────┘
```

## Tipos de Relacionamentos

### 1. Usuario → Postagem (1:N)
- Um usuário pode ter MUITAS postagens
- Uma postagem pertence a UM usuário
- Campo: `autor: Usuario` em Postagem

### 2. Post → Reaction (1:1)
- Um post tem UMA reação
- Uma reação pertence a UM post
- Campo: `reactions: Reaction` em Post

### 3. JsonReaderDemo (Independente)
- Classe utilitária
- Não tem relacionamento com outras entidades
- Propósito: Demonstrar leitura de JSON

## Exemplos de Uso

### Criar Usuário e Postagem
```java
// Criar usuário
Usuario usuario = new Usuario(
    UUID.randomUUID(),
    "Maria Silva",
    "maria@example.com",
    LocalDate.of(1995, 3, 20)
);

// Criar 2 postagens do mesmo usuário
Postagem post1 = new Postagem(
    "Primeiro Post",
    "Conteúdo...",
    usuario
);

Postagem post2 = new Postagem(
    "Segundo Post",
    "Mais conteúdo...",
    usuario
);

// Verificar autor das postagens
System.out.println(post1.getAutor().getNome()); // "Maria Silva"
System.out.println(post2.getAutor().getNome()); // "Maria Silva"
```

### Criar Post com Reação
```java
// Criar reação
Reaction reaction = new Reaction(1500L, 50L);

// Criar post com reação
Post post = new Post(
    1L,
    "Título do Post",
    "Corpo do post...",
    5L,
    1000L,
    new String[]{"java", "spring", "backend"},
    reaction
);

// Acessar métricas
System.out.println(post.getReactions().getLikes());     // 1500
System.out.println(post.getReactions().getDislikes());  // 50
```

## Fluxo de Dados

```
1. Usuário criado
   └─ UUID gerado automaticamente
   
2. Postagem criada
   └─ Referencia um Usuário
   └─ UUID gerado automaticamente
   └─ Data/hora definida automaticamente
   
3. Post criado
   └─ ID fornecido manualmente
   └─ Tags como array de strings
   └─ Referencia uma Reaction
   
4. Reaction criada
   └─ Likes e dislikes como contadores
```

## Comparação: Postagem vs Post

| Aspecto | Postagem | Post |
|---------|----------|------|
| **ID** | UUID | Long (auto-increment) |
| **Autor** | Usuario object | Long (userId) |
| **Data** | LocalDateTime | Implícita |
| **Conteúdo** | corpo | body |
| **Tags** | Não tem | String[] |
| **Engajamento** | Não tem | Reaction object |
| **Casos de Uso** | Blog, Fórum | Rede Social |

## Hierarquia de Pacotes

```
tech.ada.java/
├── Usuario.java
├── Postagem.java
├── Post.java
├── Reaction.java
└── json/
    └── JsonReaderDemo.java
```


