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
     * @param sigla Sigla do processo
     * @param numero Número do processo
     * @param ano Ano do processo
     * @return Array de VotacoesProjeto com os votos do processo
     */
    public ApiResponse<VotacoesProjetoDto> buscarVotosProcesso(String sigla, String numero, String ano) {
        try {
            String url = SENADO_API_BASE + "/votacaoComissao/materia/" + sigla + "/" + numero + "/" + ano;
            
            // Obtém a resposta como objeto JSON primeiro
            VotacoesProjetoDto jsonObject = httpClient.get(url, VotacoesProjetoDto.class, "VotacoesComissao.Votacoes.Votacao");

            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar votos do processo: " + e.getMessage(), 500);
        }
    }
}
