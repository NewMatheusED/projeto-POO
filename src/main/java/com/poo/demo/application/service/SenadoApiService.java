package com.poo.demo.application.service;
import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço específico para consumir a API do Senado Federal
 * Segue o princípio de responsabilidade única (SRP)
 */
@Service
public class SenadoApiService {

    private final HttpClient httpClient;
    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public SenadoApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ApiResponse<SenadorDto[]> buscarSenadores() {
        try {
            String url = SENADO_API_BASE + "/senador/lista/atual";
            SenadorDto[] senadores = httpClient.get(url, SenadorDto[].class);
            return ApiResponse.success(senadores);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar senadores: " + e.getMessage(), 500);
        }

    }
}
