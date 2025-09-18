package com.poo.demo.application;

import com.poo.demo.domain.entity.TokenBlacklist;
import com.poo.demo.domain.repository.TokenBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Serviço para gerenciar blacklist de tokens JWT.
 * Implementa segurança adicional para logout e invalidação de tokens.
 */
@Service
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Autowired
    public TokenBlacklistService(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    /**
     * Adiciona um token à blacklist.
     * 
     * @param jti JWT ID do token
     * @param username nome do usuário
     * @param tokenExpiresAt timestamp de expiração do token
     * @param reason motivo da invalidação
     */
    public void addToBlacklist(String jti, String username, LocalDateTime tokenExpiresAt, String reason) {
        TokenBlacklist blacklistEntry = new TokenBlacklist(
            jti, 
            username, 
            LocalDateTime.now(), 
            tokenExpiresAt, 
            reason
        );
        tokenBlacklistRepository.save(blacklistEntry);
    }

    /**
     * Verifica se um token está na blacklist.
     * 
     * @param jti JWT ID do token
     * @return true se o token está invalidado
     */
    public boolean isTokenBlacklisted(String jti) {
        return tokenBlacklistRepository.existsByJti(jti);
    }

    /**
     * Obtém informações de um token na blacklist.
     * 
     * @param jti JWT ID do token
     * @return Optional contendo informações do token, se encontrado
     */
    public Optional<TokenBlacklist> getBlacklistEntry(String jti) {
        return tokenBlacklistRepository.findByJti(jti);
    }

    /**
     * Invalida todos os tokens de um usuário.
     * Útil para logout em massa ou em caso de comprometimento de segurança.
     * 
     * @param username nome do usuário
     * @param reason motivo da invalidação
     */
    public void invalidateAllUserTokens(String username, String reason) {
        tokenBlacklistRepository.invalidateAllUserTokens(username, reason, LocalDateTime.now());
    }

    /**
     * Remove tokens expirados da blacklist.
     * Executado automaticamente via scheduler para manter a base otimizada.
     */
    @Scheduled(fixedRate = 3600000) // Executa a cada hora
    @Transactional
    public void cleanupExpiredTokens() {
        int removedCount = tokenBlacklistRepository.deleteExpiredTokens(LocalDateTime.now());
        if (removedCount > 0) {
            System.out.println("Limpeza de tokens expirados: " + removedCount + " tokens removidos da blacklist");
        }
    }
}
