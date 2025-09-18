-- Script de criação da tabela de blacklist de tokens JWT
-- Esta tabela armazena tokens invalidados para maior segurança
-- Compatível com MySQL e PostgreSQL

CREATE TABLE IF NOT EXISTS token_blacklist (
    jti VARCHAR(255) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    invalidated_at TIMESTAMP NOT NULL,
    token_expires_at TIMESTAMP NOT NULL,
    reason VARCHAR(100)
);

-- Criar índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_token_blacklist_username ON token_blacklist(username);
CREATE INDEX IF NOT EXISTS idx_token_blacklist_expires ON token_blacklist(token_expires_at);
CREATE INDEX IF NOT EXISTS idx_token_blacklist_invalidated ON token_blacklist(invalidated_at);
