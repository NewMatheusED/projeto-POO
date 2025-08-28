package com.poo.demo.domain.dto;

/**
 * DTO para representar senadores da API do Senado
 */
public class SenadorDto {
    private String codigo;
    private String nome;
    private String sigla;
    private Integer ano;
    private String ementa;
    private String autor;
    private String partido;
    private String uf;
    private String descricao;

    // Construtor padrão
    public SenadorDto() {}

    // Construtor com parâmetros
    public SenadorDto(String codigo, String nome, String sigla, Integer ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
        this.ano = ano;
    }

    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getEmenta() { return ementa; }
    public void setEmenta(String ementa) { this.ementa = ementa; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "SenadorDto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", ano=" + ano +
                ", ementa='" + ementa + '\'' +
                ", autor='" + autor + '\'' +
                ", partido='" + partido + '\'' +
                ", uf='" + uf + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}