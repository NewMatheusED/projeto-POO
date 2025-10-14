package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SenadorDetailDto {

    private String codigo;
    private String nome;
    private String nomeCompleto;
    private String sexo;
    private String partido;
    private String ufPartido;
    private String ufNaturalidade;
    private String urlFoto;
    private String dataNascimento;
    


    @JsonProperty("IdentificacaoParlamentar")
    private void unpackIdentificacao(Map<String, Object> key) {
        this.codigo = (String) key.get("CodigoParlamentar");
        this.nome = (String) key.get("NomeParlamentar");
        this.nomeCompleto = (String) key.get("NomeCompletoParlamentar");
        this.sexo = (String) key.get("SexoParlamentar");
        this.partido = (String) key.get("SiglaPartidoParlamentar");
        this.ufPartido = (String) key.get("UfParlamentar");
        this.urlFoto = (String) key.get("UrlFotoParlamentar");
    }

    @JsonProperty("DadosBasicosParlamentar")
    private void unpackDadosBasicos(Map<String, Object> key) {
        this.dataNascimento = (String) key.get("DataNascimento");
        this.ufNaturalidade = (String) key.get("UfNaturalidade");
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
    
    public String getUfPartido() { return ufPartido; }
    public void setUfPartido(String ufPartido) { this.ufPartido = ufPartido; }
    
    public String getUfNaturalidade() { return ufNaturalidade; }
    public void setUfNaturalidade(String ufNaturalidade) { this.ufNaturalidade = ufNaturalidade; }
    
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
}