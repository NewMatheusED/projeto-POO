-- Script de criação da tabela de usuários para autenticação e cadastro
-- Esta tabela armazena os dados básicos do perfil do usuário
-- Campos: id, username, email, password (criptografada)
-- Compatível com MySQL e PostgreSQL

CREATE TABLE IF NOT EXISTS usuario_login (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Criar índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_usuario_login_email ON usuario_login(email);
CREATE INDEX IF NOT EXISTS idx_usuario_login_username ON usuario_login(username);