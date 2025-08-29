package com.poo.demo.application.service;

import com.poo.demo.domain.dto.SenadorDto;
import com.poo.demo.domain.entity.Senador;
import com.poo.demo.infrastructure.repository.SenadorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para operações relacionadas a Senadores.
 * Segue os princípios SOLID, especialmente Single Responsibility Principle.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SenadorService {
    
    private final SenadorRepository senadorRepository;
    
    /**
     * Cria um novo senador.
     */
    public Senador criarSenador(Senador senador) {
        log.info("Criando novo senador: {}", senador.getNome());
        
        if (senadorRepository.existsByCodigo(senador.getCodigo())) {
            throw new IllegalArgumentException("Já existe um senador com este código");
        }
        
        try {
            return senadorRepository.save(senador);
        } catch (Exception e) {
            log.error("Erro ao criar senador: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Busca um senador por ID.
     */
    @Transactional(readOnly = true)
    public Optional<Senador> buscarPorId(Long id) {
        log.debug("Buscando senador por ID: {}", id);
        return senadorRepository.findById(id);
    }
    
    /**
     * Busca um senador por código.
     */
    @Transactional(readOnly = true)
    public Optional<Senador> buscarPorCodigo(String codigo) {
        log.debug("Buscando senador por código: {}", codigo);
        return senadorRepository.findByCodigo(codigo);
    }
    
    /**
     * Busca todos os senadores.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarTodos() {
        log.debug("Buscando todos os senadores");
        return senadorRepository.findAll();
    }
    
    /**
     * Busca senadores por UF.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorUf(String uf) {
        log.debug("Buscando senadores por UF: {}", uf);
        return senadorRepository.findByUf(uf);
    }
    
    /**
     * Busca senadores por partido.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorPartido(String partido) {
        log.debug("Buscando senadores por partido: {}", partido);
        return senadorRepository.findByPartido(partido);
    }
    
    /**
     * Busca senadores por sigla do partido.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorSiglaPartido(String siglaPartido) {
        log.debug("Buscando senadores por sigla do partido: {}", siglaPartido);
        return senadorRepository.findBySiglaPartido(siglaPartido);
    }
    
    /**
     * Busca senadores por primeira legislatura.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorPrimeiraLegislatura(String primeiraLegislaturaNumero) {
        log.debug("Buscando senadores por primeira legislatura: {}", primeiraLegislaturaNumero);
        return senadorRepository.findByPrimeiraLegislaturaNumero(primeiraLegislaturaNumero);
    }
    
    /**
     * Busca senadores por segunda legislatura.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorSegundaLegislatura(String segundaLegislaturaNumero) {
        log.debug("Buscando senadores por segunda legislatura: {}", segundaLegislaturaNumero);
        return senadorRepository.findBySegundaLegislaturaNumero(segundaLegislaturaNumero);
    }
    
    /**
     * Busca senadores que são membros da mesa.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarMembrosMesa() {
        log.debug("Buscando senadores membros da mesa");
        return senadorRepository.findByMembroMesaIsNotNull();
    }
    
    /**
     * Busca senadores que são membros da liderança.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarMembrosLideranca() {
        log.debug("Buscando senadores membros da liderança");
        return senadorRepository.findByMembroLiderancaIsNotNull();
    }
    
    /**
     * Atualiza um senador existente.
     */
    public Senador atualizarSenador(Long id, Senador senadorAtualizado) {
        log.info("Atualizando senador com ID: {}", id);
        
        Senador senadorExistente = senadorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Senador não encontrado"));
        
        // Atualiza todos os campos
        senadorExistente.setCodigo(senadorAtualizado.getCodigo());
        senadorExistente.setNome(senadorAtualizado.getNome());
        senadorExistente.setNomeCompleto(senadorAtualizado.getNomeCompleto());
        senadorExistente.setSexo(senadorAtualizado.getSexo());
        senadorExistente.setPartido(senadorAtualizado.getPartido());
        senadorExistente.setUf(senadorAtualizado.getUf());
        senadorExistente.setEmail(senadorAtualizado.getEmail());
        senadorExistente.setUrlFoto(senadorAtualizado.getUrlFoto());
        senadorExistente.setUrlPagina(senadorAtualizado.getUrlPagina());
        senadorExistente.setSiglaPartido(senadorAtualizado.getSiglaPartido());
        senadorExistente.setUfParlamentar(senadorAtualizado.getUfParlamentar());
        senadorExistente.setMembroMesa(senadorAtualizado.getMembroMesa());
        senadorExistente.setMembroLideranca(senadorAtualizado.getMembroLideranca());
        senadorExistente.setBloco(senadorAtualizado.getBloco());
        senadorExistente.setCodigoMandato(senadorAtualizado.getCodigoMandato());
        senadorExistente.setUfParlamentarMandato(senadorAtualizado.getUfParlamentarMandato());
        senadorExistente.setDescricaoParticipacao(senadorAtualizado.getDescricaoParticipacao());
        senadorExistente.setPrimeiraLegislaturaNumero(senadorAtualizado.getPrimeiraLegislaturaNumero());
        senadorExistente.setPrimeiraLegislaturaDataInicio(senadorAtualizado.getPrimeiraLegislaturaDataInicio());
        senadorExistente.setPrimeiraLegislaturaDataFim(senadorAtualizado.getPrimeiraLegislaturaDataFim());
        senadorExistente.setSegundaLegislaturaNumero(senadorAtualizado.getSegundaLegislaturaNumero());
        senadorExistente.setSegundaLegislaturaDataInicio(senadorAtualizado.getSegundaLegislaturaDataInicio());
        senadorExistente.setSegundaLegislaturaDataFim(senadorAtualizado.getSegundaLegislaturaDataFim());
        senadorExistente.setSuplentes(senadorAtualizado.getSuplentes());
        senadorExistente.setExercicios(senadorAtualizado.getExercicios());
        
        return senadorRepository.save(senadorExistente);
    }
    
    /**
     * Remove um senador permanentemente.
     */
    public void removerSenador(Long id) {
        log.info("Removendo senador com ID: {}", id);
        
        if (!senadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Senador não encontrado");
        }
        
        senadorRepository.deleteById(id);
    }
    
    /**
     * Busca senadores por UF e partido.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorUfEPartido(String uf, String partido) {
        log.debug("Buscando senadores por UF: {} e partido: {}", uf, partido);
        return senadorRepository.findByUfAndPartido(uf, partido);
    }
    
    /**
     * Busca senadores por UF e sigla do partido.
     */
    @Transactional(readOnly = true)
    public List<Senador> buscarPorUfESiglaPartido(String uf, String siglaPartido) {
        log.debug("Buscando senadores por UF: {} e sigla do partido: {}", uf, siglaPartido);
        return senadorRepository.findByUfAndSiglaPartido(uf, siglaPartido);
    }

    /**
     * Importa uma lista de senadores em lote
     * Segue o princípio de responsabilidade única
     */
    @Transactional
    public List<Senador> importarSenadoresEmLote(List<SenadorDto> senadoresDto) {
        log.info("Importando {} senadores em lote", senadoresDto.size());
        
        List<Senador> senadoresImportados = new ArrayList<>();
        
        for (SenadorDto dto : senadoresDto) {
            try {
                Senador senador = converterSenadorDtoParaEntidade(dto);
                senadoresImportados.add(criarSenador(senador));
            } catch (Exception e) {
                log.error("Erro ao importar senador {}: {}", dto.getNome(), e.getMessage());
                // Continua com o próximo senador em caso de erro
            }
        }
        
        log.info("Importação concluída. {} senadores importados com sucesso", senadoresImportados.size());
        return senadoresImportados;
    }

    /**
     * Converte SenadorDto para entidade Senador
     */
    private Senador converterSenadorDtoParaEntidade(SenadorDto dto) {
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
}


