# 📦 API RESTful de Cadastro de Usuários e Itens

Esta é uma API RESTful desenvolvida com **Java (Spring Boot)** que permite o **cadastro de usuários** e o gerenciamento de **itens** (como uma lista de compras, estoque, etc). Os dados são armazenados em um banco de dados **PostgreSQL**. A API segue a arquitetura em camadas (Controllers, Services, Repositories, DTOs, Entities) e expõe endpoints seguros e bem definidos.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Spring Security 
- Maven

---

## 🧱 Estrutura da API

### 🔐 Usuários

- **POST** `/usuario/cadastro`  
  Cadastra um novo usuário com nome, email e senha.

- **POST** `/auth/login`
  Realiza login e retorna um token JWT.

- **GET** `/usuario/listar`
  Lista todos os usuários do banco.

- **PUT** `/usuario/{id}`
  Atualiza um usuário específico.

- **DELETE** `/usuario/delete/{id}`
  Deleta um usuário específico.
---

### 📋 Itens

- **POST** `/item/cadastro`  
  Cadastra um novo item vinculado a um usuário.

- **GET** `/listar/itens`  
  Lista todos os itens do banco.

- **PUT** `/itens/{id}`  
  Atualiza um item específico.

- **DELETE** `/delete/item/{id}`  
  Remove um item pelo ID.

---
