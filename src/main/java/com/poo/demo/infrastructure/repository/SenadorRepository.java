package com.poo.demo.infrastructure.repository;

import com.poo.demo.domain.entity.Senador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório para a entidade Senador.
 * Segue os princípios SOLID e padrão Repository.
 */
@Repository
public interface SenadorRepository extends JpaRepository<Senador, Long> {
    
    /**
     * Busca senadores por UF.
     */
    List<Senador> findByUf(String uf);
    
    /**
     * Busca senadores por partido.
     */
    List<Senador> findByPartido(String partido);
    
    /**
     * Busca senadores por sigla do partido.
     */
    List<Senador> findBySiglaPartido(String siglaPartido);
    
    /**
     * Busca senador por código.
     */
    Optional<Senador> findByCodigo(String codigo);
    
    /**
     * Busca senador por nome (case insensitive).
     */
    Optional<Senador> findByNomeIgnoreCase(String nome);
    
    /**
     * Busca todos os senadores.
     */
    List<Senador> findAll();
    
    /**
     * Busca senadores por UF e partido.
     */
    @Query("SELECT s FROM Senador s WHERE s.uf = :uf AND s.partido = :partido")
    List<Senador> findByUfAndPartido(@Param("uf") String uf, @Param("partido") String partido);
    
    /**
     * Busca senadores por UF e sigla do partido.
     */
    @Query("SELECT s FROM Senador s WHERE s.uf = :uf AND s.siglaPartido = :siglaPartido")
    List<Senador> findByUfAndSiglaPartido(@Param("uf") String uf, @Param("siglaPartido") String siglaPartido);
    
    /**
     * Verifica se existe senador com o mesmo código.
     */
    boolean existsByCodigo(String codigo);
    
    /**
     * Busca senadores por primeira legislatura.
     */
    List<Senador> findByPrimeiraLegislaturaNumero(String primeiraLegislaturaNumero);
    
    /**
     * Busca senadores por segunda legislatura.
     */
    List<Senador> findBySegundaLegislaturaNumero(String segundaLegislaturaNumero);
    
    /**
     * Busca senadores que são membros da mesa.
     */
    List<Senador> findByMembroMesaIsNotNull();
    
    /**
     * Busca senadores que são membros da liderança.
     */
    List<Senador> findByMembroLiderancaIsNotNull();
}
