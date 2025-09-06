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
 * Controller específico para expor endpoints da API do Senado
 */
@RestController
@RequestMapping("/api/v1/senado")
@CrossOrigin(origins = "*")
@Tag(name = "Senado Federal", description = "Endpoints para integração com a API do Senado Federal")
public class SenadoController {

    private final SenadoApiService senadoApiService;

    @Autowired
    public SenadoController(SenadoApiService senadoApiService) {
        this.senadoApiService = senadoApiService;
    }

    @Operation(summary = "Buscar Senadores", description = "Retorna lista de todos os senadores")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de senadores retornada com sucesso")
    })
    @GetMapping("/senadores")
    public ResponseEntity<ApiResponse<SenadorDto[]>> buscarSenadores() {
        return ResponseEntity.ok(senadoApiService.buscarSenadores());
    }

    @Operation(summary = "Buscar Senadores (JSON Bruto)", description = "Retorna dados brutos dos senadores em formato JSON")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dados brutos retornados com sucesso")
    })
    @GetMapping("/senadores/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarJsonBruto() {
        return ResponseEntity.ok(senadoApiService.buscarJsonBruto());
    }

    @Operation(summary = "Buscar Detalhes do Senador (JSON Bruto)", description = "Retorna dados brutos detalhados de um senador específico")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dados brutos do senador retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Senador não encontrado")
    })
    @GetMapping("/senadores/{codigo}/detalhe/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarDetalheSenadorBruto(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        return ResponseEntity.ok(senadoApiService.buscarDetalheSenadorBruto(codigo));
    }

    @Operation(summary = "Buscar Detalhes do Senador", description = "Retorna informações detalhadas de um senador específico")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Detalhes do senador retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Senador não encontrado")
    })
    @GetMapping("/senadores/{codigo}/detalhe")
    public ResponseEntity<ApiResponse<SenadorDetailDto.Parlamentar>> buscarDetalheSenador(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        return ResponseEntity.ok(senadoApiService.buscarDetalheSenador(codigo));
    }

    @Operation(summary = "Buscar Votações do Senador (JSON Bruto)", description = "Retorna dados brutos das votações de um senador específico")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dados brutos das votações retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Senador não encontrado")
    })
    @GetMapping("/senadores/{codigo}/votacoes/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarVotacoesSenadorBruto(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        return ResponseEntity.ok(senadoApiService.buscarVotosSenadorBruto(codigo));
    }

    @Operation(summary = "Buscar Votações do Senador", description = "Retorna lista de votações de um senador específico")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de votações retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Senador não encontrado")
    })
    @GetMapping("/senadores/{codigo}/votacoes")
    public ResponseEntity<ApiResponse<VotacaoParlamentarDto.Votacoes[]>> buscarVotacoesSenador(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        return ResponseEntity.ok(senadoApiService.buscarVotacoesSenador(codigo));
    }
}
