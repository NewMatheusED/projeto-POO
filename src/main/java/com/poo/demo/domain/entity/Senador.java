package com.poo.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade Senador baseada no SenadorDto existente.
 * Segue os princípios SOLID e Object Calisthenics.
 */
@Entity
@Table(name = "senadores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Senador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo", length = 100)
    private String codigo;
    
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "nome_completo", length = 500)
    private String nomeCompleto;
    
    @Column(name = "sexo", length = 10)
    private String sexo;
    
    @Column(name = "partido", length = 100)
    private String partido;
    
    @Column(name = "uf", length = 2)
    private String uf;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Column(name = "url_foto", length = 500)
    private String urlFoto;
    
    @Column(name = "url_pagina", length = 500)
    private String urlPagina;
    
    @Column(name = "sigla_partido", length = 100)
    private String siglaPartido;
    
    @Column(name = "uf_parlamentar", length = 2)
    private String ufParlamentar;
    
    @Column(name = "membro_mesa", length = 100)
    private String membroMesa;
    
    @Column(name = "membro_lideranca", length = 100)
    private String membroLideranca;
    
    @Column(name = "bloco", columnDefinition = "TEXT")
    private Object bloco;
    
    @Column(name = "codigo_mandato", length = 100)
    private String codigoMandato;
    
    @Column(name = "uf_parlamentar_mandato", length = 2)
    private String ufParlamentarMandato;
    
    @Column(name = "descricao_participacao", length = 500)
    private String descricaoParticipacao;
    
    @Column(name = "primeira_legislatura_numero", length = 100)
    private String primeiraLegislaturaNumero;
    
    @Column(name = "primeira_legislatura_data_inicio", length = 100)
    private String primeiraLegislaturaDataInicio;
    
    @Column(name = "primeira_legislatura_data_fim", length = 100)
    private String primeiraLegislaturaDataFim;
    
    @Column(name = "segunda_legislatura_numero", length = 100)
    private String segundaLegislaturaNumero;
    
    @Column(name = "segunda_legislatura_data_inicio", length = 100)
    private String segundaLegislaturaDataInicio;
    
    @Column(name = "segunda_legislatura_data_fim", length = 100)
    private String segundaLegislaturaDataFim;
    
    @Column(name = "suplentes", columnDefinition = "TEXT")
    private Object suplentes;
    
    @Column(name = "exercicios", columnDefinition = "TEXT")
    private Object exercicios;
    
    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @Version
    @Column(name = "versao")
    private Long versao;
}
