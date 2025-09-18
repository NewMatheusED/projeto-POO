-- Script de criação da tabela de usuários para autenticação e cadastro
-- Esta tabela armazena os dados básicos do perfil do usuário
-- Campos: id, username, email, password (criptografada)
-- Compatível com PostgreSQL

CREATE TABLE IF NOT EXISTS usuario_login (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar índice para melhor performance nas consultas por email
CREATE INDEX IF NOT EXISTS idx_usuario_login_email ON usuario_login(email);

-- Criar índice para melhor performance nas consultas por username
CREATE INDEX IF NOT EXISTS idx_usuario_login_username ON usuario_login(username);

-- Trigger para atualizar updated_at automaticamente
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_usuario_login_updated_at 
    BEFORE UPDATE ON usuario_login 
    FOR EACH ROW 
    EXECUTE FUNCTION update_updated_at_column();
