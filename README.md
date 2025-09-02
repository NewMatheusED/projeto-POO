# 🏛️ API de Análise de Senadores - Sistema de Conversão Universal XML → JSON

## 🚀 **Visão Geral**

Sistema robusto de análise de dados parlamentares que converte automaticamente respostas XML da API do Senado para JSON, implementando arquitetura limpa seguindo princípios SOLID e object calisthenics. O projeto oferece uma solução universal para conversão de dados XML de qualquer API governamental para formato JSON estruturado.

## ✨ **Características Principais**

- **🔄 Conversão Universal**: Converte qualquer estrutura XML para JSON automaticamente
- **🏗️ Arquitetura Limpa**: Implementação seguindo princípios SOLID e object calisthenics
- **📊 Múltiplas APIs**: Suporte para Senado e Processos Legislativos ate o momento
- **💾 Persistência**: Banco MySQL com Flyway para migrações
- **🔍 Dados Detalhados**: Informações completas de senadores, processos e votações
- **⚡ Performance**: Cliente HTTP inteligente com detecção automática de formato
- **🛡️ Tratamento de Erros**: Sistema robusto de tratamento de exceções

## 🏛️ **Funcionalidades Implementadas**

### **1. API do Senado**
- **Lista de Senadores**: Informações básicas de todos os senadores
- **Detalhes de Senador**: Dados completos incluindo telefones, serviços e informações parlamentares
- **Histórico de Votações**: Votações detalhadas de cada senador com tramitações
- **Dados Brutos**: Acesso ao JSON bruto para análise avançada

### **2. API de Processos Legislativos**
- **Emendas de Processos**: Busca emendas de processos específicos
- **Dados de Tramitação**: Informações sobre tramitação legislativa
- **Conversão Automática**: XML → JSON transparente

## 🔧 **Arquitetura do Sistema**

### **Padrão de Camadas (Clean Architecture)**
```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                      │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │SenadoCtrl   │  │ProcessoCtrl │  │   ApiController     │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                   APPLICATION LAYER                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │SenadoService│  │ProcessoService│  │  ExternalApiService │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                  INFRASTRUCTURE LAYER                      │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │SmartHttpCl  │  │HttpClient   │  │  RestTemplateConfig │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### **Princípios SOLID Implementados**

1. **Single Responsibility Principle (SRP)**
   - Cada serviço tem uma responsabilidade específica
   - Controllers focados apenas na exposição de endpoints

2. **Open/Closed Principle (OCP)**
   - Sistema extensível para novas APIs sem modificar código existente
   - Conversor universal funciona com qualquer estrutura XML

3. **Liskov Substitution Principle (LSP)**
   - Interface `HttpClient` permite diferentes implementações
   - `SmartHttpClient` e `RestTemplateHttpClient` são intercambiáveis

4. **Interface Segregation Principle (ISP)**
   - Interfaces específicas para cada tipo de operação
   - DTOs especializados para diferentes contextos

5. **Dependency Inversion Principle (DIP)**
   - Dependências injetadas via construtor
   - Abstrações não dependem de implementações concretas

## 📋 **Endpoints da API**

### **Base URL**: `http://localhost:8080/api/v1`

#### **🏛️ Senado** (`/senado`)
- `GET /senadores` - Lista todos os senadores
- `GET /senadores/json-bruto` - Dados brutos dos senadores
- `GET /senadores/{codigo}/detalhe` - Detalhes completos de um senador
- `GET /senadores/{codigo}/detalhe/json-bruto` - Dados brutos do detalhe
- `GET /senadores/{codigo}/votacoes` - Histórico de votações do senador
- `GET /senadores/{codigo}/votacoes/json-bruto` - Votações em formato bruto

#### **📜 Processos Legislativos** (`/processo`)
- `GET /{codigo}` - Emendas de um processo específico
- `GET /{codigo}/json-bruto` - Dados brutos do processo

## 🚀 **Como Executar**

