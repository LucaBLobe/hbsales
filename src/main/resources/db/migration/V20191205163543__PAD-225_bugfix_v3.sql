drop index cad_fornecedor.ix_cad_fornecedor_01

drop index cad_fornecedor.ix_cad_fornecedor_02

alter table cad_fornecedor alter column razao_social varchar(100)

alter table cad_fornecedor alter column cnpj varchar(14)

alter table cad_fornecedor alter column endereco varchar(100)

alter table cad_fornecedor alter column telefone_contato varchar(12)

alter table cad_fornecedor alter column Email varchar(50)

alter table cad_fornecedor alter column Email varchar(50)

create unique index ix_cad_fornecedor_02 on cad_fornecedor (cnpj asc);
