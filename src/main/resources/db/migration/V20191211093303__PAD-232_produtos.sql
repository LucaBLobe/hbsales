create table cad_produtos
(
    id    BIGINT IDENTITY (1, 1) NOT NULL,
    cod_produto        VARCHAR(10)           NOT NULL,
    nome_produto       VARCHAR(200)          NOT NULL,
    preco_produto      DECIMAL(10,2)         NOT NULL,
    unidades_caixa     INT                   NOT NULL,
    peso_unitario      DECIMAL(10,2)         NOT NULL,
    unidade_medida     VARCHAR(2)            NOT NULL,
    validade_produto   DATE                  NOT NULL,
    linha_categoria_id BIGINT                NOT NULL,
    FOREIGN KEY (linha_categoria_id) REFERENCES linha_categoria (id)
);

create unique index ix_cad_produtos_01 on cad_produtos ( cod_produto asc);

