package com.poo.demo.presentation.controller;
import com.poo.demo.application.service.ProcessoApiService;
import com.poo.demo.domain.dto.ProcessoDto;
import com.poo.demo.domain.dto.ProcessoDtoDetail;
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
@RequestMapping("/v1/processo")
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
     * @return ProcessoDto com as emendas do processo
     */
    @Operation(summary = "Buscar Emendas do Processo", description = "Retorna as emendas de um processo legislativo específico")
    @GetMapping("/{codigo}")
    public ResponseEntity<ApiResponse<ProcessoDtoDetail>> buscarEmendasProcesso(
            @Parameter(description = "Código do processo", required = true) @PathVariable String codigo) {
        logRequest("/processo/" + codigo, "GET", "codigo=" + codigo);
        ApiResponse<ProcessoDtoDetail> response = processoApiService.buscarEmendasProcesso(codigo);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    /**
     * Busca todos os processos legislativos com filtro opcional por identificação.
     * @param filtro Filtro para buscar na identificação (opcional)
     * @return Array de ProcessoDto com os processos legislativos filtrados
     */
    @Operation(summary = "Buscar Emendas do Processo Geral", description = "Retorna as emendas de todos os processos legislativos com filtro opcional por identificação")
    @GetMapping("/emendas/geral")
    public ResponseEntity<ApiResponse<ProcessoDto[]>> buscarProcessosGeral(
            @Parameter(description = "Filtro para buscar na identificação (ex: PLP, PL, PEC)", required = false) 
            @RequestParam(value = "filtro", required = false) String filtro) {
        
        String endpoint = "/processo/emendas/geral" + (filtro != null ? "?filtro=" + filtro : "");
        logRequest(endpoint, "GET", filtro != null ? "filtro=" + filtro : null);
        
        ApiResponse<ProcessoDto[]> response = processoApiService.buscarProcessosGeral(filtro);
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
