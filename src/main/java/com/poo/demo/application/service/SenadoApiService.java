package com.poo.demo.application.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.demo.domain.dto.SenadorDetailDto;
import com.poo.demo.domain.dto.VotacaoParlamentarDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço específico para consumir a API do Senado Federal.
 * Estende AbstractApiService para eliminar duplicação de código.
 */
@Service
public class SenadoApiService extends AbstractApiService {

    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public SenadoApiService(SmartHttpClient httpClient, ObjectMapper jsonMapper) {
        super(httpClient, jsonMapper, SENADO_API_BASE);
    }

    public ApiResponse<SenadorDetailDto> buscarDetalheSenador(String codigo) {
        validateParameter(codigo, "código do senador");
        String endpoint = "/senador/" + codigo;
        String objectPath = "DetalheParlamentar.Parlamentar";
        return executeApiCall(endpoint, SenadorDetailDto.class, objectPath, "buscar detalhe do senador");
    }

    /**
     * Implementação do método abstrato para identificar a API.
     * @return Nome da API
     */
    @Override
    public String getApiName() {
        return "Senado Federal";
    }
    
    /**
     * Implementação do método abstrato para retornar a versão da API.
     * @return Versão da API
     */
    @Override
    public String getApiVersion() {
        return "v1.0";
    }
}
