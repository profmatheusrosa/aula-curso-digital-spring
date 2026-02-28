# 📚 Projeto Spring Boot - Curso Completo

**Um projeto educativo com 5 classes Java totalmente comentadas e documentadas em português.**

## 🎯 Visão Geral

Este é um projeto Spring Boot profissional que serve como base para aprendizado e desenvolvimento. Contém:

- ✅ 5 classes Java principais (Usuario, Postagem, Post, Reaction, JsonReaderDemo)
- ✅ Configuração Maven completa com 400+ linhas de comentários
- ✅ 95%+ de cobertura de comentários no código
- ✅ 6+ documentos de referência
- ✅ Exemplos práticos de uso

## 📁 Estrutura do Projeto

```
curso-spring/
├── src/
│   ├── main/
│   │   ├── java/tech/ada/java/
│   │   │   ├── Usuario.java          (62 linhas)
│   │   │   ├── Postagem.java         (123 linhas)
│   │   │   ├── Post.java             (62 linhas)
│   │   │   ├── Reaction.java         (54 linhas)
│   │   │   └── json/
│   │   │       └── JsonReaderDemo.java (92 linhas)
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/CursoSpringApplicationTests.java
├── pom.xml                           (400+ linhas comentadas)
├── README.md                         (Este arquivo)
├── DOCUMENTACAO_COMPLETA.md
├── GUIA_RAPIDO.md
├── RELACAO_ENTRE_CLASSES.md
├── EXEMPLOS_DE_USO.md
└── CHANGELOG.md
```

## 🎓 Classes Principais

### Usuario.java
Representa um usuário no sistema.
- **Atributos**: uuid, nome, email, dob (date of birth)
- **Anotações**: @Data, @NoArgsConstructor, @AllArgsConstructor
- **Métodos**: toString() customizado, getters, setters

### Postagem.java
Representa uma postagem tradicional de blog/fórum.
- **Atributos**: uuid, titulo, corpo, dataCriacao, autor (Usuario)
- **Construtores**: 3 customizados
- **Métodos**: toString() customizado, getters, setters

### Post.java
Representa uma postagem em rede social com métricas.
- **Atributos**: id, title, body, userId, views, tags[], reactions
- **Anotações**: @Data, @NoArgsConstructor, @AllArgsConstructor
- **Métodos**: toString() customizado, getters, setters

### Reaction.java
Armazena métricas de engajamento de uma postagem.
- **Atributos**: likes, dislikes
- **Anotações**: @Data, @NoArgsConstructor, @AllArgsConstructor
- **Métodos**: toString() customizado, getters, setters

### JsonReaderDemo.java
Demonstra como ler dados JSON de uma API REST.
- **Métodos**: main(), lerComScanner()
- **Endpoint**: https://dummyjson.com/posts/1
- **Tecnologia**: Scanner, URL connections, try-with-resources

## 🛠️ Configuração Técnica

### Java
- **Versão**: 21 (LTS - Long Term Support)
- **Encoding**: UTF-8
- **Compiler Source**: 21
- **Compiler Target**: 21

### Spring Boot
- **Versão**: 3.2.0
- **Parent**: spring-boot-starter-parent

### Dependências
1. **spring-boot-starter-web** - Web MVC + Tomcat embarcado
2. **spring-boot-starter-data-jpa** - ORM + Hibernate
3. **h2** - Banco de dados embarcado
4. **lombok** - Geração de boilerplate
5. **spring-boot-starter-test** - JUnit + Mockito + AssertJ

### Build
- **Plugin**: spring-boot-maven-plugin
- **Empacotamento**: JAR executável
- **Excludes**: Lombok (não necessário em runtime)

## 🚀 Quick Start

### Pré-requisitos
- Java 21 instalado
- Maven 3.6+ instalado
- IDE (IntelliJ IDEA, VSCode, Eclipse)

### Compilar
```bash
mvn clean compile
```

### Executar Testes
```bash
mvn test
```

### Executar Aplicação
```bash
mvn spring-boot:run
```

### Empacotar JAR
```bash
mvn package
```

### Executar JAR
```bash
java -jar target/curso-spring-1.0-SNAPSHOT.jar
```

## 📚 Documentação

### Arquivos de Referência
1. **README.md** (este arquivo) - Visão geral
2. **DOCUMENTACAO_COMPLETA.md** - Documentação extensiva
3. **GUIA_RAPIDO.md** - Referência rápida de sintaxe
4. **RELACAO_ENTRE_CLASSES.md** - Diagrama de relacionamentos
5. **EXEMPLOS_DE_USO.md** - 10+ exemplos práticos
6. **CHANGELOG.md** - Histórico de mudanças

## 💡 Exemplos Rápidos

### Criar um Usuario
```java
Usuario user = new Usuario(
    UUID.randomUUID(),
    "João Silva",
    "joao@example.com",
    LocalDate.of(1990, 5, 15)
);
```

### Criar uma Postagem
```java
Postagem post = new Postagem(
    "Título",
    "Conteúdo aqui...",
    user
);
```

### Criar um Post com Reação
```java
Reaction reaction = new Reaction(1500L, 50L);
Post post = new Post(
    1L, "Título", "Corpo", 5L, 1000L,
    new String[]{"java", "spring"},
    reaction
);
```

## 🎯 Casos de Uso

### 1. Aprendizado
- Estudar estrutura de classes Java
- Entender anotações Lombok
- Aprender configuração Maven
- Praticar Spring Boot

### 2. Base para Projetos
- Use como template para novos projetos
- Expanda com Controllers, Services, Repositories
- Adicione suas próprias lógicas de negócio

### 3. Demonstração
- Mostre em apresentações
- Use em treinamentos
- Ensine conceitos Spring Boot

## 📊 Estatísticas

| Métrica | Valor |
|---------|-------|
| Linhas de Código Java | 393 |
| Comentários | 95%+ |
| Classes Java | 5 |
| Arquivos de Documentação | 6 |
| Dependências Maven | 5 |
| Anotações Lombok | 3 (@Data, @NoArgsConstructor, @AllArgsConstructor) |

## ✨ Características

✅ **Bem Comentado** - 95%+ de cobertura
✅ **Educativo** - Perfeito para aprender
✅ **Profissional** - Segue best practices
✅ **Escalável** - Base sólida para expansão
✅ **Documentado** - 6+ arquivos de referência
✅ **Pronto para Usar** - Compile e execute

## 🔗 Próximas Etapas

### Curto Prazo
- [ ] Criar Controllers REST
- [ ] Implementar Repositories
- [ ] Adicionar Services

### Médio Prazo
- [ ] Escrever testes unitários
- [ ] Adicionar validações
- [ ] Implementar segurança

### Longo Prazo
- [ ] Deploy em produção
- [ ] Documentação API (Swagger)
- [ ] Monitoramento (Prometheus/Grafana)

## 📞 Suporte

Para dúvidas sobre:
- **Estrutura**: Veja DOCUMENTACAO_COMPLETA.md
- **Sintaxe Rápida**: Veja GUIA_RAPIDO.md
- **Relacionamentos**: Veja RELACAO_ENTRE_CLASSES.md
- **Exemplos**: Veja EXEMPLOS_DE_USO.md
- **Histórico**: Veja CHANGELOG.md

## 📝 Licença

Este projeto é fornecido como material educativo.

## 👨‍💻 Desenvolvido com

- ☕ Java 21
- 🚀 Spring Boot 3.2.0
- 📦 Maven 3.6+
- 🛠️ Lombok
- 📚 Documentação extensiva

---

**Pronto para começar?** Clone o repositório, compile com `mvn clean compile` e explore as classes! 🚀


