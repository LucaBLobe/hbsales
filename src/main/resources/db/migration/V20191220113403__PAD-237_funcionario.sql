create table cad_funcionario
(
    id    BIGINT IDENTITY (1, 1)          NOT NULL,
    nome_funcionario VARCHAR(50)          NOT NULL,
    email            VARCHAR(50)          NOT NULL,
    uuid             VARCHAR(36)          NOT NULL
);

create unique index ix_cad_funcionario_01 on cad_funcionario (nome_funcionario asc);
create unique index ix_cad_funcionario_02 on cad_funcionario (uuid asc);
