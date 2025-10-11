package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para representar um processo legislativo com emendas e subemendas.
 * Contém todos os campos da nova estrutura da API do Senado.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessoDtoDetail {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("identificacao")
    private String identificacao;
    
    @JsonProperty("sigla")
    private String sigla;
    
    @JsonProperty("descricaoSigla")
    private String descricaoSigla;
    
    @JsonProperty("numero")
    private String numero;
    
    @JsonProperty("ano")
    private Long ano;
    
    @JsonProperty("objetivo")
    private String objetivo;
    
    @JsonProperty("casaIdentificadora")
    private String casaIdentificadora;
    
    @JsonProperty("siglaEnteIdentificador")
    private String siglaEnteIdentificador;
    
    public ProcessoDtoDetail() {}

    // Construtor com campos principais
    public ProcessoDtoDetail(Long id, String identificacao, String sigla, String descricaoSigla, String numero, Long ano, String objetivo, String casaIdentificadora, String siglaEnteIdentificador) {
        this.id = id;
        this.identificacao = identificacao;
        this.sigla = sigla;
        this.descricaoSigla = descricaoSigla;
        this.numero = numero;
        this.ano = ano;
        this.objetivo = objetivo;
        this.casaIdentificadora = casaIdentificadora;
        this.siglaEnteIdentificador = siglaEnteIdentificador;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIdentificacao() {
        return identificacao;
    }
    
    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getDescricaoSigla() {
        return descricaoSigla;
    }
    
    public void setDescricaoSigla(String descricaoSigla) {
        this.descricaoSigla = descricaoSigla;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public Long getAno() {
        return ano;
    }
    
    public void setAno(Long ano) {
        this.ano = ano;
    }
    
    public String getObjetivo() {
        return objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    
    public String getCasaIdentificadora() {
        return casaIdentificadora;
    }
    
    public void setCasaIdentificadora(String casaIdentificadora) {
        this.casaIdentificadora = casaIdentificadora;
    }
    
    public String getSiglaEnteIdentificador() {
        return siglaEnteIdentificador;
    }
    
    public void setSiglaEnteIdentificador(String siglaEnteIdentificador) {
        this.siglaEnteIdentificador = siglaEnteIdentificador;
    }
}
