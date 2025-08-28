package com.poo.demo.application.service;
import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço específico para consumir a API do Senado Federal
 * Segue o princípio de responsabilidade única (SRP)
 */
@Service
public class SenadoApiService {

    private final SmartHttpClient httpClient;
    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public SenadoApiService(SmartHttpClient httpClient) {
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

    /**
     * Busca o JSON válido da API do Senado para análise da estrutura
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    public ApiResponse<Object> buscarJsonBruto() {
        try {
            String url = SENADO_API_BASE + "/senador/lista/atual";
            
            // Usa o SmartHttpClient para obter JSON como objeto (sem escape duplo)
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar JSON válido: " + e.getMessage(), 500);
        }
    }
}
