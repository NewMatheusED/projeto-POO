package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para representar uma decisão de emenda.
 * Contém informações sobre aprovação/rejeição em colegiados.
 */
public class DecisaoDto {
    
    @JsonProperty("data")
    private String data;
    
    @JsonProperty("idTipo")
    private Integer idTipo;
    
    @JsonProperty("descricaoTipo")
    private String descricaoTipo;
    
    @JsonProperty("codigoColegiado")
    private Integer codigoColegiado;
    
    @JsonProperty("casa")
    private String casa;
    
    @JsonProperty("siglaColegiado")
    private String siglaColegiado;
    
    @JsonProperty("nomeColegiado")
    private String nomeColegiado;
    
    // Construtor padrão
    public DecisaoDto() {}
    
    // Construtor com todos os campos
    public DecisaoDto(String data, Integer idTipo, String descricaoTipo, 
                     Integer codigoColegiado, String casa, String siglaColegiado, String nomeColegiado) {
        this.data = data;
        this.idTipo = idTipo;
        this.descricaoTipo = descricaoTipo;
        this.codigoColegiado = codigoColegiado;
        this.casa = casa;
        this.siglaColegiado = siglaColegiado;
        this.nomeColegiado = nomeColegiado;
    }
    
    // Getters e Setters
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public Integer getIdTipo() {
        return idTipo;
    }
    
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    
    public String getDescricaoTipo() {
        return descricaoTipo;
    }
    
    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
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
}
