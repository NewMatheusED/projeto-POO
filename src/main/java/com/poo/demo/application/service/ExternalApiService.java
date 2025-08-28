package com.poo.demo.application.service;

import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço de aplicação para consumir APIs externas
 * Segue o princípio de responsabilidade única (SRP)
 */
@Service
public class ExternalApiService {

    private final HttpClient httpClient;

    @Autowired
    public ExternalApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Consulta uma API externa e retorna os dados
     * @param url URL da API
     * @param responseType Tipo da resposta esperada
     * @return Resposta encapsulada
     */
    public <T> ApiResponse<T> consultarApiExterna(String url, Class<T> responseType) {
        try {
            T data = httpClient.get(url, responseType);
            return ApiResponse.success(data);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao consultar API externa: " + e.getMessage(), 500);
        }
    }
}
