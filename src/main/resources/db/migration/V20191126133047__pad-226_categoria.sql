create table cad_categoria

(
    
    id BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    codigo_categoria           VARCHAR(100) NOT NULL,
    nome_categoria             VARCHAR(100) NOT NULL,
    fornecedor_Id BIGINT NOT NULL,
    FOREIGN KEY (fornecedor_Id) REFERENCES cad_fornecedor (id)

);

create unique index ix_cad_categoria_01 on cad_categoria (nome_categoria asc);
