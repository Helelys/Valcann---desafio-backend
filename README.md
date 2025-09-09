# 📘 API de Usuários - Valcann

API RESTful de leitura de usuários, baseada em um mock (`mock-users.json`) localizado em `resources`. Esta API simula um backend real e segue boas práticas como paginação, filtros, CORS, organização em camadas e logs simples.

---

## 🚀 Como executar

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em:
📍 `http://localhost:8080`

---

## 📂 Estrutura de Pastas

```
valcann/
├── controller/      # Endpoints da API (UsuarioController)
├── model/           # Classe Usuario (dados do usuário)
├── pagination/      # Record para paginação (Pagination)
├── repository/      # Fonte dos dados mockados (UsuarioRepositoryMock)
├── response/        # Envelopes de resposta (UsuarioResponse)
├── service/         # Lógica de negócio (UsuarioService)
resources/
└── mock-users.json  # Lista de usuários simulados
```

---

## 📡 Endpoints disponíveis

### 🔍 GET `/users`

Retorna uma lista paginada de usuários, com filtros opcionais.

#### 🔧 Parâmetros (todos opcionais):

| Parâmetro   | Tipo    | Descrição                         | Padrão |
| ----------- | ------- | --------------------------------- | ------ |
| `page`      | int     | Número da página                  | 1      |
| `page_size` | int     | Tamanho da página (máx 50)        | 10     |
| `q`         | string  | Busca por nome ou email           | -      |
| `role`      | string  | Filtro por cargo (ex: "admin")    | -      |
| `is_active` | boolean | Filtro por usuários ativos ou não | -      |
| `sort`      | string  | Ordenação por ID: `asc` ou `desc` | asc    |

#### 📥 Exemplo de requisição:

```bash
curl "http://localhost:8080/users?page=1&page_size=5&q=john&role=admin&is_active=true&sort=desc"
```

#### 📤 Exemplo de resposta:

```json
{
  "data": [
    {
      "id": 3,
      "name": "John Doe",
      "email": "john@empresa.com",
      "role": "admin",
      "is_active": true,
      "created_at": "2023-09-01T00:00:00.000+00:00"
    }
  ],
  "pagination": {
    "page": 1,
    "page_size": 5,
    "total": 1
  }
}
```

---

### 🔍 GET `/users/{id}`

Retorna os dados de um único usuário pelo ID.

#### 📥 Exemplo:

```bash
curl http://localhost:8080/users/2
```

#### 📤 Respostas possíveis:

* `200 OK` com o objeto do usuário
* `404 Not Found` se o ID não existir

---

## ⚙️ Configurações via `application.properties`

```properties
pagination.default=10
pagination.max=50
server.port=8080
```

---

## 📋 Logs

Exibidos no console via `Slf4j`, com entradas de filtros e buscas por ID.

---

## 🧪 Testes

Use ferramentas como:

* `curl` no terminal
* [Postman](https://www.postman.com/)
* [Insomnia](https://insomnia.rest/)

---

Feito com ☕ e Spring Boot.
