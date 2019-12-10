alter table cad_categoria drop constraint nome_fornecedor_id;


alter table cad_categoria alter column codigo_categoria varchar(10) not null;

alter table cad_categoria alter column nome_categoria varchar(50) not null;


alter table cad_categoria add constraint nome_fornecedor_id unique (nome_categoria,fornecedor_id);


alter table linha_categoria drop constraint nome_categoria_id;


alter table linha_categoria alter column cod_linha_categoria varchar(10) not null;

alter table linha_categoria alter column nome_linha_categoria varchar(50) not null;


alter table linha_categoria add constraint nome_categoria_id unique (nome_linha_categoria,categoria_id);