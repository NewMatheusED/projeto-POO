package com.poo.demo.presentation.controller;
import com.poo.demo.application.service.ProcessoApiService;
import com.poo.demo.domain.dto.ProcessoDto;
import com.poo.demo.domain.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller específico para expor endpoints da API de Processos.
 * Estende BaseApiController para eliminar duplicação de código.
 */
@RestController
@RequestMapping("/processo")
@Tag(name = "Processos", description = "Endpoints para consulta de processos legislativos")
public class ProcessoController extends BaseApiController {

    private final ProcessoApiService processoApiService;

    @Autowired
    public ProcessoController(ProcessoApiService processoApiService) {
        this.processoApiService = processoApiService;
    }

    /**
     * Busca as emendas de um processo específico.
     * Utiliza métodos da classe abstrata para padronização.
     * @param codigo Código do processo
     * @return Array de ProcessoDto com as emendas do processo
     */
    @Operation(summary = "Buscar Emendas do Processo", description = "Retorna as emendas de um processo legislativo específico")
    @GetMapping("/{codigo}")
    public ResponseEntity<ApiResponse<ProcessoDto[]>> buscarEmendasProcesso(
            @Parameter(description = "Código do processo", required = true) @PathVariable String codigo) {
        logRequest("/processo/" + codigo, "GET", "codigo=" + codigo);
        ApiResponse<ProcessoDto[]> response = processoApiService.buscarEmendasProcesso(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    /**
     * Busca o JSON bruto de um processo específico.
     * Utiliza métodos da classe abstrata para padronização.
     * @param codigo Código do processo
     * @return JSON válido da resposta (XML convertido para JSON)
     */
    @Operation(summary = "Buscar Processo (JSON Bruto)", description = "Retorna dados brutos de um processo legislativo em formato JSON")
    @GetMapping("/{codigo}/json-bruto")
    public ResponseEntity<ApiResponse<Object>> buscarProcessoBruto(
            @Parameter(description = "Código do processo", required = true) @PathVariable String codigo) {
        logRequest("/processo/" + codigo + "/json-bruto", "GET", "codigo=" + codigo);
        ApiResponse<Object> response = processoApiService.buscarProcessoBruto(codigo);
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
        return "Processos";
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
