package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VotacoesProjetoDto {
    
    public static class VotacoesProjeto {
        private String codigoSessao;
        private String identificacao;
        private List<VotacaoProjeto> votos;
        
        @JsonProperty("codigoSessao")
        public String getCodigoSessao() { return codigoSessao; }
        public void setCodigoSessao(String codigoSessao) { this.codigoSessao = codigoSessao; }

        @JsonProperty("identificacao")
        public String getIdentificacao() { return identificacao; }
        public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
        
        @JsonProperty("votos")
        public List<VotacaoProjeto> getVotos() { return votos; }
        public void setVotos(List<VotacaoProjeto> votos) { this.votos = votos; }
    }

    public static class VotacaoProjeto {
        private String codigoParlamentar;
        private String nomeParlamentar;
        private String sexoParlamentar;
        private String siglaPartidoParlamentar;
        private String siglaUFParlamentar;
        private String siglaVotoParlamentar;
        private String descricaoVotoParlamentar;

        @JsonProperty("codigoParlamentar")
        public String getCodigoParlamentar() { return codigoParlamentar; }
        public void setCodigoParlamentar(String codigoParlamentar) { this.codigoParlamentar = codigoParlamentar; }

        @JsonProperty("nomeParlamentar")
        public String getNomeParlamentar() { return nomeParlamentar; }
        public void setNomeParlamentar(String nomeParlamentar) { this.nomeParlamentar = nomeParlamentar; }

        @JsonProperty("sexoParlamentar")
        public String getSexoParlamentar() { return sexoParlamentar; }
        public void setSexoParlamentar(String sexoParlamentar) { this.sexoParlamentar = sexoParlamentar; }

        @JsonProperty("siglaPartidoParlamentar")
        public String getSiglaPartidoParlamentar() { return siglaPartidoParlamentar; }
        public void setSiglaPartidoParlamentar(String siglaPartidoParlamentar) { this.siglaPartidoParlamentar = siglaPartidoParlamentar; }

        @JsonProperty("siglaUFParlamentar")
        public String getSiglaUFParlamentar() { return siglaUFParlamentar; }
        public void setSiglaUFParlamentar(String siglaUFParlamentar) { this.siglaUFParlamentar = siglaUFParlamentar; }

        @JsonProperty("siglaVotoParlamentar")
        public String getSiglaVotoParlamentar() { return siglaVotoParlamentar; }
        public void setSiglaVotoParlamentar(String siglaVotoParlamentar) { this.siglaVotoParlamentar = siglaVotoParlamentar; }

        @JsonProperty("descricaoVotoParlamentar")
        public String getDescricaoVotoParlamentar() { return descricaoVotoParlamentar; }
        public void setDescricaoVotoParlamentar(String descricaoVotoParlamentar) { this.descricaoVotoParlamentar = descricaoVotoParlamentar; }
    }
}
