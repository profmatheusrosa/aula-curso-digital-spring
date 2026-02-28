# 🚀 GUIA RÁPIDO - PROJETO SPRING

## 📦 Estrutura Resumida

```
src/main/java/tech/ada/java/
├── Usuario.java
├── Postagem.java
├── Post.java
├── Reaction.java
└── json/
    └── JsonReaderDemo.java
```

## 🎯 Classes Principais

### Usuario
- Entidade de usuário
- Atributos: uuid, nome, email, dob
- Construtor: vazio e com parâmetros (Lombok)

### Postagem
- Entidade de postagem
- Atributos: uuid, titulo, corpo, dataCriacao, autor
- Construtores: 3 customizados

### Post
- Entidade de postagem em rede social
- Atributos: id, title, body, userId, views, tags[], reactions
- Relacionamento: uma postagem com uma reação

### Reaction
- Métricas de engajamento
- Atributos: likes, dislikes

### JsonReaderDemo
- Demonstração de leitura JSON
- Lê de: https://dummyjson.com/posts/1

## 🛠️ Comandos Maven

```bash
mvn clean compile          # Compilar
mvn test                   # Testes
mvn package                # Empacotar JAR
mvn spring-boot:run        # Executar
java -jar target/*.jar     # Rodar JAR
```

## 📝 Anotações Lombok Usadas

```
@Data                   → getters, setters, toString, equals, hashCode
@NoArgsConstructor      → construtor vazio
@AllArgsConstructor     → construtor com todos os parâmetros
```

## 💻 Exemplo Rápido

```java
// Criar usuário
Usuario user = new Usuario(
    UUID.randomUUID(), "João", "joao@example.com", 
    LocalDate.of(1990, 5, 15)
);

// Criar postagem
Postagem post = new Postagem("Título", "Corpo", user);

// Criar reação
Reaction reaction = new Reaction(1500L, 50L);

// Criar post com reação
Post socialPost = new Post(
    1L, "Title", "Body", 5L, 1000L,
    new String[]{"java", "spring"},
    reaction
);

// Exibir
System.out.println(user);
System.out.println(post);
System.out.println(socialPost);
```

## 🎓 Propriedades Maven

```
java.version = 21
encoding = UTF-8
maven.compiler.source = 21
maven.compiler.target = 21
```

## 📚 Dependências

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- h2 (banco embarcado)
- lombok
- spring-boot-starter-test

## ✨ Destaques

✅ 5 classes comentadas
✅ POM documentado
✅ Pronto para produção
✅ Exemplos práticos
✅ Estrutura profissional


