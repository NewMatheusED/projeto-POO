package com.poo.demo.presentation.controller;

import com.poo.demo.application.service.SenadoApiService;
import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller específico para expor endpoints da API do Senado
 */
@RestController
@RequestMapping("/api/v1/senado")
@CrossOrigin(origins = "*")
public class SenadoController {

    private final SenadoApiService senadoApiService;

    @Autowired
    public SenadoController(SenadoApiService senadoApiService) {
        this.senadoApiService = senadoApiService;
    }

    @GetMapping("/senadores")
    public ResponseEntity<ApiResponse<SenadorDto[]>> buscarSenadores() {
        return ResponseEntity.ok(senadoApiService.buscarSenadores());
    }

    @GetMapping("/json-bruto-senadores")
    public ResponseEntity<ApiResponse<Object>> buscarJsonBruto() {
        return ResponseEntity.ok(senadoApiService.buscarJsonBruto());
    }
}
