create table cad_venda
(
    id    BIGINT IDENTITY (1, 1)             NOT NULL,
    inicio_vendas      DATE                  NOT NULL,
    fim_vendas         DATE                  NOT NULL,
    retirada_pedido    DATE                  NOT NULL,
    descricao       VARCHAR(50)              NOT NULL,
    fornecedor_id BIGINT                     NOT NULL,
    FOREIGN KEY (fornecedor_Id) REFERENCES cad_fornecedor (id)
);
