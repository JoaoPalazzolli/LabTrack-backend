CREATE TABLE IF NOT EXISTS tb_transporte_itens (
    codigo_transporte UUID NOT NULL,
    codigo_item UUID NOT NULL,
    PRIMARY KEY (codigo_transporte, codigo_item),
    FOREIGN KEY (codigo_transporte) REFERENCES tb_monitoramento_transporte(codigo_transporte),
    FOREIGN KEY (codigo_item) REFERENCES tb_inventario_item(codigo_item)
);