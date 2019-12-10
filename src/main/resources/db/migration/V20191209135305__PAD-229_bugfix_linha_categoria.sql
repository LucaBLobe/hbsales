alter table linha_categoria drop constraint nome_categoria_id;

alter table linha_categoria alter column cod_linha_categoria varchar(10)
alter table linha_categoria alter column nome_linha_categoria varchar(50)

alter table linha_categoria add constraint nome_categoria_id unique (nome_linha_categoria,categoria_id)