### **Pré-requisitos**
- Java 21+
- Maven 3.6+
- MySQL > 8.0 < 8.4
- Docker (opcional)

### **1. Execução Local**

#### **Configuração do Banco de Dados**
```bash
# Variáveis de ambiente necessárias
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_DATABASE=analise_senadores
export MYSQL_USER=seu_usuario
export MYSQL_PASSWORD=sua_senha
export MYSQL_CHARSET=utf8mb4
```

#### **Executar a Aplicação**
```bash
# Clonar o repositório
git clone <seu-repositorio>
cd demo

# Instalar dependências
mvn clean install

# Executar
run.bat
```

### **2. Execução com Docker**

#### **Desenvolvimento Local**
```bash
# Executar com docker-compose (inclui MySQL)
docker-compose up -d

# Ou apenas para desenvolvimento
docker-compose -f docker-compose.dev.yml up -d
```

#### **Build da Imagem Docker**
```bash
# Build da imagem
docker build -t analise-senadores-api:latest .

# Executar container
docker run -p 8080:8080 \
  -e MYSQL_HOST=host.docker.internal \
  -e MYSQL_PORT=3306 \
  -e MYSQL_DATABASE=analise_senadores \
  -e MYSQL_USER=seu_usuario \
  -e MYSQL_PASSWORD=sua_senha \
  analise-senadores-api:latest
```

### **3. Deploy em Produção - Render**

#### **🎨 Render (Recomendado)**
O Render é a plataforma escolhida para deploy da aplicação. É gratuito, fácil de usar e oferece integração direta com GitHub.

#### **Configuração Automática via GitHub**
1. **Conectar repositório no Render.com**
2. **O arquivo `render.yaml` já está configurado**
3. **Deploy automático via GitHub Actions**

#### **Deploy Manual via CLI**
```bash
# Instalar Render CLI
npm install -g @render/cli

# Login no Render
render login

# Deploy manual
./scripts/deploy-render.sh
```

