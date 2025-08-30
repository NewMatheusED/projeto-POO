package com.poo.demo.application.service;

import com.poo.demo.domain.dto.ProcessoDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Serviço específico para consumir a API de Processos
 */
@Service
public class ProcessoApiService {

    private final SmartHttpClient httpClient;
    private final ObjectMapper jsonMapper;
    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public ProcessoApiService(SmartHttpClient httpClient, ObjectMapper jsonMapper) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
    }

    /**
     * Busca as emendas de um processo específico
     * @param codigo Código do processo
     * @return Array de ProcessoDto com as emendas do processo
     */
    public ApiResponse<ProcessoDto[]> buscarEmendasProcesso(String codigo) {
        try {
            String url = SENADO_API_BASE + "/processo/emenda?idProcesso=" + codigo;
            Object jsonObject = httpClient.getAsJsonObject(url);
            
            // Converte o array para ProcessoDto[]
            if (jsonObject instanceof List) {
                List<?> jsonList = (List<?>) jsonObject;
                ProcessoDto[] emendas = new ProcessoDto[jsonList.size()];
                
                for (int i = 0; i < jsonList.size(); i++) {
                    emendas[i] = jsonMapper.convertValue(jsonList.get(i), ProcessoDto.class);
                }
                
                return ApiResponse.success(emendas);
            } else {
                return ApiResponse.error("Formato de resposta inesperado", 500);
            }
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar emendas do processo: " + e.getMessage(), 500);
        }
    }

    /**
     * Busca o JSON bruto de um processo específico
     * @param codigo Código do processo
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    public ApiResponse<Object> buscarProcessoBruto(String codigo) {
        try {
            String url = SENADO_API_BASE + "/processo/emenda?idProcesso=" + codigo;
            
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar processo: " + e.getMessage(), 500);
        }
    }
}
