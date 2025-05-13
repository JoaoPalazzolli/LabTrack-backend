CREATE TABLE IF NOT EXISTS tb_inventario_item (
    codigo_item UUID PRIMARY KEY,
    descricao VARCHAR(255),
    tipo_item VARCHAR(20),
    quantidade NUMERIC(19,2),
    unidade VARCHAR(2),
    local_laboratorio VARCHAR(100),
    fornecedor VARCHAR(100),
    condicoes_armazenamento VARCHAR(255) NOT NULL,
    classificacao_risco VARCHAR(10) NOT NULL,
    data_fabricacao DATE NOT NULL,
    data_vencimento DATE,
    data_registro TIMESTAMP NOT NULL,
    ultima_alteracao_data TIMESTAMP,
    usuario_id UUID,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id)
);