#### **Configuração Manual no Render Dashboard**
1. Acesse [render.com](https://render.com)
2. Conecte sua conta GitHub
3. Crie um novo "Web Service"
4. Selecione seu repositório
5. Configure as variáveis de ambiente
6. Deploy automático!

#### **📖 Guia Completo de Configuração**
Para instruções detalhadas passo a passo, consulte o arquivo **[RENDER_SETUP.md](./RENDER_SETUP.md)** que inclui:
- Criação de conta e configuração inicial
- Configuração do banco de dados MySQL
- Criação do Web Service
- Configuração de variáveis de ambiente
- Obtenção de credenciais para GitHub Actions
- Troubleshooting e solução de problemas

### **5. Testar a API**
```bash
# Health check
curl http://localhost:8080/actuator/health

# Lista de senadores
curl http://localhost:8080/api/v1/senado/senadores

# Buscar detalhes de um senador
curl http://localhost:8080/api/v1/senado/senadores/1234/detalhe

# Buscar emendas de um processo
curl http://localhost:8080/api/v1/processo/123456
```

## 🏗️ **Estrutura do Projeto**

```
src/main/java/com/poo/demo/
├── application/service/           # Camada de Aplicação
│   ├── SenadoApiService.java     # Serviço específico do Senado
│   ├── ProcessoApiService.java   # Serviço de processos legislativos
│   ├── ExternalApiService.java   # Serviço para APIs externas
│   ├── UniversalXmlConverter.java # Conversor universal XML→JSON
│   └── ResponseFormatConverter.java # Detector de formatos
├── domain/                       # Camada de Domínio
│   ├── dto/                     # Data Transfer Objects
│   │   ├── SenadorDto.java      # DTO básico de senador
│   │   ├── SenadorDetailDto.java # DTO detalhado de senador
│   │   ├── ProcessoDto.java     # DTO de processo legislativo
│   │   └── VotacaoParlamentarDto.java # DTO de votações
│   └── entity/                  # Entidades de domínio
│       └── ApiResponse.java     # Resposta padronizada da API
├── infrastructure/               # Camada de Infraestrutura
│   ├── client/                  # Clientes HTTP
│   │   ├── HttpClient.java      # Interface HTTP
│   │   ├── SmartHttpClient.java # Cliente inteligente
│   │   └── RestTemplateHttpClient.java # Implementação RestTemplate
│   ├── config/                  # Configurações
│   │   └── RestTemplateConfig.java # Configuração do RestTemplate
│   └── exception/               # Tratamento de exceções
│       └── GlobalExceptionHandler.java # Handler global
└── presentation/                 # Camada de Apresentação
    └── controller/              # Controllers REST
        ├── SenadoController.java # Controller do Senado
        ├── ProcessoController.java # Controller de processos
        └── ApiController.java    # Controller genérico
```

## 🎯 **Vantagens da Arquitetura**

### **1. Manutenibilidade**
- Código organizado em camadas bem definidas
- Responsabilidades claramente separadas
- Fácil localização e modificação de funcionalidades

### **2. Extensibilidade**
- Adicionar novas APIs sem modificar código existente
- Conversor universal funciona com qualquer estrutura XML
- Sistema de plugins para novas funcionalidades

### **3. Testabilidade**
- Dependências injetadas facilitam testes unitários
- Interfaces bem definidas permitem mocks
- Separação de responsabilidades facilita testes isolados

### **4. Performance**
- Cliente HTTP inteligente com cache automático
- Conversão XML→JSON otimizada
- Tratamento assíncrono de requisições

## 🔍 **Exemplos de Uso**

### **Buscar Senadores**
```java
@Autowired
private SenadoApiService senadoService;

// Buscar todos os senadores
ApiResponse<SenadorDto[]> response = senadoService.buscarSenadores();
if (response.isSuccess()) {
    SenadorDto[] senadores = response.getData();
    // Processar dados...
}
```

### **Consultar Processo Legislativo**
```java
@Autowired
private ProcessoApiService processoService;

// Buscar emendas de um processo
ApiResponse<ProcessoDto[]> response = processoService.buscarEmendasProcesso("PEC001");
if (response.isSuccess()) {
    ProcessoDto[] emendas = response.getData();
    // Analisar emendas...
}
```

## 🔄 **CI/CD e Workflow**

### **GitHub Actions**
O projeto inclui workflows automatizados para:

- **🧪 Testes Automáticos**: Execução de testes em cada PR
- **🚀 Deploy Automático**: Deploy no Render via push na branch main
- **📦 Build Otimizado**: Build da aplicação Spring Boot

### **Workflows Disponíveis**
- `.github/workflows/ci-cd.yml` - Pipeline completo de CI/CD com deploy no Render

### **Secrets Necessários**
Configure os seguintes secrets no GitHub:
- `RENDER_SERVICE_ID` - ID do serviço no Render
- `RENDER_API_KEY` - API key do Render

### **Deploy Automático**
1. **Push na branch `main`** → Deploy automático no Render
2. **Push na branch `develop`** → Deploy em ambiente de desenvolvimento
3. **Pull Request** → Execução de testes automáticos

## 🚀 **Próximos Passos**

### **Funcionalidades Planejadas**
1. **📊 Dashboard Web**: Interface gráfica para análise de dados
2. **🔍 Busca Avançada**: Filtros e pesquisa por múltiplos critérios
3. **📈 Análise Estatística**: Relatórios e métricas parlamentares

### **Melhorias Técnicas**
1. **⚡ Cache Redis**: Cache distribuído para melhor performance
2. **📝 Logs Estruturados**: Sistema de logging avançado
3. **🧪 Testes**: Cobertura completa de testes
4. **🔒 Segurança**: Implementação de autenticação e autorização

### **Padrões de Código**
- Seguir princípios SOLID
- Implementar object calisthenics
- Manter cobertura de testes acima de 80%
- Usar nomes descritivos para variáveis e métodos