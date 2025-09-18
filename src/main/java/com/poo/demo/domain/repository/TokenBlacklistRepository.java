package com.poo.demo.domain.repository;

import com.poo.demo.domain.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository para gerenciar tokens na blacklist.
 * Fornece operações para invalidar e verificar tokens.
 */
@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, String> {

    /**
     * Verifica se um token está na blacklist.
     * 
     * @param jti JWT ID do token
     * @return true se o token está invalidado
     */
    boolean existsByJti(String jti);

    /**
     * Busca um token na blacklist pelo JTI.
     * 
     * @param jti JWT ID do token
     * @return Optional contendo o token, se encontrado
     */
    Optional<TokenBlacklist> findByJti(String jti);

    /**
     * Remove tokens expirados da blacklist.
     * Operação de limpeza para manter a base de dados otimizada.
     * 
     * @param now timestamp atual
     * @return número de tokens removidos
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM TokenBlacklist t WHERE t.tokenExpiresAt < :now")
    int deleteExpiredTokens(@Param("now") LocalDateTime now);

    /**
     * Invalida todos os tokens de um usuário específico.
     * Útil para logout em massa ou em caso de comprometimento de segurança.
     * 
     * @param username nome do usuário
     * @param reason motivo da invalidação
     * @param now timestamp atual
     * @return número de tokens invalidados
     */
    @Modifying
    @Transactional
    @Query("UPDATE TokenBlacklist t SET t.invalidatedAt = :now, t.reason = :reason " +
           "WHERE t.username = :username AND t.invalidatedAt IS NULL")
    int invalidateAllUserTokens(@Param("username") String username, 
                                @Param("reason") String reason, 
                                @Param("now") LocalDateTime now);
}
