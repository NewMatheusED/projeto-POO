# Projeto Spring Boot - API do Senado Federal

Este projeto demonstra como estruturar uma aplicação Spring Boot seguindo os princípios SOLID e object calisthenics para consumir a API do Senado Federal de forma organizada e extensível.

## 🏛️ Sobre a API do Senado

**URL Base**: `https://legis.senado.leg.br`

A API do Senado Federal fornece acesso a dados legislativos abertos, incluindo:
- Proposições legislativas (PL, PEC, MPV, etc.)
- Informações sobre senadores
- Sessões legislativas
- Comissões parlamentares
- Tramitações e votações
- Documentos parlamentares

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definidas:

### Domain Layer
- **Entidades**: `ApiResponse<T>` - Respostas padronizadas
- **DTOs**: `ProposicaoDto`, `SenadorDto`, `SessaoDto`
- **Regras de negócio**: Lógica central da aplicação

### Infrastructure Layer
- **Cliente HTTP**: Interface e implementação para consumir APIs externas
- **Configurações**: Beans e configurações do Spring
- **Tratamento de Exceções**: Handler global para erros

### Application Layer
- **Serviços**: `SenadoApiService`, `ApiExplorerService`
- **Casos de uso**: Lógica de aplicação específica para o Senado

### Presentation Layer
- **Controllers**: Endpoints REST organizados por domínio
- **Validação**: Tratamento de parâmetros e respostas

## 🚀 Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+

### Executando a Aplicação
```bash
cd demo
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints Disponíveis

### 🏛️ API do Senado

#### Proposições Legislativas
```
GET /api/v1/senado/proposicoes - Todas as proposições
GET /api/v1/senado/proposicoes/{id} - Proposição por ID
GET /api/v1/senado/proposicoes/ano/{ano} - Proposições por ano
```

#### Senadores
```
GET /api/v1/senado/senadores - Todos os senadores
GET /api/v1/senado/senadores/{id} - Senador por ID
GET /api/v1/senado/senadores/uf/{uf} - Senadores por UF
```

#### Sessões Legislativas
```
GET /api/v1/senado/sessoes - Todas as sessões
GET /api/v1/senado/sessoes/{id} - Sessão por ID
GET /api/v1/senado/sessoes/data/{data} - Sessões por data
GET /api/v1/senado/sessoes/tipo/{tipo} - Sessões por tipo
```

### 🔍 Exploração da API

#### Descoberta de Rotas
```
GET /api/v1/senado/explorar - Guia de rotas disponíveis
GET /api/v1/senado/explorar/rotas - Testa todas as rotas principais
GET /api/v1/senado/explorar/rota?rota={rota} - Testa rota específica
GET /api/v1/senado/explorar/url?url={url} - Testa URL completa
GET /api/v1/senado/explorar/parametros - Testa rotas com parâmetros
GET /api/v1/senado/explorar/relatorio - Relatório completo
GET /api/v1/senado/explorar/ajuda - Guia de uso
```

### 🛠️ API Genérica (Legacy)
```
GET /api/v1/consulta?url={URL}&responseType={TIPO}
POST /api/v1/envio?url={URL}&responseType={TIPO}
GET /api/v1/health - Health check
```

## 🔧 Exemplos de Uso

### 1. Explorar Rotas da API do Senado
```bash
# Ver todas as rotas disponíveis
curl "http://localhost:8080/api/v1/senado/explorar"

# Testar todas as rotas principais
curl "http://localhost:8080/api/v1/senado/explorar/rotas"

# Testar rota específica
curl "http://localhost:8080/api/v1/senado/explorar/rota?rota=/dadosabertos/materias"
```

### 2. Consultar Proposições
```bash
# Todas as proposições
curl "http://localhost:8080/api/v1/senado/proposicoes"

# Proposição por ID
curl "http://localhost:8080/api/v1/senado/proposicoes/12345"

# Proposições de 2024
curl "http://localhost:8080/api/v1/senado/proposicoes/ano/2024"
```

### 3. Consultar Senadores
```bash
# Todos os senadores
curl "http://localhost:8080/api/v1/senado/senadores"

# Senador por ID
curl "http://localhost:8080/api/v1/senado/senadores/67890"

# Senadores de São Paulo
curl "http://localhost:8080/api/v1/senado/senadores/uf/SP"
```

### 4. Consultar Sessões
```bash
# Todas as sessões
curl "http://localhost:8080/api/v1/senado/sessoes"

# Sessão por ID
curl "http://localhost:8080/api/v1/senado/sessoes/11111"

# Sessões de uma data específica
curl "http://localhost:8080/api/v1/senado/sessoes/data/2024-01-15"
```

## 🎯 Princípios SOLID Aplicados

### 1. Single Responsibility Principle (SRP)
- `SenadoApiService` gerencia apenas dados do Senado
- `ApiExplorerService` gerencia apenas exploração de rotas
- `ProposicaoDto` representa apenas proposições legislativas

### 2. Open/Closed Principle (OCP)
- Estrutura permite adicionar novos tipos de dados sem modificação
- Novos endpoints podem ser facilmente implementados

### 3. Liskov Substitution Principle (LSP)
- `RestTemplateHttpClient` pode substituir `HttpClient`
- Implementações são intercambiáveis

### 4. Interface Segregation Principle (ISP)
- `HttpClient` define apenas métodos necessários
- DTOs específicos para cada tipo de entidade

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

### Adicionando Nova Entidade do Senado

1. **Criar DTO específico**:
```java
public class ComissaoDto {
    private Long id;
    private String nome;
    private String sigla;
    private String tipo;
    // getters, setters, construtores
}
```

2. **Criar serviço específico**:
```java
@Service
public class ComissaoService {
    private final HttpClient httpClient;
    
    public ApiResponse<ComissaoDto[]> buscarComissoes() {
        // implementação
    }
}
```

3. **Criar controller específico**:
```java
@RestController
@RequestMapping("/api/v1/senado/comissoes")
public class ComissaoController {
    // endpoints
}
```

## 🧪 Testes

Para executar os testes:
```bash
mvnw.cmd test
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
- Endpoints de exploração para debug

## 📖 Próximos Passos

1. **Validação**: Adicionar Bean Validation para DTOs
2. **Cache**: Implementar cache para APIs externas
3. **Rate Limiting**: Adicionar controle de taxa de requisições
4. **Métricas**: Integrar com Micrometer
5. **Documentação**: Adicionar Swagger/OpenAPI
6. **Testes**: Expandir cobertura de testes
7. **Segurança**: Implementar autenticação/autorização
8. **Mapeamento**: Adicionar mais DTOs para outras entidades do Senado
9. **Filtros**: Implementar filtros avançados para consultas
10. **Paginação**: Adicionar suporte a paginação nas consultas

## 🚨 Importante

- A API do Senado pode ter limitações de taxa de requisições
- Algumas rotas podem retornar erro 404 (não implementadas)
- O formato de resposta pode variar entre diferentes endpoints
- Recomenda-se sempre usar os endpoints de exploração primeiro para entender a estrutura da API
