package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VotacoesProjetoDto {
    @JsonProperty("DataHoraInicioReuniao")
    private String dataInicio;

    @JsonProperty("NomeColegiado")
    private String colegiado;

    @JsonProperty("Votos")
    private Votos votos;

    // Getters e Setters
    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getColegiado() {
        return colegiado;
    }

    public void setColegiado(String colegiado) {
        this.colegiado = colegiado;
    }

    public Votos getVotos() {
        return votos;
    }

    public void setVotos(Votos votos) {
        this.votos = votos;
    }

    public static class Votos {
        @JsonProperty("Voto")
        private List<Voto> voto;

        // Getters e Setters
        public List<Voto> getVoto() {
            return voto;
        }

        public void setVoto(List<Voto> voto) {
            this.voto = voto;
        }

        public static class Voto {
            @JsonProperty("CodigoParlamentar")
            private String codigoParlamentar;

            @JsonProperty("NomeParlamentar")
            private String nomeParlamentar;

            @JsonProperty("SiglaPartidoParlamentar")
            private String siglaPartidoParlamentar;

            @JsonProperty("SiglaCasaParlamentar")
            private String siglaCasaParlamentar;

            @JsonProperty("QualidadeVoto")
            private String qualidadeVoto;

            @JsonProperty("VotoPresidente")
            private String votoPresidente;

            // Getters e Setters
            public String getCodigoParlamentar() {
                return codigoParlamentar;
            }

            public void setCodigoParlamentar(String codigoParlamentar) {
                this.codigoParlamentar = codigoParlamentar;
            }

            public String getNomeParlamentar() {
                return nomeParlamentar;
            }

            public void setNomeParlamentar(String nomeParlamentar) {
                this.nomeParlamentar = nomeParlamentar;
            }

            public String getSiglaPartidoParlamentar() {
                return siglaPartidoParlamentar;
            }

            public void setSiglaPartidoParlamentar(String siglaPartidoParlamentar) {
                this.siglaPartidoParlamentar = siglaPartidoParlamentar;
            }

            public String getSiglaCasaParlamentar() {
                return siglaCasaParlamentar;
            }

            public void setSiglaCasaParlamentar(String siglaCasaParlamentar) {
                this.siglaCasaParlamentar = siglaCasaParlamentar;
            }

            public String getQualidadeVoto() {
                return qualidadeVoto;
            }

            public void setQualidadeVoto(String qualidadeVoto) {
                this.qualidadeVoto = qualidadeVoto;
            }

            public String getVotoPresidente() {
                return votoPresidente;
            }

            public void setVotoPresidente(String votoPresidente) {
                this.votoPresidente = votoPresidente;
            }
        }
    }
}
