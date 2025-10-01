package com.poo.demo.application.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.demo.domain.dto.SenadorDto;
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

    /**
     * Busca todos os senadores atuais.
     * Utiliza o método da classe abstrata para eliminar duplicação.
     * @return Retorna um array de SenadorDto com os senadores atuais
     */
    public ApiResponse<SenadorDto[]> buscarSenadores() {
        String endpoint = "/senador/lista/atual";
        String arrayPath = "ListaParlamentarEmExercicio.Parlamentares.Parlamentar";
        return executeApiCall(endpoint, SenadorDto[].class, arrayPath, "buscar senadores");
    }

    /**
     * Busca o JSON válido da API do Senado para análise da estrutura.
     * Utiliza o método da classe abstrata para eliminar duplicação.
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    public ApiResponse<Object> buscarJsonBruto() {
        String endpoint = "/senador/lista/atual";
        return executeRawApiCall(endpoint, "buscar JSON bruto");
    }

    public ApiResponse<Object> buscarDetalheSenadorBruto(String codigo) {
        validateParameter(codigo, "código do senador");
        String endpoint = "/senador/" + codigo;
        return executeRawApiCall(endpoint, "buscar detalhe do senador");
    }

    public ApiResponse<SenadorDetailDto.Parlamentar> buscarDetalheSenador(String codigo) {
        validateParameter(codigo, "código do senador");
        String endpoint = "/senador/" + codigo;
        String objectPath = "DetalheParlamentar.Parlamentar";
        return executeApiCall(endpoint, SenadorDetailDto.Parlamentar.class, objectPath, "buscar detalhe do senador");
    }

    /**
     * Busca as votações de um senador específico.
     * Utiliza o método da classe abstrata para eliminar duplicação.
     * @param codigo Código do senador
     * @return Retorna um VotacaoParlamentarDto com as votações do senador
     */
    public ApiResponse<VotacaoParlamentarDto.Votacoes[]> buscarVotacoesSenador(String codigo) {
        validateParameter(codigo, "código do senador");
        String endpoint = "/senador/" + codigo + "/votacoes";
        String objectPath = "VotacaoParlamentar.Parlamentar.Votacoes";
        return executeApiCall(endpoint, VotacaoParlamentarDto.Votacoes[].class, objectPath, "buscar votações do senador");
    }

    public ApiResponse<Object> buscarVotosSenadorBruto(String codigo) {
        validateParameter(codigo, "código do senador");
        String endpoint = "/senador/" + codigo + "/votacoes";
        return executeRawApiCall(endpoint, "buscar votos do senador");
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
