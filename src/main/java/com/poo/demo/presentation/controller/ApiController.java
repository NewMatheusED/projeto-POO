package com.poo.demo.presentation.controller;

import com.poo.demo.domain.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para expor endpoints da aplicação
 * Segue o princípio de responsabilidade única (SRP)
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Tag(name = "API Geral", description = "Endpoints gerais da aplicação")
public class ApiController {


    /**
     * Endpoint de health check
     * @return Status da aplicação
     */
    @Operation(summary = "Health Check", description = "Verifica se a aplicação está funcionando normalmente")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aplicação funcionando normalmente")
    })
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponse.success("Aplicação funcionando normalmente"));
    }
}
