package com.poo.demo.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisições de criação/atualização de Senador.
 * Baseado exatamente no SenadorDto existente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenadorRequestDto {
    
    @NotBlank(message = "Código é obrigatório")
    @Size(max = 100, message = "Código deve ter no máximo 100 caracteres")
    private String codigo;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
    private String nome;
    
    @Size(max = 500, message = "Nome completo deve ter no máximo 500 caracteres")
    private String nomeCompleto;
    
    @Size(max = 10, message = "Sexo deve ter no máximo 10 caracteres")
    private String sexo;
    
    @Size(max = 100, message = "Partido deve ter no máximo 100 caracteres")
    private String partido;
    
    @Size(max = 2, message = "UF deve ter no máximo 2 caracteres")
    private String uf;
    
    @Email(message = "Email deve ser válido")
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    private String email;
    
    @Size(max = 500, message = "URL da foto deve ter no máximo 500 caracteres")
    private String urlFoto;
    
    @Size(max = 500, message = "URL da página deve ter no máximo 500 caracteres")
    private String urlPagina;
    
    @Size(max = 100, message = "Sigla do partido deve ter no máximo 100 caracteres")
    private String siglaPartido;
    
    @Size(max = 2, message = "UF parlamentar deve ter no máximo 2 caracteres")
    private String ufParlamentar;
    
    @Size(max = 100, message = "Membro mesa deve ter no máximo 100 caracteres")
    private String membroMesa;
    
    @Size(max = 100, message = "Membro liderança deve ter no máximo 100 caracteres")
    private String membroLideranca;
    
    private String bloco; // Campo JSON
    
    @Size(max = 100, message = "Código do mandato deve ter no máximo 100 caracteres")
    private String codigoMandato;
    
    @Size(max = 2, message = "UF parlamentar mandato deve ter no máximo 2 caracteres")
    private String ufParlamentarMandato;
    
    @Size(max = 500, message = "Descrição participação deve ter no máximo 500 caracteres")
    private String descricaoParticipacao;
    
    @Size(max = 100, message = "Primeira legislatura número deve ter no máximo 100 caracteres")
    private String primeiraLegislaturaNumero;
    
    @Size(max = 100, message = "Primeira legislatura data início deve ter no máximo 100 caracteres")
    private String primeiraLegislaturaDataInicio;
    
    @Size(max = 100, message = "Primeira legislatura data fim deve ter no máximo 100 caracteres")
    private String primeiraLegislaturaDataFim;
    
    @Size(max = 100, message = "Segunda legislatura número deve ter no máximo 100 caracteres")
    private String segundaLegislaturaNumero;
    
    @Size(max = 100, message = "Segunda legislatura data início deve ter no máximo 100 caracteres")
    private String segundaLegislaturaDataInicio;
    
    @Size(max = 100, message = "Segunda legislatura data fim deve ter no máximo 100 caracteres")
    private String segundaLegislaturaDataFim;
    
    private String suplentes; // Campo JSON
    
    private String exercicios; // Campo JSON
}
