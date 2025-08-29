-- Migração V1: Criação da tabela senadores
-- Baseada exatamente no SenadorDto existente
-- Segue as melhores práticas de nomenclatura e estrutura

CREATE TABLE senadores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(100),
    nome VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(500),
    sexo VARCHAR(10),
    partido VARCHAR(100),
    uf VARCHAR(2),
    email VARCHAR(255),
    url_foto VARCHAR(500),
    url_pagina VARCHAR(500),
    sigla_partido VARCHAR(100),
    uf_parlamentar VARCHAR(2),
    membro_mesa VARCHAR(100),
    membro_lideranca VARCHAR(100),
    bloco JSON,
    codigo_mandato VARCHAR(100),
    uf_parlamentar_mandato VARCHAR(2),
    descricao_participacao VARCHAR(500),
    primeira_legislatura_numero VARCHAR(100),
    primeira_legislatura_data_inicio VARCHAR(100),
    primeira_legislatura_data_fim VARCHAR(100),
    segunda_legislatura_numero VARCHAR(100),
    segunda_legislatura_data_inicio VARCHAR(100),
    segunda_legislatura_data_fim VARCHAR(100),
    suplentes JSON,
    exercicios JSON,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    versao BIGINT DEFAULT 0,
    
    -- Índices para melhorar performance
    INDEX idx_senadores_codigo (codigo),
    INDEX idx_senadores_nome (nome),
    INDEX idx_senadores_uf (uf),
    INDEX idx_senadores_partido (partido),
    INDEX idx_senadores_sigla_partido (sigla_partido),
    INDEX idx_senadores_uf_partido (uf, partido),
    INDEX idx_senadores_uf_sigla_partido (uf, sigla_partido),
    INDEX idx_senadores_primeira_legislatura (primeira_legislatura_numero),
    INDEX idx_senadores_segunda_legislatura (segunda_legislatura_numero),
    INDEX idx_senadores_membro_mesa (membro_mesa),
    INDEX idx_senadores_membro_lideranca (membro_lideranca),
    
    -- Constraints de validação
    CONSTRAINT chk_senadores_uf CHECK (uf IS NULL OR uf REGEXP '^[A-Z]{2}$'),
    CONSTRAINT chk_senadores_uf_parlamentar CHECK (uf_parlamentar IS NULL OR uf_parlamentar REGEXP '^[A-Z]{2}$'),
    CONSTRAINT chk_senadores_uf_parlamentar_mandato CHECK (uf_parlamentar_mandato IS NULL OR uf_parlamentar_mandato REGEXP '^[A-Z]{2}$'),
    CONSTRAINT chk_senadores_email CHECK (email IS NULL OR email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
