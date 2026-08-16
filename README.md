# Baozi Store API REST

Projeto desenvolvido para a Atividade Prática da disciplina **Desenvolvimento Web Back-End**.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- JSON
- Postman

## Arquitetura

O projeto segue a organização MVC solicitada:

```text
src/main/java/br/com/baozistore/
├── controller/
│   ├── ApiExceptionHandler.java
│   ├── ClienteController.java
│   ├── PedidoController.java
│   └── ProdutoController.java
├── model/
│   ├── Cliente.java
│   ├── Pedido.java
│   └── Produto.java
├── repository/
│   ├── ClienteRepository.java
│   ├── PedidoRepository.java
│   └── ProdutoRepository.java
└── BaoziStoreApplication.java
```

## Como executar no VS Code

### 1. Pré-requisitos

Instale:

- JDK 17 ou superior
- VS Code
- Extension Pack for Java
- Postman

Verifique o Java:

```bash
java -version
```

### 2. Abrir o projeto

No VS Code:

**File > Open Folder** e selecione a pasta `BaoziStoreAPI`.

### 3. Executar

No terminal:

```bash
mvn spring-boot:run
```

Ou execute a classe:

```text
BaoziStoreApplication.java
```

A API ficará disponível em:

```text
http://localhost:8080
```

## H2 Console

Acesse:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:baozistore
User Name: sa
Password:
```

## Endpoints

### Clientes

| Método | Endpoint | Função |
|---|---|---|
| POST | `/clientes` | Criar cliente |
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Consultar cliente por ID |
| PUT | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Excluir cliente |

### Produtos

| Método | Endpoint | Função |
|---|---|---|
| POST | `/produtos` | Criar produto |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Consultar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |

### Pedidos

| Método | Endpoint | Função |
|---|---|---|
| POST | `/pedidos` | Criar pedido |
| GET | `/pedidos` | Listar pedidos |
| GET | `/pedidos/{id}` | Consultar pedido por ID |
| PUT | `/pedidos/{id}` | Atualizar pedido |
| DELETE | `/pedidos/{id}` | Excluir pedido |

## Sequência recomendada para os testes

1. Criar o cliente.
2. Criar o produto.
3. Criar o pedido.
4. Fazer GET geral de clientes.
5. Fazer GET geral de produtos.
6. Fazer GET geral de pedidos.
7. Fazer GET de cliente por ID.
8. Fazer GET de produto por ID.
9. Fazer GET de pedido por ID.
10. Atualizar o cliente com PUT.
11. Atualizar o produto com PUT.
12. Atualizar o pedido com PUT.
13. Excluir o pedido.
14. Excluir o produto.
15. Excluir o cliente.

**Importante:** para os prints do trabalho, tire as capturas antes de executar os DELETEs finais.

## Exemplos JSON

### Cliente

```json
{
  "nome": "Volmir - RU: 1560690",
  "clienteDesde": "2026-08-15"
}
```

### Produto

```json
{
  "nome": "Baozi Tradicional",
  "preco": 8.50,
  "estoque": true
}
```

### Pedido

```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 5
}
```
