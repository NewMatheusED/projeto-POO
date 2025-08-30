package com.poo.demo.presentation.controller;
import com.poo.demo.application.service.ProcessoApiService;
import com.poo.demo.domain.dto.ProcessoDto;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller específico para expor endpoints da API de Processos
 */
@RestController
@RequestMapping("/api/v1/processo")
@CrossOrigin(origins = "*")
public class ProcessoController {

    private final ProcessoApiService processoApiService;

    @Autowired
    public ProcessoController(ProcessoApiService processoApiService) {
        this.processoApiService = processoApiService;
    }

    /**
     * Busca as emendas de um processo específico
     * @param codigo Código do processo
     * @return Array de ProcessoDto com as emendas do processo
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<ApiResponse<ProcessoDto[]>> buscarEmendasProcesso(@PathVariable String codigo) {
        return ResponseEntity.ok(processoApiService.buscarEmendasProcesso(codigo));
    }

    /**
     * Busca o JSON bruto de um processo específico
     * @param codigo Código do processo
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    @GetMapping("/{codigo}/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarProcessoBruto(@PathVariable String codigo) {
        return ResponseEntity.ok(processoApiService.buscarProcessoBruto(codigo));
    }
}
