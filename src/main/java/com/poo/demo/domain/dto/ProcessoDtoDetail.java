package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para representar um processo legislativo.
 * Contém apenas os campos essenciais para identificação e informações básicas do processo.
 */
public class ProcessoDtoDetail {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("codigoMateria")
    private Integer codigoMateria;
    
    @JsonProperty("identificacao")
    private String identificacao;
    
    @JsonProperty("objetivo")
    private String objetivo;
    
    @JsonProperty("casaIdentificadora")
    private String casaIdentificadora;
    
    @JsonProperty("enteIdentificador")
    private String enteIdentificador;
    
    @JsonProperty("tipoConteudo")
    private String tipoConteudo;
    
    @JsonProperty("ementa")
    private String ementa;
    
    @JsonProperty("tipoDocumento")
    private String tipoDocumento;
    
    @JsonProperty("dataApresentacao")
    private String dataApresentacao;
    
    @JsonProperty("autoria")
    private String autoria;
    
    @JsonProperty("tramitando")
    private String tramitando;
    
    @JsonProperty("ultimaInformacaoAtualizada")
    private String ultimaInformacaoAtualizada;
    
    @JsonProperty("dataUltimaAtualizacao")
    private String dataUltimaAtualizacao;
    
    @JsonProperty("urlDocumento")
    private String urlDocumento;
    
    // Construtor padrão
    public ProcessoDtoDetail() {}
    
    // Construtor com todos os campos
    public ProcessoDtoDetail(Long id, Integer codigoMateria, String identificacao, String objetivo,
                      String casaIdentificadora, String enteIdentificador, String tipoConteudo,
                      String ementa, String tipoDocumento, String dataApresentacao,
                      String autoria, String tramitando, String ultimaInformacaoAtualizada,
                      String dataUltimaAtualizacao, String urlDocumento) {
        this.id = id;
        this.codigoMateria = codigoMateria;
        this.identificacao = identificacao;
        this.objetivo = objetivo;
        this.casaIdentificadora = casaIdentificadora;
        this.enteIdentificador = enteIdentificador;
        this.tipoConteudo = tipoConteudo;
        this.ementa = ementa;
        this.tipoDocumento = tipoDocumento;
        this.dataApresentacao = dataApresentacao;
        this.autoria = autoria;
        this.tramitando = tramitando;
        this.ultimaInformacaoAtualizada = ultimaInformacaoAtualizada;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.urlDocumento = urlDocumento;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getCodigoMateria() {
        return codigoMateria;
    }
    
    public void setCodigoMateria(Integer codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
    
    public String getIdentificacao() {
        return identificacao;
    }
    
    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
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
    
    public String getEnteIdentificador() {
        return enteIdentificador;
    }
    
    public void setEnteIdentificador(String enteIdentificador) {
        this.enteIdentificador = enteIdentificador;
    }
    
    public String getTipoConteudo() {
        return tipoConteudo;
    }
    
    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }
    
    public String getEmenta() {
        return ementa;
    }
    
    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getDataApresentacao() {
        return dataApresentacao;
    }
    
    public void setDataApresentacao(String dataApresentacao) {
        this.dataApresentacao = dataApresentacao;
    }
    
    public String getAutoria() {
        return autoria;
    }
    
    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }
    
    public String getTramitando() {
        return tramitando;
    }
    
    public void setTramitando(String tramitando) {
        this.tramitando = tramitando;
    }
    
    public String getUltimaInformacaoAtualizada() {
        return ultimaInformacaoAtualizada;
    }
    
    public void setUltimaInformacaoAtualizada(String ultimaInformacaoAtualizada) {
        this.ultimaInformacaoAtualizada = ultimaInformacaoAtualizada;
    }
    
    public String getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }
    
    public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }
    
    public String getUrlDocumento() {
        return urlDocumento;
    }
    
    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }
}
