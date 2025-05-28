CREATE TABLE IF NOT EXISTS tb_monitoramento_transporte (
    codigo_transporte UUID PRIMARY KEY,
    qr_code_image_url VARCHAR(255) UNIQUE,
    usuario_enviado UUID,
    usuario_recebido UUID,
    data_recebimento TIMESTAMP,
    data_saida TIMESTAMP,
    status_transporte VARCHAR(25),
    CONSTRAINT fk_usuario_enviado FOREIGN KEY (usuario_enviado) REFERENCES tb_usuario(id),
    CONSTRAINT fk_usuario_recebido FOREIGN KEY (usuario_recebido) REFERENCES tb_usuario(id)
);
