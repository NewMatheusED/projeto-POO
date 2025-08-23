# Projeto Spring Boot - Consumo de APIs Externas

Este projeto demonstra como estruturar uma aplicação Spring Boot seguindo os princípios SOLID e object calisthenics para consumir APIs externas de forma organizada e extensível.

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definidas:

### Domain Layer
- **Entidades**: Representam os objetos de negócio
- **DTOs**: Objetos de transferência de dados
- **Regras de negócio**: Lógica central da aplicação

### Infrastructure Layer
- **Cliente HTTP**: Interface e implementação para consumir APIs externas
- **Configurações**: Beans e configurações do Spring

### Application Layer
- **Serviços**: Casos de uso e lógica de aplicação
- **Orquestração**: Coordenação entre diferentes serviços

### Presentation Layer
- **Controllers**: Endpoints REST da aplicação
- **Tratamento de Exceções**: Handler global para erros

## 🚀 Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+

### Executando a Aplicação
```bash
cd demo
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints Disponíveis

### Health Check
```
GET /api/v1/health
```

### API Genérica
```
GET /api/v1/consulta?url={URL}&responseType={TIPO}
POST /api/v1/envio?url={URL}&responseType={TIPO}
```

### Usuários (Exemplo com JSONPlaceholder)
```
GET /api/v1/users
GET /api/v1/users/{id}
POST /api/v1/users
```

## 🔧 Exemplos de Uso

### 1. Consultar API Externa Genérica
```bash
curl "http://localhost:8080/api/v1/consulta?url=https://jsonplaceholder.typicode.com/users/1&responseType=com.poo.demo.domain.dto.UserDto"
```

### 2. Buscar Todos os Usuários
```bash
curl "http://localhost:8080/api/v1/users"
```

### 3. Buscar Usuário por ID
```bash
curl "http://localhost:8080/api/v1/users/1"
```

### 4. Criar Novo Usuário
```bash
curl -X POST "http://localhost:8080/api/v1/users" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@email.com",
    "username": "joaosilva"
  }'
```

## 🎯 Princípios SOLID Aplicados

### 1. Single Responsibility Principle (SRP)
- Cada classe tem uma única responsabilidade
- `UserService` gerencia apenas usuários
- `HttpClient` gerencia apenas comunicação HTTP

### 2. Open/Closed Principle (OCP)
- A estrutura permite extensões sem modificação
- Novos tipos de API podem ser adicionados facilmente

### 3. Liskov Substitution Principle (LSP)
- `RestTemplateHttpClient` pode substituir `HttpClient`
- Implementações são intercambiáveis

### 4. Interface Segregation Principle (ISP)
- `HttpClient` define apenas métodos necessários
- Interfaces específicas para diferentes tipos de API

### 5. Dependency Inversion Principle (DIP)
- Dependências são injetadas via construtor
- Alto nível não depende de baixo nível

## 📚 Object Calisthenics

### 1. Um nível de indentação por método
- Métodos são curtos e focados
- Evita aninhamento excessivo

### 2. Não use ELSE
- Uso de early returns
- Tratamento de exceções adequado

### 3. Encapsule primitivos
- Uso de DTOs para transferência de dados
- Entidades com comportamento

### 4. Coleções de primeira classe
- Arrays e Collections são tratados adequadamente
- Uso de generics para type safety

## 🔄 Extendendo o Projeto

### Adicionando Nova API Externa

1. **Criar DTO específico**:
```java
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    // getters, setters, construtores
}
```

2. **Criar serviço específico**:
```java
@Service
public class ProductService {
    private final HttpClient httpClient;
    
    public ApiResponse<ProductDto[]> buscarProdutos() {
        // implementação
    }
}
```

3. **Criar controller específico**:
```java
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    // endpoints
}
```

## 🧪 Testes

Para executar os testes:
```bash
mvn test
```

## 📝 Logs

A aplicação está configurada para mostrar logs detalhados:
- `com.poo.demo`: DEBUG
- `org.springframework.web`: INFO

## 🌐 CORS

CORS está configurado para permitir requisições de qualquer origem durante o desenvolvimento.

## 🔍 Monitoramento

A aplicação inclui:
- Health check endpoint
- Tratamento global de exceções
- Logs estruturados
- Timeouts configuráveis

## 📖 Próximos Passos

1. **Validação**: Adicionar Bean Validation
2. **Cache**: Implementar cache para APIs externas
3. **Rate Limiting**: Adicionar controle de taxa de requisições
4. **Métricas**: Integrar com Micrometer
5. **Documentação**: Adicionar Swagger/OpenAPI
6. **Testes**: Expandir cobertura de testes
7. **Segurança**: Implementar autenticação/autorização
