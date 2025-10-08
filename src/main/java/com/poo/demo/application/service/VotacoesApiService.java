package com.poo.demo.application.service;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.SmartHttpClient;
import com.poo.demo.domain.dto.VotacoesProjetoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Serviço específico para consumir a API de Votações
 */
@Service
public class VotacoesApiService {

    private final SmartHttpClient httpClient;
    private final ObjectMapper jsonMapper;
    private static final String SENADO_API_BASE = "https://legis.senado.leg.br/dadosabertos";

    @Autowired
    public VotacoesApiService(SmartHttpClient httpClient, ObjectMapper jsonMapper) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
    }

    /**
     * Busca todos os votos de um processo específico
     * @param codigo Código do processo
     * @return Array de VotacoesProjeto com os votos do processo
     */
    public ApiResponse<VotacoesProjetoDto.VotacoesProjeto[]> buscarVotosProcesso(String codigo) {
        try {
            String url = SENADO_API_BASE + "/votacao?idProcesso=" + codigo;
            
            // Obtém a resposta como objeto JSON primeiro
            Object jsonObject = httpClient.getAsJsonObject(url);
            
            // Converte o array para VotacoesProjeto[]
            if (jsonObject instanceof List) {
                List<?> jsonList = (List<?>) jsonObject;
                VotacoesProjetoDto.VotacoesProjeto[] votacoes = new VotacoesProjetoDto.VotacoesProjeto[jsonList.size()];
                
                for (int i = 0; i < jsonList.size(); i++) {
                    votacoes[i] = jsonMapper.convertValue(jsonList.get(i), VotacoesProjetoDto.VotacoesProjeto.class);
                }
                
                return ApiResponse.success(votacoes);
            } else {
                return ApiResponse.error("Formato de resposta inesperado", 500);
            }
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar votos do processo: " + e.getMessage(), 500);
        }
    }
}
