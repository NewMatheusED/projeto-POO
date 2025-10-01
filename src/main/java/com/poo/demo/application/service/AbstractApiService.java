package com.poo.demo.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;

/**
 * Classe abstrata base para todos os serviços de API externa.
 * Implementa padrões comuns e elimina duplicação de código entre serviços.
 */
public abstract class AbstractApiService {
    
    protected final SmartHttpClient httpClient;
    protected final ObjectMapper jsonMapper;
    protected final String API_BASE;
    
    /**
     * Construtor protegido para classes filhas.
     * 
     * @param httpClient Cliente HTTP inteligente para requisições
     * @param jsonMapper Mapper para conversão JSON
     * @param apiBase URL base da API específica
     */
    protected AbstractApiService(SmartHttpClient httpClient, ObjectMapper jsonMapper, String apiBase) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
        this.API_BASE = apiBase;
    }
    
    /**
     * Método concreto que executa chamadas para APIs externas com tratamento de erro padronizado.
     * Elimina duplicação de código entre todos os serviços de API.
     * 
     * @param endpoint Endpoint específico da API (ex: "/senador/lista/atual")
     * @param responseType Tipo da resposta esperada
     * @param arrayPath Caminho opcional para extrair array aninhado
     * @param operationName Nome da operação para mensagens de erro personalizadas
     * @return ApiResponse encapsulando o resultado
     */
    protected <T> ApiResponse<T> executeApiCall(String endpoint, Class<T> responseType, String arrayPath, String operationName) {
        try {
            String url = API_BASE + endpoint;
            T data = httpClient.get(url, responseType, arrayPath);
            return ApiResponse.success(data);
        } catch (Exception e) {
            String errorMessage = String.format("Erro ao %s: %s", operationName, e.getMessage());
            return ApiResponse.error(errorMessage, 500);
        }
    }
    
    /**
     * Método concreto para buscar dados brutos (JSON válido) de APIs externas.
     * 
     * @param endpoint Endpoint específico da API
     * @param operationName Nome da operação para mensagens de erro
     * @return ApiResponse com dados brutos em formato Object
     */
    protected ApiResponse<Object> executeRawApiCall(String endpoint, String operationName) {
        try {
            String url = API_BASE + endpoint;
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            String errorMessage = String.format("Erro ao %s: %s", operationName, e.getMessage());
            return ApiResponse.error(errorMessage, 500);
        }
    }
    
    /**
     * Método concreto para conversão de dados JSON usando Jackson.
     * Centraliza a lógica de conversão para evitar duplicação.
     * 
     * @param jsonObject Objeto JSON a ser convertido
     * @param targetType Tipo alvo da conversão
     * @return Objeto convertido para o tipo especificado
     */
    protected <T> T convertJsonObject(Object jsonObject, Class<T> targetType) {
        return jsonMapper.convertValue(jsonObject, targetType);
    }
    
    /**
     * Método concreto para validação de parâmetros de entrada.
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
     * Método abstrato que cada serviço deve implementar para identificar a API.
     * 
     * @return Nome identificador da API (ex: "Senado Federal", "Processos Legislativos")
     */
    public abstract String getApiName();
    
    /**
     * Método abstrato que cada serviço deve implementar para retornar a versão da API.
     * 
     * @return Versão da API (ex: "v1.0", "2.1")
     */
    public abstract String getApiVersion();
    
    /**
     * Método concreto que retorna informações completas da API.
     * 
     * @return String formatada com nome e versão da API
     */
    public String getApiInfo() {
        return String.format("%s - Versão %s", getApiName(), getApiVersion());
    }
}
