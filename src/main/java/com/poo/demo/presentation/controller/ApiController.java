package com.poo.demo.presentation.controller;

import com.poo.demo.domain.entity.ApiResponse;
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


    /**
     * Endpoint de health check
     * @return Status da aplicação
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponse.success("Aplicação funcionando normalmente"));
    }
}
