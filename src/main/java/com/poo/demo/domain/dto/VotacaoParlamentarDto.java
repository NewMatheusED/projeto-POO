package com.poo.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VotacaoParlamentarDto {
    
    // Classe interna para Votacoes
    public static class Votacoes {
        private List<Votacao> votacao;
        
        @JsonProperty("Votacao")
        public List<Votacao> getVotacao() { return votacao; }
        public void setVotacao(List<Votacao> votacao) { this.votacao = votacao; }
    }
    
    // Classe interna para Votacao
    public static class Votacao {
        private SessaoPlenaria sessaoPlenaria;
        private Materia materia;
        private Tramitacao tramitacao;
        private String codigoSessaoVotacao;
        private String sequencial;
        private String indicadorVotacaoSecreta;
        private String descricaoVotacao;
        private String descricaoResultado;
        private String siglaDescricaoVoto;
        private String descricaoVoto;
        private String totalVotosSim;
        private String totalVotosNao;
        private String totalVotosAbstencao;
        
        @JsonProperty("SessaoPlenaria")
        public SessaoPlenaria getSessaoPlenaria() { return sessaoPlenaria; }
        public void setSessaoPlenaria(SessaoPlenaria sessaoPlenaria) { this.sessaoPlenaria = sessaoPlenaria; }
        
        @JsonProperty("Materia")
        public Materia getMateria() { return materia; }
        public void setMateria(Materia materia) { this.materia = materia; }
        
        @JsonProperty("Tramitacao")
        public Tramitacao getTramitacao() { return tramitacao; }
        public void setTramitacao(Tramitacao tramitacao) { this.tramitacao = tramitacao; }
        
        @JsonProperty("CodigoSessaoVotacao")
        public String getCodigoSessaoVotacao() { return codigoSessaoVotacao; }
        public void setCodigoSessaoVotacao(String codigoSessaoVotacao) { this.codigoSessaoVotacao = codigoSessaoVotacao; }
        
        @JsonProperty("Sequencial")
        public String getSequencial() { return sequencial; }
        public void setSequencial(String sequencial) { this.sequencial = sequencial; }
        
        @JsonProperty("IndicadorVotacaoSecreta")
        public String getIndicadorVotacaoSecreta() { return indicadorVotacaoSecreta; }
        public void setIndicadorVotacaoSecreta(String indicadorVotacaoSecreta) { this.indicadorVotacaoSecreta = indicadorVotacaoSecreta; }
        
        @JsonProperty("DescricaoVotacao")
        public String getDescricaoVotacao() { return descricaoVotacao; }
        public void setDescricaoVotacao(String descricaoVotacao) { this.descricaoVotacao = descricaoVotacao; }
        
        @JsonProperty("DescricaoResultado")
        public String getDescricaoResultado() { return descricaoResultado; }
        public void setDescricaoResultado(String descricaoResultado) { this.descricaoResultado = descricaoResultado; }
        
        @JsonProperty("SiglaDescricaoVoto")
        public String getSiglaDescricaoVoto() { return siglaDescricaoVoto; }
        public void setSiglaDescricaoVoto(String siglaDescricaoVoto) { this.siglaDescricaoVoto = siglaDescricaoVoto; }
        
        @JsonProperty("TotalVotosSim")
        public String getTotalVotosSim() { return totalVotosSim; }
        public void setTotalVotosSim(String totalVotosSim) { this.totalVotosSim = totalVotosSim; }
        
        @JsonProperty("TotalVotosNao")
        public String getTotalVotosNao() { return totalVotosNao; }
        public void setTotalVotosNao(String totalVotosNao) { this.totalVotosNao = totalVotosNao; }
        
        @JsonProperty("TotalVotosAbstencao")
        public String getTotalVotosAbstencao() { return totalVotosAbstencao; }
        public void setTotalVotosAbstencao(String totalVotosAbstencao) { this.totalVotosAbstencao = totalVotosAbstencao; }
        
        @JsonProperty("DescricaoVoto")
        public String getDescricaoVoto() { return descricaoVoto; }
        public void setDescricaoVoto(String descricaoVoto) { this.descricaoVoto = descricaoVoto; }
    }
    
    // Classe interna para SessaoPlenaria
    public static class SessaoPlenaria {
        private String codigoSessao;
        private String siglaCasaSessao;
        private String codigoSessaoLegislativa;
        private String siglaTipoSessao;
        private String numeroSessao;
        private String dataSessao;
        private String horaInicioSessao;
        
        @JsonProperty("CodigoSessao")
        public String getCodigoSessao() { return codigoSessao; }
        public void setCodigoSessao(String codigoSessao) { this.codigoSessao = codigoSessao; }
        
        @JsonProperty("SiglaCasaSessao")
        public String getSiglaCasaSessao() { return siglaCasaSessao; }
        public void setSiglaCasaSessao(String siglaCasaSessao) { this.siglaCasaSessao = siglaCasaSessao; }
        
        @JsonProperty("CodigoSessaoLegislativa")
        public String getCodigoSessaoLegislativa() { return codigoSessaoLegislativa; }
        public void setCodigoSessaoLegislativa(String codigoSessaoLegislativa) { this.codigoSessaoLegislativa = codigoSessaoLegislativa; }
        
        @JsonProperty("SiglaTipoSessao")
        public String getSiglaTipoSessao() { return siglaTipoSessao; }
        public void setSiglaTipoSessao(String siglaTipoSessao) { this.siglaTipoSessao = siglaTipoSessao; }
        
        @JsonProperty("NumeroSessao")
        public String getNumeroSessao() { return numeroSessao; }
        public void setNumeroSessao(String numeroSessao) { this.numeroSessao = numeroSessao; }
        
        @JsonProperty("DataSessao")
        public String getDataSessao() { return dataSessao; }
        public void setDataSessao(String dataSessao) { this.dataSessao = dataSessao; }
        
        @JsonProperty("HoraInicioSessao")
        public String getHoraInicioSessao() { return horaInicioSessao; }
        public void setHoraInicioSessao(String horaInicioSessao) { this.horaInicioSessao = horaInicioSessao; }
    }
    
    // Classe interna para Materia
    public static class Materia {
        private String codigo;
        private String identificacaoProcesso;
        private String descricaoIdentificacao;
        private String sigla;
        private String numero;
        private String ano;
        private String ementa;
        private String data;
        
        @JsonProperty("Codigo")
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        
        @JsonProperty("IdentificacaoProcesso")
        public String getIdentificacaoProcesso() { return identificacaoProcesso; }
        public void setIdentificacaoProcesso(String identificacaoProcesso) { this.identificacaoProcesso = identificacaoProcesso; }
        
        @JsonProperty("DescricaoIdentificacao")
        public String getDescricaoIdentificacao() { return descricaoIdentificacao; }
        public void setDescricaoIdentificacao(String descricaoIdentificacao) { this.descricaoIdentificacao = descricaoIdentificacao; }
        
        @JsonProperty("Sigla")
        public String getSigla() { return sigla; }
        public void setSigla(String sigla) { this.sigla = sigla; }
        
        @JsonProperty("Numero")
        public String getNumero() { return numero; }
        public void setNumero(String numero) { this.numero = numero; }
        
        @JsonProperty("Ano")
        public String getAno() { return ano; }
        public void setAno(String ano) { this.ano = ano; }
        
        @JsonProperty("Ementa")
        public String getEmenta() { return ementa; }
        public void setEmenta(String ementa) { this.ementa = ementa; }
        
        @JsonProperty("Data")
        public String getData() { return data; }
        public void setData(String data) { this.data = data; }
    }
    
    // Classe interna para Tramitacao
    public static class Tramitacao {
        private IdentificacaoTramitacao identificacaoTramitacao;
        
        @JsonProperty("IdentificacaoTramitacao")
        public IdentificacaoTramitacao getIdentificacaoTramitacao() { return identificacaoTramitacao; }
        public void setIdentificacaoTramitacao(IdentificacaoTramitacao identificacaoTramitacao) { this.identificacaoTramitacao = identificacaoTramitacao; }
    }
    
    // Classe interna para IdentificacaoTramitacao
    public static class IdentificacaoTramitacao {
        private String codigoTramitacao;
        private String numeroAutuacao;
        private String dataTramitacao;
        private String textoTramitacao;
        private OrigemTramitacao origemTramitacao;
        private DestinoTramitacao destinoTramitacao;
        
        @JsonProperty("CodigoTramitacao")
        public String getCodigoTramitacao() { return codigoTramitacao; }
        public void setCodigoTramitacao(String codigoTramitacao) { this.codigoTramitacao = codigoTramitacao; }
        
        @JsonProperty("NumeroAutuacao")
        public String getNumeroAutuacao() { return numeroAutuacao; }
        public void setNumeroAutuacao(String numeroAutuacao) { this.numeroAutuacao = numeroAutuacao; }
        
        @JsonProperty("DataTramitacao")
        public String getDataTramitacao() { return dataTramitacao; }
        public void setDataTramitacao(String dataTramitacao) { this.dataTramitacao = dataTramitacao; }
        
        @JsonProperty("TextoTramitacao")
        public String getTextoTramitacao() { return textoTramitacao; }
        public void setTextoTramitacao(String textoTramitacao) { this.textoTramitacao = textoTramitacao; }
        
        @JsonProperty("OrigemTramitacao")
        public OrigemTramitacao getOrigemTramitacao() { return origemTramitacao; }
        public void setOrigemTramitacao(OrigemTramitacao origemTramitacao) { this.origemTramitacao = origemTramitacao; }
        
        @JsonProperty("DestinoTramitacao")
        public DestinoTramitacao getDestinoTramitacao() { return destinoTramitacao; }
        public void setDestinoTramitacao(DestinoTramitacao destinoTramitacao) { this.destinoTramitacao = destinoTramitacao; }
    }
    
    // Classe interna para OrigemTramitacao
    public static class OrigemTramitacao {
        private Local local;
        
        @JsonProperty("Local")
        public Local getLocal() { return local; }
        public void setLocal(Local local) { this.local = local; }
    }
    
    // Classe interna para DestinoTramitacao
    public static class DestinoTramitacao {
        private Local local;
        
        @JsonProperty("Local")
        public Local getLocal() { return local; }
        public void setLocal(Local local) { this.local = local; }
    }
    
    // Classe interna para Local
    public static class Local {
        private String codigoLocal;
        private String tipoLocal;
        private String siglaCasaLocal;
        private String siglaLocal;
        private String nomeLocal;
        
        @JsonProperty("CodigoLocal")
        public String getCodigoLocal() { return codigoLocal; }
        public void setCodigoLocal(String codigoLocal) { this.codigoLocal = codigoLocal; }
        
        @JsonProperty("TipoLocal")
        public String getTipoLocal() { return tipoLocal; }
        public void setTipoLocal(String tipoLocal) { this.tipoLocal = tipoLocal; }
        
        @JsonProperty("SiglaCasaLocal")
        public String getSiglaCasaLocal() { return siglaCasaLocal; }
        public void setSiglaCasaLocal(String siglaCasaLocal) { this.siglaCasaLocal = siglaCasaLocal; }
        
        @JsonProperty("SiglaLocal")
        public String getSiglaLocal() { return siglaLocal; }
        public void setSiglaLocal(String siglaLocal) { this.siglaLocal = siglaLocal; }
        
        @JsonProperty("NomeLocal")
        public String getNomeLocal() { return nomeLocal; }
        public void setNomeLocal(String nomeLocal) { this.nomeLocal = nomeLocal; }
    }
}
