package com.poo.demo.infrastructure.converter;

import com.poo.demo.domain.dto.SenadorRequestDto;
import com.poo.demo.domain.dto.SenadorResponseDto;
import com.poo.demo.domain.entity.Senador;
import org.springframework.stereotype.Component;

/**
 * Conversor para transformar entre entidade Senador e DTOs.
 * Baseado exatamente no SenadorDto existente.
 * Segue os princípios SOLID e Object Calisthenics.
 */
@Component
public class SenadorConverter {
    
    /**
     * Converte SenadorRequestDto para entidade Senador.
     */
    public Senador toEntity(SenadorRequestDto dto) {
        return Senador.builder()
            .codigo(dto.getCodigo())
            .nome(dto.getNome())
            .nomeCompleto(dto.getNomeCompleto())
            .sexo(dto.getSexo())
            .partido(dto.getPartido())
            .uf(dto.getUf())
            .email(dto.getEmail())
            .urlFoto(dto.getUrlFoto())
            .urlPagina(dto.getUrlPagina())
            .siglaPartido(dto.getSiglaPartido())
            .ufParlamentar(dto.getUfParlamentar())
            .membroMesa(dto.getMembroMesa())
            .membroLideranca(dto.getMembroLideranca())
            .bloco(dto.getBloco())
            .codigoMandato(dto.getCodigoMandato())
            .ufParlamentarMandato(dto.getUfParlamentarMandato())
            .descricaoParticipacao(dto.getDescricaoParticipacao())
            .primeiraLegislaturaNumero(dto.getPrimeiraLegislaturaNumero())
            .primeiraLegislaturaDataInicio(dto.getPrimeiraLegislaturaDataInicio())
            .primeiraLegislaturaDataFim(dto.getPrimeiraLegislaturaDataFim())
            .segundaLegislaturaNumero(dto.getSegundaLegislaturaNumero())
            .segundaLegislaturaDataInicio(dto.getSegundaLegislaturaDataInicio())
            .segundaLegislaturaDataFim(dto.getSegundaLegislaturaDataFim())
            .suplentes(dto.getSuplentes())
            .exercicios(dto.getExercicios())
            .build();
    }
    
    /**
     * Converte entidade Senador para SenadorResponseDto.
     */
    public SenadorResponseDto toResponseDto(Senador entity) {
        return SenadorResponseDto.builder()
            .id(entity.getId())
            .codigo(entity.getCodigo())
            .nome(entity.getNome())
            .nomeCompleto(entity.getNomeCompleto())
            .sexo(entity.getSexo())
            .partido(entity.getPartido())
            .uf(entity.getUf())
            .email(entity.getEmail())
            .urlFoto(entity.getUrlFoto())
            .urlPagina(entity.getUrlPagina())
            .siglaPartido(entity.getSiglaPartido())
            .ufParlamentar(entity.getUfParlamentar())
            .membroMesa(entity.getMembroMesa())
            .membroLideranca(entity.getMembroLideranca())
            .bloco(entity.getBloco())
            .codigoMandato(entity.getCodigoMandato())
            .ufParlamentarMandato(entity.getUfParlamentarMandato())
            .descricaoParticipacao(entity.getDescricaoParticipacao())
            .primeiraLegislaturaNumero(entity.getPrimeiraLegislaturaNumero())
            .primeiraLegislaturaDataInicio(entity.getPrimeiraLegislaturaDataInicio())
            .primeiraLegislaturaDataFim(entity.getPrimeiraLegislaturaDataFim())
            .segundaLegislaturaNumero(entity.getSegundaLegislaturaNumero())
            .segundaLegislaturaDataInicio(entity.getSegundaLegislaturaDataInicio())
            .segundaLegislaturaDataFim(entity.getSegundaLegislaturaDataFim())
            .suplentes(entity.getSuplentes())
            .exercicios(entity.getExercicios())
            .dataCriacao(entity.getDataCriacao())
            .dataAtualizacao(entity.getDataAtualizacao())
            .build();
    }
    
    /**
     * Atualiza uma entidade Senador existente com dados do DTO.
     */
    public void updateEntityFromDto(Senador entity, SenadorRequestDto dto) {
        entity.setCodigo(dto.getCodigo());
        entity.setNome(dto.getNome());
        entity.setNomeCompleto(dto.getNomeCompleto());
        entity.setSexo(dto.getSexo());
        entity.setPartido(dto.getPartido());
        entity.setUf(dto.getUf());
        entity.setEmail(dto.getEmail());
        entity.setUrlFoto(dto.getUrlFoto());
        entity.setUrlPagina(dto.getUrlPagina());
        entity.setSiglaPartido(dto.getSiglaPartido());
        entity.setUfParlamentar(dto.getUfParlamentar());
        entity.setMembroMesa(dto.getMembroMesa());
        entity.setMembroLideranca(dto.getMembroLideranca());
        entity.setBloco(dto.getBloco());
        entity.setCodigoMandato(dto.getCodigoMandato());
        entity.setUfParlamentarMandato(dto.getUfParlamentarMandato());
        entity.setDescricaoParticipacao(dto.getDescricaoParticipacao());
        entity.setPrimeiraLegislaturaNumero(dto.getPrimeiraLegislaturaNumero());
        entity.setPrimeiraLegislaturaDataInicio(dto.getPrimeiraLegislaturaDataInicio());
        entity.setPrimeiraLegislaturaDataFim(dto.getPrimeiraLegislaturaDataFim());
        entity.setSegundaLegislaturaNumero(dto.getSegundaLegislaturaNumero());
        entity.setSegundaLegislaturaDataInicio(dto.getSegundaLegislaturaDataInicio());
        entity.setSegundaLegislaturaDataFim(dto.getSegundaLegislaturaDataFim());
        entity.setSuplentes(dto.getSuplentes());
        entity.setExercicios(dto.getExercicios());
    }
}
