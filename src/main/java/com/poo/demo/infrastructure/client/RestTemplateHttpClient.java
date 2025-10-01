package com.poo.demo.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementação concreta do cliente HTTP usando RestTemplate.
 * Estende AbstractHttpClient para eliminar duplicação de código.
 */
@Component
public class RestTemplateHttpClient extends AbstractHttpClient {

    @Autowired
    public RestTemplateHttpClient(RestTemplate restTemplate, ObjectMapper jsonMapper) {
        super(restTemplate, jsonMapper);
    }

    @Override
    public <T> T get(String url, Class<T> responseType) {
        validateUrl(url);
        validateResponseType(responseType);
        
        ResponseEntity<String> response = executeRequest(url, HttpMethod.GET, null, String.class);
        return processResponse(response.getBody(), responseType);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> responseType) {
        validateUrl(url);
        validateResponseType(responseType);
        
        ResponseEntity<String> response = executeRequest(url, HttpMethod.POST, request, String.class);
        return processResponse(response.getBody(), responseType);
    }

    @Override
    protected <T> T processResponse(String responseBody, Class<T> responseType) {
        try {
            return jsonMapper.readValue(responseBody, responseType);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar resposta JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public String getClientType() {
        return "RestTemplate";
    }

    @Override
    public String getClientVersion() {
        return "1.0";
    }
}
