package com.poo.demo.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.demo.domain.dto.ProcessoDto;
import com.poo.demo.domain.dto.ProcessoDtoDetail;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço específico para consumir a API de Processos.
 * Estende AbstractApiService para eliminar duplicação de código.
 */
@Service
public class ProcessoApiService extends AbstractApiService {

    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public ProcessoApiService(SmartHttpClient httpClient, ObjectMapper jsonMapper) {
        super(httpClient, jsonMapper, SENADO_API_BASE);
    }

    /**
     * Busca as emendas de um processo específico.
     * Utiliza o método da classe abstrata para eliminar duplicação.
     * @param codigo Código do processo
     * @return Array de ProcessoDtoDetail com as emendas do processo
     */
    public ApiResponse<ProcessoDto[]> buscarEmendasProcesso(String codigo) {
        validateParameter(codigo, "código do processo");
        String endpoint = "/processo/emenda?idProcesso=" + codigo;
        return executeApiCall(endpoint, ProcessoDto[].class, null, "buscar emendas do processo");
    }

    public ApiResponse<ProcessoDto[]> buscarEmendasProcessoGeral() {
        String endpoint = "/processo/emenda";
        return executeApiCall(endpoint, ProcessoDto[].class, null, "buscar emendas do processo");
    }

    /**
     * Busca o JSON bruto de um processo específico.
     * Utiliza o método da classe abstrata para eliminar duplicação.
     * @param codigo Código do processo
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    public ApiResponse<Object> buscarProcessoBruto(String codigo) {
        validateParameter(codigo, "código do processo");
        String endpoint = "/processo/emenda?idProcesso=" + codigo;
        return executeRawApiCall(endpoint, "buscar processo bruto");
    }
    
    /**
     * Implementação do método abstrato para identificar a API.
     * @return Nome da API
     */
    @Override
    public String getApiName() {
        return "Processos Legislativos";
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
