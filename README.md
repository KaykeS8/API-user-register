# 👤 User Registration API

API REST para cadastro e gerenciamento de usuários, desenvolvida com **Spring Boot 4** e **Java 21**. O projeto aplica boas práticas de desenvolvimento como arquitetura em camadas, DTOs, validações com Bean Validation e tratamento centralizado de erros.

---

## 🚀 Tecnologias utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.5 |
| Spring Data JPA | — |
| Spring Validation | — |
| MySQL | 8+ |
| Maven | — |

---

## 📁 Estrutura do projeto

```
src/main/java/first/step/convertcoin/
├── controller/
│   └── UserController.java        # Endpoints da API
├── service/
│   └── UserService.java           # Regras de negócio
├── repository/
│   └── UserRepository.java        # Acesso ao banco de dados
├── entity/
│   └── UserEntity.java            # Entidade JPA
├── dto/
│   ├── UserRequestDto.java        # Dados de entrada
│   └── UserResponseDto.java       # Dados de saída
├── mapper/
│   └── UserMapper.java            # Conversão entity ↔ DTO
└── exception/
    ├── UserNotFoundException.java
    ├── CurrencyException.java
    └── GlobalExceptionHandler.java
```

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21+
- Maven 3.8+
- MySQL 8+

### 1. Clone o repositório

```bash
git clone https://github.com/KaykeS8/API-convertCurrency.git
cd API-convertCurrency
```

### 2. Configure o banco de dados

Crie um banco no MySQL:

```sql
CREATE DATABASE userdb;
```

### 3. Configure as variáveis de ambiente

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Execute o projeto

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📌 Endpoints

Base URL: `/api/users`

### Listar todos os usuários

```http
GET /api/users
```

**Resposta `200 OK`:**
```json
[
  {
    "id": 1,
    "name": "Kayke Silva",
    "email": "kayke@email.com"
  }
]
```

---

### Buscar usuário por ID

```http
GET /api/users/{id}
```

**Resposta `200 OK`:**
```json
{
  "id": 1,
  "name": "Kayke Silva",
  "email": "kayke@email.com"
}
```

**Resposta `404 Not Found`:**
```json
{
  "timestamp": "2026-04-12T10:30:00",
  "status": 404,
  "error": "User not found",
  "message": "User with ID 1 does not exist"
}
```

---

### Buscar usuário por e-mail

```http
GET /api/users/search?email=kayke@email.com
```

**Resposta `200 OK`:**
```json
{
  "id": 1,
  "name": "Kayke Silva",
  "email": "kayke@email.com"
}
```

---

### Criar usuário

```http
POST /api/users
Content-Type: application/json
```

**Body:**
```json
{
  "name": "Kayke Silva",
  "email": "kayke@email.com"
}
```

**Resposta `201 Created`:**
```json
{
  "id": 1,
  "name": "Kayke Silva",
  "email": "kayke@email.com"
}
```

**Resposta `400 Bad Request` (validação):**
```json
{
  "timestamp": "2026-04-12T10:30:00",
  "status": 400,
  "error": "Validation error",
  "message": {
    "email": "must be a well-formed email address",
    "name": "must not be blank"
  }
}
```

---

### Atualizar usuário

```http
PUT /api/users/{id}
Content-Type: application/json
```

**Body:**
```json
{
  "name": "Kayke Santos",
  "email": "kayke.novo@email.com"
}
```

**Resposta `200 OK`:**
```json
{
  "id": 1,
  "name": "Kayke Santos",
  "email": "kayke.novo@email.com"
}
```

---

### Deletar usuário

```http
DELETE /api/users/{id}
```

**Resposta `204 No Content`**

---

## 🛡️ Validações

Os campos de entrada são validados automaticamente antes de chegar na camada de serviço:

| Campo | Regra |
|---|---|
| `name` | Obrigatório, não pode ser vazio |
| `email` | Obrigatório, deve ter formato válido |

Erros de validação retornam `400 Bad Request` com detalhes de cada campo inválido.

---

## 🧱 Decisões técnicas

**DTOs com `record`** — Os objetos de transferência de dados usam `record` do Java 16+, que gera automaticamente construtor, getters, `equals` e `hashCode`, deixando o código mais limpo e imutável.

**Mapper estático** — A conversão entre `UserEntity` e DTOs é centralizada na classe `UserMapper`, evitando lógica duplicada e facilitando manutenção.

**Injeção via construtor** — Todos os beans usam injeção por construtor em vez de `@Autowired` em campo, seguindo a recomendação oficial do Spring.

**Tratamento de erros centralizado** — O `GlobalExceptionHandler` captura exceções conhecidas (`UserNotFoundException`, erros de validação) e genéricas, sempre retornando um corpo de resposta padronizado com `timestamp`, `status`, `error` e `message`.

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

Feito por **Kayke** — [GitHub](https://github.com/KaykeS8)# API-convertCurrency
