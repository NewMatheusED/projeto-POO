package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SenadorDetailDto {
    // Classe interna para IdentificacaoParlamentar
    public static class IdentificacaoParlamentar {
        private String codigoParlamentar;
        private String codigoPublicoNaLegAtual;
        private String nomeParlamentar;
        private String nomeCompletoParlamentar;
        private String sexoParlamentar;
        private String urlFotoParlamentar;
        private String urlPaginaParlamentar;
        private String emailParlamentar;
        private String siglaPartidoParlamentar;
        private String ufParlamentar;
        
        @JsonProperty("CodigoParlamentar")
        public String getCodigoParlamentar() { return codigoParlamentar; }
        public void setCodigoParlamentar(String codigoParlamentar) { this.codigoParlamentar = codigoParlamentar; }
        
        @JsonProperty("CodigoPublicoNaLegAtual")
        public String getCodigoPublicoNaLegAtual() { return codigoPublicoNaLegAtual; }
        public void setCodigoPublicoNaLegAtual(String codigoPublicoNaLegAtual) { this.codigoPublicoNaLegAtual = codigoPublicoNaLegAtual; }
        
        @JsonProperty("NomeParlamentar")
        public String getNomeParlamentar() { return nomeParlamentar; }
        public void setNomeParlamentar(String nomeParlamentar) { this.nomeParlamentar = nomeParlamentar; }
        
        @JsonProperty("NomeCompletoParlamentar")
        public String getNomeCompletoParlamentar() { return nomeCompletoParlamentar; }
        public void setNomeCompletoParlamentar(String nomeCompletoParlamentar) { this.nomeCompletoParlamentar = nomeCompletoParlamentar; }
        
        @JsonProperty("SexoParlamentar")
        public String getSexoParlamentar() { return sexoParlamentar; }
        public void setSexoParlamentar(String sexoParlamentar) { this.sexoParlamentar = sexoParlamentar; }
        
        @JsonProperty("UrlFotoParlamentar")
        public String getUrlFotoParlamentar() { return urlFotoParlamentar; }
        public void setUrlFotoParlamentar(String urlFotoParlamentar) { this.urlFotoParlamentar = urlFotoParlamentar; }
        
        @JsonProperty("UrlPaginaParlamentar")
        public String getUrlPaginaParlamentar() { return urlPaginaParlamentar; }
        public void setUrlPaginaParlamentar(String urlPaginaParlamentar) { this.urlPaginaParlamentar = urlPaginaParlamentar; }
        
        @JsonProperty("EmailParlamentar")
        public String getEmailParlamentar() { return emailParlamentar; }
        public void setEmailParlamentar(String emailParlamentar) { this.emailParlamentar = emailParlamentar; }
        
        @JsonProperty("SiglaPartidoParlamentar")
        public String getSiglaPartidoParlamentar() { return siglaPartidoParlamentar; }
        public void setSiglaPartidoParlamentar(String siglaPartidoParlamentar) { this.siglaPartidoParlamentar = siglaPartidoParlamentar; }
        
        @JsonProperty("UfParlamentar")
        public String getUfParlamentar() { return ufParlamentar; }
        public void setUfParlamentar(String ufParlamentar) { this.ufParlamentar = ufParlamentar; }
    }
    
    // Classe interna para DadosBasicosParlamentar
    public static class DadosBasicosParlamentar {
        private String dataNascimento;
        private String naturalidade;
        private String ufNaturalidade;
        private String enderecoParlamentar;
        
        @JsonProperty("DataNascimento")
        public String getDataNascimento() { return dataNascimento; }
        public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
        
        @JsonProperty("Naturalidade")
        public String getNaturalidade() { return naturalidade; }
        public void setNaturalidade(String naturalidade) { this.naturalidade = naturalidade; }
        
        @JsonProperty("UfNaturalidade")
        public String getUfNaturalidade() { return ufNaturalidade; }
        public void setUfNaturalidade(String ufNaturalidade) { this.ufNaturalidade = ufNaturalidade; }
        
        @JsonProperty("EnderecoParlamentar")
        public String getEnderecoParlamentar() { return enderecoParlamentar; }
        public void setEnderecoParlamentar(String enderecoParlamentar) { this.enderecoParlamentar = enderecoParlamentar; }
    }
    
    // Classe interna para Telefone
    public static class Telefone {
        private String numeroTelefone;
        private String ordemPublicacao;
        private String indicadorFax;
        
        @JsonProperty("NumeroTelefone")
        public String getNumeroTelefone() { return numeroTelefone; }
        public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }
        
        @JsonProperty("OrdemPublicacao")
        public String getOrdemPublicacao() { return ordemPublicacao; }
        public void setOrdemPublicacao(String ordemPublicacao) { this.ordemPublicacao = ordemPublicacao; }
        
        @JsonProperty("IndicadorFax")
        public String getIndicadorFax() { return indicadorFax; }
        public void setIndicadorFax(String indicadorFax) { this.indicadorFax = indicadorFax; }
    }
    
    // Classe interna para Telefones
    public static class Telefones {
        private List<Telefone> telefone;
        
        @JsonProperty("Telefone")
        public List<Telefone> getTelefone() { return telefone; }
        public void setTelefone(List<Telefone> telefone) { this.telefone = telefone; }
    }
    
    // Classe interna para Servico
    public static class Servico {
        private String nomeServico;
        private String descricaoServico;
        private String urlServico;
        
        @JsonProperty("NomeServico")
        public String getNomeServico() { return nomeServico; }
        public void setNomeServico(String nomeServico) { this.nomeServico = nomeServico; }
        
        @JsonProperty("DescricaoServico")
        public String getDescricaoServico() { return descricaoServico; }
        public void setDescricaoServico(String descricaoServico) { this.descricaoServico = descricaoServico; }
        
        @JsonProperty("UrlServico")
        public String getUrlServico() { return urlServico; }
        public void setUrlServico(String urlServico) { this.urlServico = urlServico; }
    }
    
    // Classe interna para OutrasInformacoes
    public static class OutrasInformacoes {
        private List<Servico> servico;
        
        @JsonProperty("Servico")
        public List<Servico> getServico() { return servico; }
        public void setServico(List<Servico> servico) { this.servico = servico; }
    }
    
    // Classe interna para Parlamentar
    public static class Parlamentar {
        private IdentificacaoParlamentar identificacaoParlamentar;
        private DadosBasicosParlamentar dadosBasicosParlamentar;
        private Telefones telefones;
        private OutrasInformacoes outrasInformacoes;
        
        @JsonProperty("IdentificacaoParlamentar")
        public IdentificacaoParlamentar getIdentificacaoParlamentar() { return identificacaoParlamentar; }
        public void setIdentificacaoParlamentar(IdentificacaoParlamentar identificacaoParlamentar) { this.identificacaoParlamentar = identificacaoParlamentar; }
        
        @JsonProperty("DadosBasicosParlamentar")
        public DadosBasicosParlamentar getDadosBasicosParlamentar() { return dadosBasicosParlamentar; }
        public void setDadosBasicosParlamentar(DadosBasicosParlamentar dadosBasicosParlamentar) { this.dadosBasicosParlamentar = dadosBasicosParlamentar; }
        
        @JsonProperty("Telefones")
        public Telefones getTelefones() { return telefones; }
        public void setTelefones(Telefones telefones) { this.telefones = telefones; }
        
        @JsonProperty("OutrasInformacoes")
        public OutrasInformacoes getOutrasInformacoes() { return outrasInformacoes; }
        public void setOutrasInformacoes(OutrasInformacoes outrasInformacoes) { this.outrasInformacoes = outrasInformacoes; }
    }
    
    // Classe interna para DetalheParlamentar
    public static class DetalheParlamentar {
        private Parlamentar parlamentar;
        
        @JsonProperty("Parlamentar")
        public Parlamentar getParlamentar() { return parlamentar; }
        public void setParlamentar(Parlamentar parlamentar) { this.parlamentar = parlamentar; }
    }
}
