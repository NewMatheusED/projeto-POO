package com.poo.demo.presentation.controller;

import com.poo.demo.application.service.SenadoApiService;
import com.poo.demo.domain.dto.SenadorDetailDto;
import com.poo.demo.domain.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Buscar Detalhes do Senador", description = "Retorna informações detalhadas de um senador específico")
    @GetMapping("/senadores/{codigo}/detalhe")
    public ResponseEntity<ApiResponse<SenadorDetailDto>> buscarDetalheSenador(
            @Parameter(description = "Código do senador", required = true) @PathVariable String codigo) {
        logRequest("/senadores/" + codigo + "/detalhe", "GET", "codigo=" + codigo);
        ApiResponse<SenadorDetailDto> response = senadoApiService.buscarDetalheSenador(codigo);
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
