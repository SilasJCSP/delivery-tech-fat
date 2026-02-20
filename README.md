# 🚚 Delivery Tech FAT

[![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-4.0.2-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow.svg)](#)

API REST para um sistema de delivery (projeto acadêmico – Arquitetura de Sistemas/FAT). O projeto segue arquitetura em camadas (Controller, Service, Repository) e já possui CRUD de **Clientes**, documentação **OpenAPI/Swagger**, endpoints de **Actuator** e base para **autenticação JWT**.

## ✨ O que já foi implementado

- **Clientes**
  - CRUD completo: criar, listar, buscar por ID, buscar por email, buscar por nome, atualizar
  - Inativação (soft delete) e endpoint para alternar status (`PATCH /api/clientes/{id}/status`)
  - Validações com Jakarta Validation
- **Persistência**
  - H2 em memória
  - JPA/Hibernate
- **Observabilidade / Docs**
  - Swagger/OpenAPI via Springdoc (`/swagger-ui.html`, `/v3/api-docs`)
  - Actuator habilitado (`/actuator/health`, `/actuator/info`, `/actuator/prometheus`, etc.)
- **Segurança (em andamento)**
  - Configuração stateless e filtro JWT (base)
  - Utilitário `JwtUtil` para gerar/validar tokens (ainda há métodos TODO)
  - Roles definidas (ADMIN, CLIENTE, RESTAURANTE, ENTREGADOR, USER)

## 🛠️ Stack

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 4.0.2 |
| Maven | 3.8+ |
| Spring Data JPA | — |
| H2 | Runtime |
| Spring Security | — |
| Springdoc OpenAPI | — |
| Actuator/Micrometer | — |

## 🚀 Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+ (ou Maven Wrapper)

### Rodando localmente

```bash
git clone https://github.com/SilasJCSP/delivery-tech-fat.git
cd delivery-tech-fat

# build
./mvnw clean install -DskipTests

# run
./mvnw spring-boot:run
```

Aplicação: `http://localhost:8080`

### Banco de dados (H2)

- Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:delivery`
- User: `sa` | Password: (vazio)

## 🔌 Endpoints

### Clientes (`/api/clientes`)

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/clientes` | Cadastrar cliente |
| GET | `/api/clientes` | Listar clientes ativos |
| GET | `/api/clientes/{id}` | Buscar por ID |
| GET | `/api/clientes/email/{email}` | Buscar por email |
| GET | `/api/clientes/buscar?nome=...` | Buscar por nome |
| PUT | `/api/clientes/{id}` | Atualizar |
| DELETE | `/api/clientes/{id}` | Inativar |
| PATCH | `/api/clientes/{id}/status` | Alternar status |

### Docs / Observabilidade

- Swagger UI: `/swagger-ui.html`
- OpenAPI JSON: `/v3/api-docs`
- Actuator: `/actuator` (ver `application.properties` para endpoints expostos)

## 🧪 Testes

```bash
./mvnw test
```

## 🔄 Próximos passos (Roadmap)

- [ ] Finalizar autenticação/autorização JWT (Login/Refresh, roles e proteção por endpoint)
- [ ] Pedidos (endpoints, regras de negócio e relacionamento com cliente)
- [ ] Migração para PostgreSQL
- [ ] Testes de integração
- [ ] Docker e CI/CD (GitHub Actions)

## 👨‍💻 Autor

Silas — Projeto acadêmico FAT (2026)

---

**Status**: 🟡 Em Desenvolvimento | **Última atualização**: 2026-02-20