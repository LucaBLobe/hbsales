create table pedido
(
    id    BIGINT IDENTITY (1, 1) NOT NULL primary key,
    cod_pedido        VARCHAR(10)           NOT NULL,
    status       VARCHAR(25)          NOT NULL,
    criacao_pedido   DATE                  NOT NULL,
    fornecedor_id BIGINT                NOT NULL,
    FOREIGN KEY (fornecedor_id) REFERENCES cad_fornecedor (id)

);