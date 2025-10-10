package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO para representar um processo legislativo com emendas e subemendas.
 * Contém todos os campos da nova estrutura da API do Senado.
 */
public class ProcessoDto {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("idDocumentoEmenda")
    private Long idDocumentoEmenda;
    
    @JsonProperty("urlDocumentoEmenda")
    private String urlDocumentoEmenda;
    
    @JsonProperty("descricaoDocumentoEmenda")
    private String descricaoDocumentoEmenda;
    
    @JsonProperty("idCiEmenda")
    private Long idCiEmenda;
    
    @JsonProperty("idCiEmendado")
    private Long idCiEmendado;
    
    @JsonProperty("idProcesso")
    private Long idProcesso;
    
    @JsonProperty("dataApresentacao")
    private String dataApresentacao;
    
    @JsonProperty("codigoColegiado")
    private Integer codigoColegiado;
    
    @JsonProperty("casa")
    private String casa;
    
    @JsonProperty("siglaColegiado")
    private String siglaColegiado;
    
    @JsonProperty("nomeColegiado")
    private String nomeColegiado;
    
    @JsonProperty("autoria")
    private String autoria;
    
    @JsonProperty("numero")
    private String numero;
    
    @JsonProperty("identificacao")
    private String identificacao;
    
    @JsonProperty("tipo")
    private String tipo;
    
    @JsonProperty("turnoApresentacao")
    private String turnoApresentacao;
    
    @JsonProperty("decisoes")
    private List<DecisaoDto> decisoes;
    
    @JsonProperty("subemendas")
    private List<ProcessoDto> subemendas;
    
    // Construtor padrão
    public ProcessoDto() {}
    
    // Construtor com campos principais
    public ProcessoDto(Long id, Long idDocumentoEmenda, String urlDocumentoEmenda, 
                      String descricaoDocumentoEmenda, Long idCiEmenda, Long idCiEmendado,
                      Long idProcesso, String dataApresentacao, Integer codigoColegiado,
                      String casa, String siglaColegiado, String nomeColegiado,
                      String autoria, String numero, String identificacao, String tipo,
                      String turnoApresentacao) {
        this.id = id;
        this.idDocumentoEmenda = idDocumentoEmenda;
        this.urlDocumentoEmenda = urlDocumentoEmenda;
        this.descricaoDocumentoEmenda = descricaoDocumentoEmenda;
        this.idCiEmenda = idCiEmenda;
        this.idCiEmendado = idCiEmendado;
        this.idProcesso = idProcesso;
        this.dataApresentacao = dataApresentacao;
        this.codigoColegiado = codigoColegiado;
        this.casa = casa;
        this.siglaColegiado = siglaColegiado;
        this.nomeColegiado = nomeColegiado;
        this.autoria = autoria;
        this.numero = numero;
        this.identificacao = identificacao;
        this.tipo = tipo;
        this.turnoApresentacao = turnoApresentacao;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdDocumentoEmenda() {
        return idDocumentoEmenda;
    }
    
    public void setIdDocumentoEmenda(Long idDocumentoEmenda) {
        this.idDocumentoEmenda = idDocumentoEmenda;
    }
    
    public String getUrlDocumentoEmenda() {
        return urlDocumentoEmenda;
    }
    
    public void setUrlDocumentoEmenda(String urlDocumentoEmenda) {
        this.urlDocumentoEmenda = urlDocumentoEmenda;
    }
    
    public String getDescricaoDocumentoEmenda() {
        return descricaoDocumentoEmenda;
    }
    
    public void setDescricaoDocumentoEmenda(String descricaoDocumentoEmenda) {
        this.descricaoDocumentoEmenda = descricaoDocumentoEmenda;
    }
    
    public Long getIdCiEmenda() {
        return idCiEmenda;
    }
    
    public void setIdCiEmenda(Long idCiEmenda) {
        this.idCiEmenda = idCiEmenda;
    }
    
    public Long getIdCiEmendado() {
        return idCiEmendado;
    }
    
    public void setIdCiEmendado(Long idCiEmendado) {
        this.idCiEmendado = idCiEmendado;
    }
    
    public Long getIdProcesso() {
        return idProcesso;
    }
    
    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }
    
    public String getDataApresentacao() {
        return dataApresentacao;
    }
    
    public void setDataApresentacao(String dataApresentacao) {
        this.dataApresentacao = dataApresentacao;
    }
    
    public Integer getCodigoColegiado() {
        return codigoColegiado;
    }
    
    public void setCodigoColegiado(Integer codigoColegiado) {
        this.codigoColegiado = codigoColegiado;
    }
    
    public String getCasa() {
        return casa;
    }
    
    public void setCasa(String casa) {
        this.casa = casa;
    }
    
    public String getSiglaColegiado() {
        return siglaColegiado;
    }
    
    public void setSiglaColegiado(String siglaColegiado) {
        this.siglaColegiado = siglaColegiado;
    }
    
    public String getNomeColegiado() {
        return nomeColegiado;
    }
    
    public void setNomeColegiado(String nomeColegiado) {
        this.nomeColegiado = nomeColegiado;
    }
    
    public String getAutoria() {
        return autoria;
    }
    
    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getIdentificacao() {
        return identificacao;
    }
    
    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTurnoApresentacao() {
        return turnoApresentacao;
    }
    
    public void setTurnoApresentacao(String turnoApresentacao) {
        this.turnoApresentacao = turnoApresentacao;
    }
    
    public List<DecisaoDto> getDecisoes() {
        return decisoes;
    }
    
    public void setDecisoes(List<DecisaoDto> decisoes) {
        this.decisoes = decisoes;
    }
    
    public List<ProcessoDto> getSubemendas() {
        return subemendas;
    }
    
    public void setSubemendas(List<ProcessoDto> subemendas) {
        this.subemendas = subemendas;
    }
}
