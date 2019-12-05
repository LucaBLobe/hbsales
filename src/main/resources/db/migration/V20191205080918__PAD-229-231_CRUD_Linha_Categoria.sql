create table linha_categoria

(
    
    id BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    cod_linha_categoria              VARCHAR(100) NOT NULL,
    nome_linha_categoria             VARCHAR(100) NOT NULL,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES cad_categoria (id)

);

alter table linha_categoria add constraint nome_categoria_id unique (nome_linha_categoria,categoria_id)

