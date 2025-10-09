package com.poo.demo.presentation.controller;

import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata base para todos os controllers da API.
 * Implementa padrões comuns e elimina duplicação de código entre controllers.
 */
@CrossOrigin(origins = "*")
public abstract class BaseApiController {
    
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Cria uma resposta de sucesso padronizada com status HTTP 200.
     * 
     * @param data Dados a serem retornados
     * @return ResponseEntity com ApiResponse de sucesso
     */
    protected <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }
    
    /**
     * Cria uma resposta de sucesso com status HTTP customizado.
     * 
     * @param data Dados a serem retornados
     * @param statusCode Código de status HTTP
     * @return ResponseEntity com ApiResponse de sucesso
     */
    protected <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(T data, HttpStatus statusCode) {
        return ResponseEntity.status(statusCode).body(ApiResponse.success(data));
    }
    
    /**
     * Cria uma resposta de erro padronizada.
     * 
     * @param message Mensagem de erro
     * @param statusCode Código de status HTTP
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createErrorResponse(String message, HttpStatus statusCode) {
        return ResponseEntity.status(statusCode).body(ApiResponse.error(message, statusCode.value()));
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 400 (Bad Request).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createBadRequestResponse(String message) {
        return createErrorResponse(message, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 404 (Not Found).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createNotFoundResponse(String message) {
        return createErrorResponse(message, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 500 (Internal Server Error).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createInternalErrorResponse(String message) {
        return createErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 401 (Unauthorized).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createUnauthorizedResponse(String message) {
        return createErrorResponse(message, HttpStatus.UNAUTHORIZED);
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 403 (Forbidden).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createForbiddenResponse(String message) {
        return createErrorResponse(message, HttpStatus.FORBIDDEN);
    }
    
    /**
     * Cria uma resposta de erro com status HTTP 422 (Unprocessable Entity).
     * 
     * @param message Mensagem de erro
     * @return ResponseEntity com ApiResponse de erro
     */
    protected <T> ResponseEntity<ApiResponse<T>> createUnprocessableEntityResponse(String message) {
        return createErrorResponse(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    /**
     * Valida se um parâmetro não é nulo ou vazio.
     * 
     * @param param Parâmetro a ser validado
     * @param paramName Nome do parâmetro para mensagens de erro
     * @throws IllegalArgumentException se o parâmetro for inválido
     */
    protected void validateParameter(String param, String paramName) {
        if (param == null || param.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("Parâmetro '%s' é obrigatório e não pode ser vazio", paramName));
        }
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
     * Registra uma requisição para logging e auditoria.
     * 
     * @param endpoint Endpoint acessado
     * @param method Método HTTP utilizado
     * @param params Parâmetros da requisição (opcional)
     */
    protected void logRequest(String endpoint, String method, String params) {
        String timestamp = getCurrentTimestamp();
        String logMessage = String.format("[%s] %s %s", timestamp, method, endpoint);
        
        if (params != null && !params.trim().isEmpty()) {
            logMessage += " - Parâmetros: " + params;
        }
        
        System.out.println(logMessage);
    }
    
    /**
     * Registra uma requisição para logging e auditoria (sem parâmetros).
     * 
     * @param endpoint Endpoint acessado
     * @param method Método HTTP utilizado
     */
    protected void logRequest(String endpoint, String method) {
        logRequest(endpoint, method, null);
    }
    
    
    /**
     * Método abstrato que cada controller deve implementar para identificar o serviço.
     * 
     * @return Nome identificador do serviço (ex: "Senado", "Processos", "Autenticação")
     */
    public abstract String getControllerName();
    
    /**
     * Método abstrato que cada controller deve implementar para retornar a versão da API.
     * 
     * @return Versão da API do controller
     */
    public abstract String getApiVersion();
    
    /**
     * Método concreto que retorna informações completas do controller.
     * 
     * @return String formatada com nome e versão do controller
     */
    public String getControllerInfo() {
        return String.format("%s Controller - API v%s", getControllerName(), getApiVersion());
    }
}
