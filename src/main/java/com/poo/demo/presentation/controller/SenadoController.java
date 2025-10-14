package com.poo.demo.presentation.controller;

import com.poo.demo.application.service.SenadoApiService;
import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.dto.SenadorDetailDto;
import com.poo.demo.domain.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.poo.demo.domain.dto.VotacaoParlamentarDto;

/**
 * Controller específico para expor endpoints da API do Senado.
 * Estende BaseApiController para eliminar duplicação de código.
 */
@RestController
@RequestMapping("/v1/senado")
@Tag(name = "Senado Federal", description = "Endpoints para integração com a API do Senado Federal")
public class SenadoController extends BaseApiController {

    private final SenadoApiService senadoApiService;

    @Autowired
    public SenadoController(SenadoApiService senadoApiService) {
        this.senadoApiService = senadoApiService;
    }

    @Operation(summary = "Buscar Senadores", description = "Retorna lista de todos os senadores")
    @GetMapping("/senadores")
    public ResponseEntity<ApiResponse<SenadorDto[]>> buscarSenadores() {
        logRequest("/senadores", "GET");
        return createSuccessResponse(senadoApiService.buscarSenadores().getData());
    }

    @Operation(summary = "Buscar Senadores (JSON Bruto)", description = "Retorna dados brutos dos senadores em formato JSON")
    @GetMapping("/senadores/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarJsonBruto() {
        logRequest("/senadores/json-bruto", "GET");
        return createSuccessResponse(senadoApiService.buscarJsonBruto().getData());
    }

    @Operation(summary = "Buscar Detalhes do Senador (JSON Bruto)", description = "Retorna dados brutos detalhados de um senador específico")
    @GetMapping("/senadores/{codigo}/detalhe/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarDetalheSenadorBruto(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        logRequest("/senadores/" + codigo + "/detalhe/json-bruto", "GET", "codigo=" + codigo);
        ApiResponse<Object> response = senadoApiService.buscarDetalheSenadorBruto(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    @Operation(summary = "Buscar Detalhes do Senador", description = "Retorna informações detalhadas de um senador específico")
    @GetMapping("/senadores/{codigo}/detalhe")
    public ResponseEntity<ApiResponse<SenadorDetailDto.Parlamentar>> buscarDetalheSenador(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        logRequest("/senadores/" + codigo + "/detalhe", "GET", "codigo=" + codigo);
        ApiResponse<SenadorDetailDto.Parlamentar> response = senadoApiService.buscarDetalheSenador(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    @Operation(summary = "Buscar Votações do Senador (JSON Bruto)", description = "Retorna dados brutos das votações de um senador específico")
    @GetMapping("/senadores/{codigo}/votacoes/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarVotacoesSenadorBruto(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        logRequest("/senadores/" + codigo + "/votacoes/json-bruto", "GET", "codigo=" + codigo);
        ApiResponse<Object> response = senadoApiService.buscarVotosSenadorBruto(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    @Operation(summary = "Buscar Votações do Senador", description = "Retorna lista de votações de um senador específico")
    @GetMapping("/senadores/{codigo}/votacoes")
    public ResponseEntity<ApiResponse<VotacaoParlamentarDto.Votacoes[]>> buscarVotacoesSenador(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        logRequest("/senadores/" + codigo + "/votacoes", "GET", "codigo=" + codigo);
        ApiResponse<VotacaoParlamentarDto.Votacoes[]> response = senadoApiService.buscarVotacoesSenador(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }
    
    /**
     * Implementação do método abstrato para identificar o controller.
     * @return Nome do controller
     */
    @Override
    public String getControllerName() {
        return "Senado";
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
