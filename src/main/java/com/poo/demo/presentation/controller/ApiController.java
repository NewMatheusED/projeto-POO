package com.poo.demo.presentation.controller;

import com.poo.demo.application.service.ExternalApiService;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para expor endpoints da aplicação
 * Segue o princípio de responsabilidade única (SRP)
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ApiController {

    private final ExternalApiService externalApiService;

    @Autowired
    public ApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    /**
     * Endpoint para consultar API externa
     * @param url URL da API externa
     * @return Dados da API externa
     */
    @GetMapping("/consulta")
    public ResponseEntity<ApiResponse<Object>> consultarApiExterna(
            @RequestParam String url,
            @RequestParam(defaultValue = "java.lang.Object") String responseType) {
        
        try {
            Class<?> clazz = Class.forName(responseType);
            ApiResponse<?> response = externalApiService.consultarApiExterna(url, clazz);
            return ResponseEntity.ok((ApiResponse<Object>) response);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Tipo de resposta inválido: " + e.getMessage(), 400));
        }
    }

    /**
     * Endpoint para enviar dados para API externa
     * @param url URL da API externa
     * @param request Dados da requisição
     * @param responseType Tipo da resposta esperada
     * @return Resposta da API externa
     */
    @PostMapping("/envio")
    public ResponseEntity<ApiResponse<Object>> enviarParaApiExterna(
            @RequestParam String url,
            @RequestBody Object request,
            @RequestParam(defaultValue = "java.lang.Object") String responseType) {
        
        try {
            Class<?> clazz = Class.forName(responseType);
            ApiResponse<?> response = externalApiService.enviarParaApiExterna(url, request, clazz);
            return ResponseEntity.ok((ApiResponse<Object>) response);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Tipo de resposta inválido: " + e.getMessage(), 400));
        }
    }

    /**
     * Endpoint de health check
     * @return Status da aplicação
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponse.success("Aplicação funcionando normalmente"));
    }
}
