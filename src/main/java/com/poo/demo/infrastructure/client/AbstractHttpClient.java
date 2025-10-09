package com.poo.demo.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata base para todos os clientes HTTP.
 * Implementa funcionalidades comuns e elimina duplicação de código entre clientes HTTP.
 * Segue os princípios DRY (Don't Repeat Yourself) e SRP (Single Responsibility Principle).
 */
public abstract class AbstractHttpClient implements HttpClient {
    
    protected final RestTemplate restTemplate;
    protected final ObjectMapper jsonMapper;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Construtor protegido para classes filhas.
     * 
     * @param restTemplate Template REST para requisições HTTP
     * @param jsonMapper Mapper para conversão JSON
     */
    protected AbstractHttpClient(RestTemplate restTemplate, ObjectMapper jsonMapper) {
        this.restTemplate = restTemplate;
        this.jsonMapper = jsonMapper;
    }
    
    /**
     * Cria headers HTTP padronizados com suporte a múltiplos formatos.
     * Método concreto que pode ser usado por todas as implementações.
     * 
     * @return HttpHeaders configurados com tipos de conteúdo padrão
     */
    protected HttpHeaders createStandardHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, application/xml, text/xml, */*");
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "POO-Demo-Client/1.0");
        return headers;
    }
    
    /**
     * Cria headers HTTP customizados.
     * Permite adicionar headers específicos além dos padrões.
     * 
     * @param additionalHeaders Headers adicionais a serem incluídos
     * @return HttpHeaders configurados com headers padrão e adicionais
     */
    protected HttpHeaders createCustomHeaders(HttpHeaders additionalHeaders) {
        HttpHeaders headers = createStandardHeaders();
        if (additionalHeaders != null) {
            headers.putAll(additionalHeaders);
        }
        return headers;
    }
    
    /**
     * Cria um HttpEntity com headers padronizados.
     * 
     * @param body Corpo da requisição (pode ser null para GET)
     * @return HttpEntity configurado
     */
    protected HttpEntity<Object> createHttpEntity(Object body) {
        return new HttpEntity<>(body, createStandardHeaders());
    }
    
    /**
     * Cria um HttpEntity com headers customizados.
     * 
     * @param body Corpo da requisição (pode ser null para GET)
     * @param additionalHeaders Headers adicionais
     * @return HttpEntity configurado
     */
    protected HttpEntity<Object> createHttpEntity(Object body, HttpHeaders additionalHeaders) {
        return new HttpEntity<>(body, createCustomHeaders(additionalHeaders));
    }
    
    /**
     * Executa uma requisição HTTP genérica com tratamento de erro padronizado.
     * Método concreto que centraliza a lógica de requisições.
     * 
     * @param url URL da requisição
     * @param method Método HTTP (GET, POST, PUT, DELETE)
     * @param requestBody Corpo da requisição (null para GET)
     * @param responseType Tipo da resposta esperada
     * @return Resposta da requisição
     */
    protected <T> ResponseEntity<T> executeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
        try {
            logRequest(url, method.name());
            
            HttpEntity<Object> entity = createHttpEntity(requestBody);
            ResponseEntity<T> response = restTemplate.exchange(url, method, entity, responseType);
            
            logResponse(url, method.name(), response.getStatusCode().value());
            return response;
            
        } catch (Exception e) {
            logError(url, method.name(), e);
            throw new RuntimeException(String.format("Erro na requisição %s para %s: %s", method.name(), url, e.getMessage()), e);
        }
    }
    
    /**
     * Método abstrato para processamento específico de resposta.
     * Cada implementação pode definir como processar a resposta de forma específica.
     * 
     * @param responseBody Corpo da resposta como String
     * @param responseType Tipo da resposta esperada
     * @return Objeto processado da resposta
     */
    protected abstract <T> T processResponse(String responseBody, Class<T> responseType);
    
    /**
     * Valida se uma URL é válida.
     * 
     * @param url URL a ser validada
     * @throws IllegalArgumentException se a URL for inválida
     */
    protected void validateUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL não pode ser nula ou vazia");
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL deve começar com http:// ou https://");
        }
    }
    
    /**
     * Valida se um tipo de resposta é válido.
     * 
     * @param responseType Tipo da resposta
     * @throws IllegalArgumentException se o tipo for inválido
     */
    protected void validateResponseType(Class<?> responseType) {
        if (responseType == null) {
            throw new IllegalArgumentException("Tipo de resposta não pode ser nulo");
        }
    }
    
    /**
     * Registra uma requisição para logging e auditoria.
     * 
     * @param url URL da requisição
     * @param method Método HTTP
     */
    protected void logRequest(String url, String method) {
        String timestamp = getCurrentTimestamp();
        System.out.printf("[%s] HTTP %s: %s%n", timestamp, method, url);
    }
    
    /**
     * Registra uma resposta para logging e auditoria.
     * 
     * @param url URL da requisição
     * @param method Método HTTP
     * @param statusCode Código de status da resposta
     */
    protected void logResponse(String url, String method, int statusCode) {
        String timestamp = getCurrentTimestamp();
        System.out.printf("[%s] HTTP %s: %s - Status: %d%n", timestamp, method, url, statusCode);
    }
    
    /**
     * Registra um erro para logging e auditoria.
     * 
     * @param url URL da requisição
     * @param method Método HTTP
     * @param exception Exceção ocorrida
     */
    protected void logError(String url, String method, Exception exception) {
        String timestamp = getCurrentTimestamp();
        System.out.printf("[%s] ERRO HTTP %s: %s - %s%n", timestamp, method, url, exception.getMessage());
    }
    
    /**
     * Gera um timestamp atual formatado.
     * 
     * @return String com timestamp atual no formato yyyy-MM-dd HH:mm:ss
     */
    protected String getCurrentTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMATTER);
    }
    
    /**
     * Método abstrato que cada cliente deve implementar para identificar o tipo.
     * 
     * @return Nome identificador do cliente (ex: "RestTemplate", "Smart", "WebClient")
     */
    public abstract String getClientType();
    
    /**
     * Método abstrato que cada cliente deve implementar para retornar a versão.
     * 
     * @return Versão do cliente
     */
    public abstract String getClientVersion();
    
    /**
     * Método concreto que retorna informações completas do cliente.
     * 
     * @return String formatada com tipo e versão do cliente
     */
    public String getClientInfo() {
        return String.format("%s HTTP Client v%s", getClientType(), getClientVersion());
    }
}
