package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessoDto {
    
    // Classe interna para Decisao
    public static class Decisao {
        private String data;
        private Integer idTipo;
        private String descricaoTipo;
        private Integer codigoColegiado;
        private String casa;
        private String siglaColegiado;
        private String nomeColegiado;
        
        @JsonProperty("data")
        public String getData() { return data; }
        public void setData(String data) { this.data = data; }
        
        @JsonProperty("idTipo")
        public Integer getIdTipo() { return idTipo; }
        public void setIdTipo(Integer idTipo) { this.idTipo = idTipo; }
        
        @JsonProperty("descricaoTipo")
        public String getDescricaoTipo() { return descricaoTipo; }
        public void setDescricaoTipo(String descricaoTipo) { this.descricaoTipo = descricaoTipo; }
        
        @JsonProperty("codigoColegiado")
        public Integer getCodigoColegiado() { return codigoColegiado; }
        public void setCodigoColegiado(Integer codigoColegiado) { this.codigoColegiado = codigoColegiado; }
        
        @JsonProperty("casa")
        public String getCasa() { return casa; }
        public void setCasa(String casa) { this.casa = casa; }
        
        @JsonProperty("siglaColegiado")
        public String getSiglaColegiado() { return siglaColegiado; }
        public void setSiglaColegiado(String siglaColegiado) { this.siglaColegiado = siglaColegiado; }
        
        @JsonProperty("nomeColegiado")
        public String getNomeColegiado() { return nomeColegiado; }
        public void setNomeColegiado(String nomeColegiado) { this.nomeColegiado = nomeColegiado; }
    }
    
    // Classe interna para Subemenda
    public static class Subemenda {
        private Integer id;
        private Integer idDocumentoEmenda;
        private String urlDocumentoEmenda;
        private String descricaoDocumentoEmenda;
        private Integer idCiEmenda;
        private Integer idCiEmendado;
        private Integer idProcesso;
        private String dataApresentacao;
        private Integer codigoColegiado;
        private String casa;
        private String siglaColegiado;
        private String nomeColegiado;
        private String autoria;
        private String numero;
        private String identificacao;
        private String tipo;
        private String turnoApresentacao;
        private List<Decisao> decisoes;
        private List<Subemenda> subemendas;
        
        @JsonProperty("id")
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        
        @JsonProperty("idDocumentoEmenda")
        public Integer getIdDocumentoEmenda() { return idDocumentoEmenda; }
        public void setIdDocumentoEmenda(Integer idDocumentoEmenda) { this.idDocumentoEmenda = idDocumentoEmenda; }
        
        @JsonProperty("urlDocumentoEmenda")
        public String getUrlDocumentoEmenda() { return urlDocumentoEmenda; }
        public void setUrlDocumentoEmenda(String urlDocumentoEmenda) { this.urlDocumentoEmenda = urlDocumentoEmenda; }
        
        @JsonProperty("descricaoDocumentoEmenda")
        public String getDescricaoDocumentoEmenda() { return descricaoDocumentoEmenda; }
        public void setDescricaoDocumentoEmenda(String descricaoDocumentoEmenda) { this.descricaoDocumentoEmenda = descricaoDocumentoEmenda; }
        
        @JsonProperty("idCiEmenda")
        public Integer getIdCiEmenda() { return idCiEmenda; }
        public void setIdCiEmenda(Integer idCiEmenda) { this.idCiEmenda = idCiEmenda; }
        
        @JsonProperty("idCiEmendado")
        public Integer getIdCiEmendado() { return idCiEmendado; }
        public void setIdCiEmendado(Integer idCiEmendado) { this.idCiEmendado = idCiEmendado; }
        
        @JsonProperty("idProcesso")
        public Integer getIdProcesso() { return idProcesso; }
        public void setIdProcesso(Integer idProcesso) { this.idProcesso = idProcesso; }
        
        @JsonProperty("dataApresentacao")
        public String getDataApresentacao() { return dataApresentacao; }
        public void setDataApresentacao(String dataApresentacao) { this.dataApresentacao = dataApresentacao; }
        
        @JsonProperty("codigoColegiado")
        public Integer getCodigoColegiado() { return codigoColegiado; }
        public void setCodigoColegiado(Integer codigoColegiado) { this.codigoColegiado = codigoColegiado; }
        
        @JsonProperty("casa")
        public String getCasa() { return casa; }
        public void setCasa(String casa) { this.casa = casa; }
        
        @JsonProperty("siglaColegiado")
        public String getSiglaColegiado() { return siglaColegiado; }
        public void setSiglaColegiado(String siglaColegiado) { this.siglaColegiado = siglaColegiado; }
        
        @JsonProperty("nomeColegiado")
        public String getNomeColegiado() { return nomeColegiado; }
        public void setNomeColegiado(String nomeColegiado) { this.nomeColegiado = nomeColegiado; }
        
        @JsonProperty("autoria")
        public String getAutoria() { return autoria; }
        public void setAutoria(String autoria) { this.autoria = autoria; }
        
        @JsonProperty("numero")
        public String getNumero() { return numero; }
        public void setNumero(String numero) { this.numero = numero; }
        
        @JsonProperty("identificacao")
        public String getIdentificacao() { return identificacao; }
        public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
        
        @JsonProperty("tipo")
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        
        @JsonProperty("turnoApresentacao")
        public String getTurnoApresentacao() { return turnoApresentacao; }
        public void setTurnoApresentacao(String turnoApresentacao) { this.turnoApresentacao = turnoApresentacao; }
        
        @JsonProperty("decisoes")
        public List<Decisao> getDecisoes() { return decisoes; }
        public void setDecisoes(List<Decisao> decisoes) { this.decisoes = decisoes; }
        
        @JsonProperty("subemendas")
        public List<Subemenda> getSubemendas() { return subemendas; }
        public void setSubemendas(List<Subemenda> subemendas) { this.subemendas = subemendas; }
    }
    
    // Propriedades principais da Emenda
    private Integer id;
    private Integer idDocumentoEmenda;
    private String urlDocumentoEmenda;
    private String descricaoDocumentoEmenda;
    private Integer idCiEmenda;
    private Integer idCiEmendado;
    private Integer idProcesso;
    private String dataApresentacao;
    private Integer codigoColegiado;
    private String casa;
    private String siglaColegiado;
    private String nomeColegiado;
    private String autoria;
    private String numero;
    private String identificacao;
    private String tipo;
    private String turnoApresentacao;
    private List<Decisao> decisoes;
    private List<Subemenda> subemendas;
    
    // Getters e Setters para as propriedades principais
    @JsonProperty("id")
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    @JsonProperty("idDocumentoEmenda")
    public Integer getIdDocumentoEmenda() { return idDocumentoEmenda; }
    public void setIdDocumentoEmenda(Integer idDocumentoEmenda) { this.idDocumentoEmenda = idDocumentoEmenda; }
    
    @JsonProperty("urlDocumentoEmenda")
    public String getUrlDocumentoEmenda() { return urlDocumentoEmenda; }
    public void setUrlDocumentoEmenda(String urlDocumentoEmenda) { this.urlDocumentoEmenda = urlDocumentoEmenda; }
    
    @JsonProperty("descricaoDocumentoEmenda")
    public String getDescricaoDocumentoEmenda() { return descricaoDocumentoEmenda; }
    public void setDescricaoDocumentoEmenda(String descricaoDocumentoEmenda) { this.descricaoDocumentoEmenda = descricaoDocumentoEmenda; }
    
    @JsonProperty("idCiEmenda")
    public Integer getIdCiEmenda() { return idCiEmenda; }
    public void setIdCiEmenda(Integer idCiEmenda) { this.idCiEmenda = idCiEmenda; }
    
    @JsonProperty("idCiEmendado")
    public Integer getIdCiEmendado() { return idCiEmendado; }
    public void setIdCiEmendado(Integer idCiEmendado) { this.idCiEmendado = idCiEmendado; }
    
    @JsonProperty("idProcesso")
    public Integer getIdProcesso() { return idProcesso; }
    public void setIdProcesso(Integer idProcesso) { this.idProcesso = idProcesso; }
    
    @JsonProperty("dataApresentacao")
    public String getDataApresentacao() { return dataApresentacao; }
    public void setDataApresentacao(String dataApresentacao) { this.dataApresentacao = dataApresentacao; }
    
    @JsonProperty("codigoColegiado")
    public Integer getCodigoColegiado() { return codigoColegiado; }
    public void setCodigoColegiado(Integer codigoColegiado) { this.codigoColegiado = codigoColegiado; }
    
    @JsonProperty("casa")
    public String getCasa() { return casa; }
    public void setCasa(String casa) { this.casa = casa; }
    
    @JsonProperty("siglaColegiado")
    public String getSiglaColegiado() { return siglaColegiado; }
    public void setSiglaColegiado(String siglaColegiado) { this.siglaColegiado = siglaColegiado; }
    
    @JsonProperty("nomeColegiado")
    public String getNomeColegiado() { return nomeColegiado; }
    public void setNomeColegiado(String nomeColegiado) { this.nomeColegiado = nomeColegiado; }
    
    @JsonProperty("autoria")
    public String getAutoria() { return autoria; }
    public void setAutoria(String autoria) { this.autoria = autoria; }
    
    @JsonProperty("numero")
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    @JsonProperty("identificacao")
    public String getIdentificacao() { return identificacao; }
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
    
    @JsonProperty("tipo")
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    @JsonProperty("turnoApresentacao")
    public String getTurnoApresentacao() { return turnoApresentacao; }
    public void setTurnoApresentacao(String turnoApresentacao) { this.turnoApresentacao = turnoApresentacao; }
    
    @JsonProperty("decisoes")
    public List<Decisao> getDecisoes() { return decisoes; }
    public void setDecisoes(List<Decisao> decisoes) { this.decisoes = decisoes; }
    
    @JsonProperty("subemendas")
    public List<Subemenda> getSubemendas() { return subemendas; }
    public void setSubemendas(List<Subemenda> subemendas) { this.subemendas = subemendas; }
}
