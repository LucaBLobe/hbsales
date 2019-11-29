ALTER TABLE categoria_cad
RENAME COLUMN fornecedor_categoria TO fornecedor_id


ALTER TABLE cad_categoria ALTER COLUMN fornecedor_id BIGINT NOT NULL

ALTER TABLE cad_categoria ALTER COLUMN nome_categoria BIGINT NOT UNIQUE

ALTER TABLE cad_categoria ALTER COLUMN nome_categoria BIGINT NOT UNIQUE

alter table cad_categoria add constraint preco_positivo check (preco > 0);




