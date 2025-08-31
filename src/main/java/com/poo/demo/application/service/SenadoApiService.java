package com.poo.demo.application.service;
import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.dto.SenadorDetailDto;
import com.poo.demo.domain.dto.VotacaoParlamentarDto;
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

    /**
     * Busca todos os senadores atuais
     * @return Retorna um array de SenadorDto com os senadores atuais
     */
    public ApiResponse<SenadorDto[]> buscarSenadores() {
        try {
            String url = SENADO_API_BASE + "/senador/lista/atual";
            // Especifica o caminho exato para extrair o array de senadores
            String arrayPath = "ListaParlamentarEmExercicio.Parlamentares.Parlamentar";

            SenadorDto[] senadores = httpClient.get(url, SenadorDto[].class, arrayPath);
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
            
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar JSON válido: " + e.getMessage(), 500);
        }
    }

    public ApiResponse<Object> buscarDetalheSenadorBruto(String codigo) {
        try {
            String url = SENADO_API_BASE + "/senador/" + codigo;
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar detalhe do senador: " + e.getMessage(), 500);
        }
    }

    public ApiResponse<SenadorDetailDto.Parlamentar> buscarDetalheSenador(String codigo) {
        try {
            String url = SENADO_API_BASE + "/senador/" + codigo;
            String objectPath = "DetalheParlamentar.Parlamentar";
            SenadorDetailDto.Parlamentar senador = httpClient.get(url, SenadorDetailDto.Parlamentar.class, objectPath);
            return ApiResponse.success(senador);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar detalhe do senador: " + e.getMessage(), 500);
        }
    }

    /**
     * Busca as votações de um senador específico
     * @param codigo Código do senador
     * @return Retorna um VotacaoParlamentarDto com as votações do senador
     */
    public ApiResponse<VotacaoParlamentarDto.Votacoes[]> buscarVotacoesSenador(String codigo) {
        try {
            String url = SENADO_API_BASE + "/senador/" + codigo + "/votacoes";
            String objectPath = "VotacaoParlamentar.Parlamentar.Votacoes";
            VotacaoParlamentarDto.Votacoes[] votacoes = httpClient.get(url, VotacaoParlamentarDto.Votacoes[].class, objectPath);
            return ApiResponse.success(votacoes);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar votações do senador: " + e.getMessage(), 500);
        }
    }

    public ApiResponse<Object> buscarVotosSenadorBruto(String codigo) {
        try {
            String url = SENADO_API_BASE + "/senador/" + codigo + "/votacoes";
            Object jsonObject = httpClient.getAsJsonObject(url);
            return ApiResponse.success(jsonObject);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar votos do senador: " + e.getMessage(), 500);
        }
    }
}
