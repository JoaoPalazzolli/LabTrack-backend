INSERT INTO tb_permissao (id, descricao)
VALUES
    (gen_random_uuid(), 'ADMIN'),
    (gen_random_uuid(), 'COMMON_USER')
ON CONFLICT (descricao) DO NOTHING;
