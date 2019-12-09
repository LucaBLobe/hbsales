alter table cad_categoria drop constraint nome_fornecedor_id;

alter table cad_categoria alter column codigo_categoria varchar(10);

alter table cad_categoria alter column nome_categoria varchar(50);

alter table cad_fornecedor alter column telefone_contato varchar(13);

alter table cad_categoria add constraint nome_fornecedor_id unique (nome_categoria,fornecedor_id);


