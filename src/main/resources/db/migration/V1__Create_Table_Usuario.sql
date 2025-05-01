CREATE TABLE IF NOT EXISTS tb_usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    conta_criada TIMESTAMP,
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN
);
