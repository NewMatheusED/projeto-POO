package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SenadorDto {

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

    private Object bloco;
    
    private String codigoMandato;
    private String ufParlamentarMandato;
    private String descricaoParticipacao;
    
    private String primeiraLegislaturaNumero;
    private String primeiraLegislaturaDataInicio;
    private String primeiraLegislaturaDataFim;
    
    private String segundaLegislaturaNumero;
    private String segundaLegislaturaDataInicio;
    private String segundaLegislaturaDataFim;
    
    private Object suplentes;
    
    private Object exercicios;

    @JsonProperty("IdentificacaoParlamentar")
    private void unpackIdentificacao(Map<String, Object> key) {
        this.codigo = (String) key.get("CodigoParlamentar");
        this.nome = (String) key.get("NomeParlamentar");
        this.nomeCompleto = (String) key.get("NomeCompletoParlamentar");
        this.sexo = (String) key.get("SexoParlamentar");
        this.partido = (String) key.get("SiglaPartidoParlamentar");
        this.uf = (String) key.get("UfParlamentar");
        this.email = (String) key.get("EmailParlamentar");
        this.urlFoto = (String) key.get("UrlFotoParlamentar");
        this.urlPagina = (String) key.get("UrlPaginaParlamentar");
        this.siglaPartido = (String) key.get("SiglaPartidoParlamentar");
        this.ufParlamentar = (String) key.get("UfParlamentar");
        this.membroMesa = (String) key.get("MembroMesa");
        this.membroLideranca = (String) key.get("MembroLideranca");

        this.bloco = key.get("Bloco");
    }

    @JsonProperty("Mandato")
    @SuppressWarnings("unchecked")
    private void setMandato(Map<String, Object> mandato) {
        if (mandato != null) {
            this.codigoMandato = (String) mandato.get("CodigoMandato");
            this.ufParlamentarMandato = (String) mandato.get("UfParlamentar");
            this.descricaoParticipacao = (String) mandato.get("DescricaoParticipacao");
            
            Object primeiraLeg = mandato.get("PrimeiraLegislaturaDoMandato");
            if (primeiraLeg instanceof Map) {
                Map<String, Object> primeira = (Map<String, Object>) primeiraLeg;
                this.primeiraLegislaturaNumero = (String) primeira.get("NumeroLegislatura");
                this.primeiraLegislaturaDataInicio = (String) primeira.get("DataInicio");
                this.primeiraLegislaturaDataFim = (String) primeira.get("DataFim");
            }
            
            Object segundaLeg = mandato.get("SegundaLegislaturaDoMandato");
            if (segundaLeg instanceof Map) {
                Map<String, Object> segunda = (Map<String, Object>) segundaLeg;
                this.segundaLegislaturaNumero = (String) segunda.get("NumeroLegislatura");
                this.segundaLegislaturaDataInicio = (String) segunda.get("DataInicio");
                this.segundaLegislaturaDataFim = (String) segunda.get("DataFim");
            }
            
            this.suplentes = mandato.get("Suplentes");
            this.exercicios = mandato.get("Exercicios");
        }
    }


    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }
    
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    
    public String getUrlPagina() { return urlPagina; }
    public void setUrlPagina(String urlPagina) { this.urlPagina = urlPagina; }

    public String getCodigoMandato() { return codigoMandato; }
    public void setCodigoMandato(String codigoMandato) { this.codigoMandato = codigoMandato; }
    
    public String getUfParlamentarMandato() { return ufParlamentarMandato; }
    public void setUfParlamentarMandato(String ufParlamentarMandato) { this.ufParlamentarMandato = ufParlamentarMandato; }
    
    public String getDescricaoParticipacao() { return descricaoParticipacao; }
    public void setDescricaoParticipacao(String descricaoParticipacao) { this.descricaoParticipacao = descricaoParticipacao; }
    
    public String getPrimeiraLegislaturaNumero() { return primeiraLegislaturaNumero; }
    public void setPrimeiraLegislaturaNumero(String primeiraLegislaturaNumero) { this.primeiraLegislaturaNumero = primeiraLegislaturaNumero; }
    
    public String getPrimeiraLegislaturaDataInicio() { return primeiraLegislaturaDataInicio; }
    public void setPrimeiraLegislaturaDataInicio(String primeiraLegislaturaDataInicio) { this.primeiraLegislaturaDataInicio = primeiraLegislaturaDataInicio; }
    
    public String getPrimeiraLegislaturaDataFim() { return primeiraLegislaturaDataFim; }
    public void setPrimeiraLegislaturaDataFim(String primeiraLegislaturaDataFim) { this.primeiraLegislaturaDataFim = primeiraLegislaturaDataFim; }
    
    public String getSegundaLegislaturaNumero() { return segundaLegislaturaNumero; }
    public void setSegundaLegislaturaNumero(String segundaLegislaturaNumero) { this.segundaLegislaturaNumero = segundaLegislaturaNumero; }
    
    public String getSegundaLegislaturaDataInicio() { return segundaLegislaturaDataInicio; }
    public void setSegundaLegislaturaDataInicio(String segundaLegislaturaDataInicio) { this.segundaLegislaturaDataInicio = segundaLegislaturaDataInicio; }
    
    public String getSegundaLegislaturaDataFim() { return segundaLegislaturaDataFim; }
    public void setSegundaLegislaturaDataFim(String segundaLegislaturaDataFim) { this.segundaLegislaturaDataFim = segundaLegislaturaDataFim; }
    
    public Object getSuplentes() { return suplentes; }
    public void setSuplentes(Object suplentes) { this.suplentes = suplentes; }
    
    public Object getExercicios() { return exercicios; }
    public void setExercicios(Object exercicios) { this.exercicios = exercicios; }

    public Object getBloco() { return bloco; }
    public void setBloco(Object bloco) { this.bloco = bloco; }

    public String getMembroMesa() { return membroMesa; }
    public void setMembroMesa(String membroMesa) { this.membroMesa = membroMesa; }

    public String getMembroLideranca() { return membroLideranca; }
    public void setMembroLideranca(String membroLideranca) { this.membroLideranca = membroLideranca; }

    public String getSiglaPartido() { return siglaPartido; }
    public void setSiglaPartido(String siglaPartido) { this.siglaPartido = siglaPartido; }
    
    public String getUfParlamentar() { return ufParlamentar; }
    public void setUfParlamentar(String ufParlamentar) { this.ufParlamentar = ufParlamentar; }
}