# Projeto ToDoList

O Projeto ToDoList é um back end feito m conjunto com a Rocketseat e a professora Daniele Leão desenvolvido em Java 17 com o uso do framework Spring Boot. Ele permite aos usuários criar, listar e atualizar tarefas em um banco de dados.

# Dependências

O projeto faz uso das seguintes dependências:

- **Spring Boot**: Um framework para criar aplicativos Java de maneira mais fácil e rápida.
- **Spring Boot Data JPA**: Usado para simplificar o acesso a bancos de dados relacionais com o Spring Data JPA.
- **H2 Database**: Um banco de dados em memória usado para desenvolvimento e teste.
- **Lombok**: Uma biblioteca que reduz a verbosidade do código Java, gerando automaticamente getters, setters e outros métodos.
- **BCrypt**: Uma biblioteca para trabalhar com funções de hash bcrypt, comumente usadas para armazenar senhas com segurança.

# Estrutura do Projeto

O projeto está organizado nas seguintes partes principais:

- **Model**: Contém as classes de modelo que representam os objetos do domínio, como `UserModel` e `TaskModel`.
- **Controller**: Contém os controladores que lidam com as requisições HTTP e fornecem respostas.
- **Repository**: Contém as interfaces que estendem o `JpaRepository` para acessar os dados do banco.
- **Filter**: Contém um filtro para autenticar e autorizar solicitações com base em credenciais.
- **Utils**: Contém classes utilitárias, como `utils`, para facilitar a cópia de propriedades não nulas entre objetos.

## Executando o Projeto

Para executar o projeto, você pode usar o comando `mvn spring-boot:run`. Certifique-se de que todas as dependências estão configuradas corretamente em seu arquivo `pom.xml`. Lembre-se de personalizar as configurações do aplicativo, como o banco de dados e as credenciais de autenticação, no arquivo `application.properties`.

# Endpoints da API
- `POST /users/` : Cria um novo usuário.
- `POST /users/login` : Realiza a autenticação do - usuário.
- `POST /tasks/`: Cria uma nova tarefa.
- `GET /tasks/`: Lista tarefas do usuário autenticado.
- `PUT /tasks/{id}`: Atualiza uma tarefa existente.

# Estrutura do Projeto
O projeto é dividido em várias partes:

- `UserModel.java`: Define a entidade de usuário.
- `UserController.java`: Controlador para gerenciamento de usuários.
- `IUserRepository.java` : Repositório para usuários.
- `TaskModel.java` : Define a entidade de tarefa.
- `TaskController.java` : Controlador para gerenciamento de tarefas.
- `ITaskRepository.java` : Repositório para tarefas.
- `FilterTaskAuth.java` : Filtro de autorização para autenticar solicitações.
- `ExceptionHandlerController.java` : Controlador de aconselhamento para lidar com exceções.
- `utils.java` : Classe utilitária com métodos de cópia de propriedades não nulas.
- `TodolistApplication.java`: Classe principal para iniciar a aplicação.

# Contribuição

Sinta-se à vontade para contribuir para este projeto! Você pode reportar problemas, sugerir melhorias ou enviar solicitações de pull.

---
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.11/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.11/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.11/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


