
create table cad_fornecedor
(
    id    BIGINT IDENTITY (1, 1) PRIMARY KEY NOT NULL,
    razao_social VARCHAR(255)       NOT NULL,
    cnpj VARCHAR(25)                NOT NULL,
    nome_fantasia  VARCHAR(100)     NOT NULL,
    endereco  VARCHAR(255)          NOT NULL,
    telefone_contato VARCHAR(14)    NOT NULL,
    Email VARCHAR(100)              NOT NULL
);

create unique index ix_cad_fornecedor_01 on cad_fornecedor (razao_social asc);
