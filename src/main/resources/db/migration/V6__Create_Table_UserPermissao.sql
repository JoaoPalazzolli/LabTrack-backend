CREATE TABLE IF NOT EXISTS tb_user_permissao (
    user_id UUID NOT NULL,
    permissao_id UUID NOT NULL,
    PRIMARY KEY (user_id, permissao_id),
    FOREIGN KEY (user_id) REFERENCES tb_usuario(id),
    FOREIGN KEY (permissao_id) REFERENCES tb_permissao(id)
);