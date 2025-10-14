package com.poo.demo.presentation.controller;
import com.poo.demo.application.service.VotacoesApiService; 
import com.poo.demo.domain.dto.VotacoesProjetoDto;
import com.poo.demo.domain.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller específico para expor endpoints da API de Votações
 */
@RestController
@RequestMapping("/v1/votacoes")
@Tag(name = "Votações", description = "Endpoints para consulta de votações")
public class VotacoesController extends BaseApiController {

    private final VotacoesApiService votacoesApiService;

    @Autowired
    public VotacoesController(VotacoesApiService votacoesApiService) {
        this.votacoesApiService = votacoesApiService;
    }

    /**
     * Busca todos os votos de um processo específico
     * @param sigla Sigla do processo
     * @param numero Número do processo
     * @param ano Ano do processo
     * @return Array de ProcessoDto com os votos do processo
     */
    @Operation(summary = "Buscar Votos do Processo", description = "Retorna todos os votos de um processo legislativo específico")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Votos do processo retornados com sucesso")
    })
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<VotacoesProjetoDto>> buscarVotosProcesso(
            @Parameter(description = "Sigla do processo", required = true) @RequestParam String sigla,
            @Parameter(description = "Número do processo", required = true) @RequestParam String numero,
            @Parameter(description = "Ano do processo", required = true) @RequestParam String ano) {
        ApiResponse<VotacoesProjetoDto> response = votacoesApiService.buscarVotosProcesso(sigla, numero, ano);
        return response.isSuccess() ? 
            createSuccessResponse(response.getData()) : 
            createNotFoundResponse(response.getMessage());
    }

    @Override
    public String getControllerName() {
        return "Votacoes";
    }

    @Override
    public String getApiVersion() {
        return "v1.0";
    }
}
