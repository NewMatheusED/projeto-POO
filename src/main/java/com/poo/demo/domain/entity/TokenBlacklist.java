package com.poo.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

/**
 * Entidade para gerenciar tokens JWT invalidados (blacklist).
 * Implementa segurança adicional para logout e invalidação de tokens.
 */
@Entity
public class TokenBlacklist {

    /**
     * JTI (JWT ID) do token invalidado.
     * Usado como chave primária para identificar tokens únicos.
     */
    @Id
    private String jti;

    /**
     * Username do proprietário do token.
     */
    @Column(nullable = false)
    private String username;

    /**
     * Timestamp de quando o token foi invalidado.
     */
    @Column(nullable = false)
    private LocalDateTime invalidatedAt;

    /**
     * Timestamp de expiração original do token.
     * Usado para limpeza automática de tokens expirados.
     */
    @Column(nullable = false)
    private LocalDateTime tokenExpiresAt;

    /**
     * Motivo da invalidação (logout, security, etc.).
     */
    @Column
    private String reason;

    public TokenBlacklist() {}

    public TokenBlacklist(String jti, String username, LocalDateTime invalidatedAt, 
                         LocalDateTime tokenExpiresAt, String reason) {
        this.jti = jti;
        this.username = username;
        this.invalidatedAt = invalidatedAt;
        this.tokenExpiresAt = tokenExpiresAt;
        this.reason = reason;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getInvalidatedAt() {
        return invalidatedAt;
    }

    public void setInvalidatedAt(LocalDateTime invalidatedAt) {
        this.invalidatedAt = invalidatedAt;
    }

    public LocalDateTime getTokenExpiresAt() {
        return tokenExpiresAt;
    }

    public void setTokenExpiresAt(LocalDateTime tokenExpiresAt) {
        this.tokenExpiresAt = tokenExpiresAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
