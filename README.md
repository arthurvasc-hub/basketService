# Basket Service (basketservice)

Microsserviço responsável pelo gerenciamento de cestas de compras em um sistema de e-commerce.

## Descrição

Este serviço permite criar, visualizar, atualizar e finalizar (pagar) cestas de compras. Ele se integra com um serviço externo (simulado via Platzi API) para obter informações de produtos e utiliza MongoDB para persistência de dados e Redis para caching.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.x**
    * Spring Web: Para APIs REST.
    * Spring Data MongoDB: Para interação com o MongoDB.
    * Spring Data Redis: Para caching.
    * Spring Cloud OpenFeign: Para chamadas HTTP declarativas a outros serviços.
    * Spring Boot Starter Validation: Para validação de dados.
    * Spring Boot Starter Cache: Para abstração de cache.
* **Maven**: Gerenciador de dependências e build.
* **MongoDB**: Banco de dados NoSQL para armazenar as cestas.
* **Redis**: Banco de dados em memória para cache de produtos.
* **Lombok**: Para reduzir código boilerplate (getters, setters, etc.).
* **Springdoc OpenAPI (Swagger UI)**: Para documentação da API.
* **Docker & Docker Compose**: Para ambiente de desenvolvimento (MongoDB, Redis).

## Pré-requisitos

* JDK 21 ou superior instalado.
* Maven 3.6 ou superior instalado.
* Docker e Docker Compose instalados (para rodar MongoDB e Redis localmente).

## Configuração e Execução

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    cd basketservice
    ```

2.  **Inicie os serviços de dependência (MongoDB e Redis) com Docker Compose:**
    ```bash
    docker-compose up -d
    ```
    Isso iniciará containers para MongoDB na porta 27017 e Redis na porta 6379.

3.  **Compile e execute a aplicação Spring Boot usando Maven:**
    ```bash
    mvn spring-boot:run
    ```
    Alternativamente, você pode compilar em um JAR e executá-lo:
    ```bash
    mvn clean package
    java -jar target/basketservice-0.0.1-SNAPSHOT.jar
    ```

A aplicação estará disponível em `http://localhost:8080` (ou a porta configurada).

## Documentação da API (Swagger UI)

Após iniciar a aplicação, a documentação interativa da API estará disponível em:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints Principais

* `POST /basket`: Cria uma nova cesta.
* `GET /basket/{id}`: Busca uma cesta pelo ID.
* `PUT /basket/{id}`: Atualiza os produtos de uma cesta existente.
* `PUT /basket/{id}/payment`: Realiza o pagamento de uma cesta.
* `DELETE /basket/{id}`: Deleta uma cesta.
* `GET /products`: Lista todos os produtos (do serviço externo, com cache).
* `GET /products/{id}`: Busca um produto pelo ID (do serviço externo, com cache).

## Configuração (application.yml)

As configurações de banco de dados, cache e URL do cliente Feign estão no arquivo `src/main/resources/application.yml`.

```yaml
spring:
  application:
    name: basket-service
  data:
    mongodb:
      host: localhost
      port: 27017
      database: basket-service
    redis:
      host: localhost
      port: 6379
      # password: sa # Descomente se seu Redis tiver senha
  cache:
    redis:
      time-to-live: 60000 # Tempo de vida do cache em milissegundos

basket:
  client:
    platzi: [https://api.escuelajs.co/api/v1](https://api.escuelajs.co/api/v1) # URL do serviço externo de produtos
