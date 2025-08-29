package com.poo.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para respostas de Senador.
 * Baseado exatamente no SenadorDto existente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenadorResponseDto {
    
    private Long id;
    private String codigo;
    private String nome;
    private String nomeCompleto;
    private String sexo;
    private String partido;
    private String uf;
    private String email;
    private String urlFoto;
    private String urlPagina;
    private String siglaPartido;
    private String ufParlamentar;
    private String membroMesa;
    private String membroLideranca;
    private String bloco;
    private String codigoMandato;
    private String ufParlamentarMandato;
    private String descricaoParticipacao;
    private String primeiraLegislaturaNumero;
    private String primeiraLegislaturaDataInicio;
    private String primeiraLegislaturaDataFim;
    private String segundaLegislaturaNumero;
    private String segundaLegislaturaDataInicio;
    private String segundaLegislaturaDataFim;
    private String suplentes;
    private String exercicios;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
