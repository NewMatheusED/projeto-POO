# API do Senado - Conversor Universal XML → JSON

## 🚀 **Visão Geral**

Esta API converte automaticamente respostas XML da API do Senado para JSON, usando um conversor universal que funciona com **qualquer estrutura XML** sem precisar definir mapeamentos específicos.

## ✨ **Características Principais**

- **Conversão Automática**: Converte QUALQUER XML para JSON
- **DTO Simples**: Você só precisa criar o DTO final em JSON
- **Universal**: Funciona com diferentes APIs (Senado, Câmara, etc.)
- **SOLID**: Código limpo seguindo princípios SOLID
- **Spring Boot**: Framework moderno e robusto

## 🔧 **Como Funciona**

### 1. **Conversor Universal** (`UniversalXmlConverter`)
- Recebe qualquer XML
- Converte automaticamente para JSON
- Não precisa definir estrutura XML

### 2. **Cliente Inteligente** (`SmartHttpClient`)
- Detecta automaticamente o formato da resposta
- Converte XML para JSON transparentemente
- Sempre retorna dados em formato JSON

### 3. **Uso Simples**
```java
// Basta criar o DTO final
public class SenadorDto {
    private String nome;
    private String partido;
    // ... outros campos
}

// E usar o conversor universal
List<SenadorDto> senadores = universalConverter.convertXmlToDtoList(xmlResponse, SenadorDto.class);
```

## 📋 **Endpoints Disponíveis**

### **API do Senado**
- `GET /api/senado/senadores` - Lista de senadores
- `GET /api/senado/health` - Status da API

## 🏗️ **Arquitetura**

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Controller    │───▶│   Service        │───▶│  SmartHttpClient│
└─────────────────┘    └──────────────────┘    └─────────────────┘
                                │                       │
                                ▼                       ▼
                       ┌──────────────────┐    ┌─────────────────┐
                       │UniversalConverter│    │  RestTemplate   │
                       └──────────────────┘    └─────────────────┘
```

## 🚀 **Como Usar**

### 1. **Iniciar a Aplicação**
```bash
mvn spring-boot:run
```

### 2. **Testar a API**
```bash
# Health check
curl http://localhost:8080/api/senado/health

# Buscar senadores
curl http://localhost:8080/api/senado/senadores
```

### 3. **Adicionar Novas APIs**
```java
@Service
public class NovaApiService {
    
    private final SmartHttpClient httpClient;
    
    public List<NovoDto> buscarDados() {
        String url = "https://api.exemplo.com/dados";
        return httpClient.get(url, NovoDto[].class);
    }
}
```

## 📁 **Estrutura do Projeto**

```
src/main/java/com/poo/demo/
├── application/service/
│   ├── SenadoApiService.java          # Serviço da API do Senado
│   ├── UniversalXmlConverter.java     # Conversor universal XML→JSON
│   └── ResponseFormatConverter.java   # Detector de formatos
├── domain/dto/
│   └── SenadorDto.java               # DTO do senador
├── infrastructure/client/
│   └── SmartHttpClient.java          # Cliente HTTP inteligente
└── presentation/controller/
    └── SenadoController.java         # Controller da API
```

## 🎯 **Vantagens**

1. **Simplicidade**: Não precisa definir estrutura XML
2. **Flexibilidade**: Funciona com qualquer API
3. **Manutenibilidade**: Código limpo e bem estruturado
4. **Extensibilidade**: Fácil adicionar novas APIs
5. **Consistência**: Sempre retorna JSON

## 🔍 **Exemplo de Conversão**

### **XML da API do Senado:**
```xml
<ListaMateriasTramitando>
    <Materia>
        <IdentificacaoMateria>
            <NomeMateria>PEC sobre Reforma Tributária</NomeMateria>
            <SiglaMateria>PEC</SiglaMateria>
        </IdentificacaoMateria>
    </Materia>
</ListaMateriasTramitando>
```

### **DTO Final (JSON):**
```json
{
    "nome": "PEC sobre Reforma Tributária",
    "sigla": "PEC"
}
```

## 🚀 **Próximos Passos**

1. **Testar a API** com dados reais
2. **Adicionar novas APIs** conforme necessário
3. **Implementar cache** para melhor performance
4. **Adicionar validações** nos DTOs
5. **Implementar logs** para monitoramento

---

**🎉 Agora você tem uma API limpa e funcional para converter QUALQUER XML para JSON!**
