# 🚚 Delivery Tech FAT

[![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-4.0.2-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow.svg)](#)

API REST para gerenciamento de serviços de delivery com arquitetura em camadas. Projeto acadêmico da disciplina de Arquitetura de Sistemas.

## 📋 Sobre o Projeto

Este projeto implementa uma API RESTful completa para gerenciamento de clientes e pedidos em um sistema de delivery. Segue as boas práticas de arquitetura de software com separação clara de responsabilidades entre camadas (Controller, Service, Repository).

### Características

- ✅ API REST com Spring Boot 4.0.2
- ✅ Banco de dados em memória (H2)
- ✅ ORM com JPA/Hibernate
- ✅ Validação de dados com Jakarta Validation
- ✅ Geração automática de getters/setters com Lombok
- ✅ Suporte a DevTools para desenvolvimento rápido
- 🔄 Em desenvolvimento: Autenticação e autorização

## 🛠️ Stack Tecnológico

| Tecnologia | Versão |
|-----------|--------|
| Java | 17 |
| Spring Boot | 4.0.2 |
| Maven | 3.8+ |
| JPA/Hibernate | Padrão |
| Lombok | Padrão |
| H2 Database | Runtime |
| Jakarta Validation | 3.0.2 |

## 📦 Pré-requisitos

- Java 17 ou superior
- Maven 3.8.1 ou superior (ou use o Maven Wrapper incluído)

## 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/delivery-tech-fat.git
cd delivery-tech-fat/delivery_api
```

### 2. Instalar dependências e compilar

```bash
# Usando Maven
mvn clean install -DskipTests

# Ou usando Maven Wrapper (Windows)
.\mvnw.cmd clean install -DskipTests

# Ou usando Maven Wrapper (Linux/Mac)
./mvnw clean install -DskipTests
```

### 3. Executar a aplicação

```bash
# Usando Maven
mvn spring-boot:run

# Ou usando Maven Wrapper
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/deliverutech/delivery_api/
│   │   ├── controller/          # Endpoints REST
│   │   ├── service/             # Lógica de negócio
│   │   ├── repository/          # Acesso a dados
│   │   ├── model/               # Entidades JPA
│   │   ├── dto/
│   │   │   ├── request/         # DTOs de entrada
│   │   │   └── response/        # DTOs de saída
│   │   └── DeliveryApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/...                 # Testes unitários
```

## 🔌 Endpoints da API

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/clientes` | Criar novo cliente |
| GET | `/api/clientes` | Listar todos os clientes |
| GET | `/api/clientes/{id}` | Obter cliente por ID |
| PUT | `/api/clientes/{id}` | Atualizar cliente |
| DELETE | `/api/clientes/{id}` | Deletar cliente |

### Exemplo de Requisição

```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva Santos",
    "telefone": "11987654321"
  }'
```

## 🗄️ Banco de Dados

A aplicação utiliza **H2 Database** em memória. 

### Acessar o Console H2

1. Inicie a aplicação
2. Acesse: `http://localhost:8080/h2-console`
3. JDBC URL: `jdbc:h2:mem:testdb`
4. Username: `sa`
5. Password: (deixar em branco)

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar com coverage
mvn test jacoco:report
```

## 📝 Validações

### ClienteRequest

- **nome**: Obrigatório, 10-100 caracteres
- **telefone**: Obrigatório, 10-11 dígitos

## 🔄 Roadmap

- [ ] Implementar autenticação JWT
- [ ] Adicionar autorização com roles
- [ ] Criar endpoints de pedidos
- [ ] Integração com banco de dados PostgreSQL
- [ ] Testes de integração
- [ ] Documentação com Swagger/OpenAPI
- [ ] Deploy em Docker
- [ ] CI/CD com GitHub Actions

## 👥 Contribuindo

Este é um projeto acadêmico. Para contribuições, por favor:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👨‍💻 Autor

**Silas** - Projeto acadêmico FAT 2026

## 📞 Suporte

Para dúvidas ou sugestões sobre o projeto, abra uma [Issue](../../issues) no repositório.

---

**Status**: 🟡 Em Desenvolvimento | **Última atualização**: Janeiro 2026
