-- Script de criação da tabela de usuários para autenticação e cadastro
-- Esta tabela armazena os dados básicos do perfil do usuário
-- Campos: id, username, email, password (criptografada)

CREATE TABLE IF NOT EXISTS usuario_login (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(50) NOT NULL
);