insert into categoria (descricao) values ('Refrigerantes');
insert into categoria (descricao) values ('Doces');
insert into categoria (descricao) values ('Frutas');
insert into categoria (descricao) values ('Bebidas Alcoolicas');
insert into categoria (descricao) values ('Pereciveis');
insert into categoria (descricao) values ('Frios');
insert into produto (nome, sku, quantidade_estoque, valor_unitario, categoria_id) values ('Guaraná 2L', 'GUA00123', 10, 2.32, 1);
insert into usuario (nome, email, senha) values ('Maria', 'maria@abadia.com', '123');
insert into cliente (nome, email, tipo, doc_receita_federal) values ('Mercado do Tiao', 'mercado@tiao.com', 'JURIDICA', '10123123000101');

delete from categoria where categoria_pai_id is not null;
delete from categoria;

alter table categoria auto_increment = 1;

insert into categoria (descricao) values ('Informática');
insert into categoria (descricao) values ('Eletrodomésticos');
insert into categoria (descricao) values ('Móveis');

insert into categoria (descricao, categoria_pai_id) values ('Computadores', 1);
insert into categoria (descricao, categoria_pai_id) values ('Notebooks', 1);
insert into categoria (descricao, categoria_pai_id) values ('Tablets', 1);
insert into categoria (descricao, categoria_pai_id) values ('Monitores', 1);
insert into categoria (descricao, categoria_pai_id) values ('Impressoras', 1);
insert into categoria (descricao, categoria_pai_id) values ('Acessórios', 1);

insert into categoria (descricao, categoria_pai_id) values ('Ar condicionados', 2);
insert into categoria (descricao, categoria_pai_id) values ('Fogões', 2);
insert into categoria (descricao, categoria_pai_id) values ('Fornos elétricos', 2);
insert into categoria (descricao, categoria_pai_id) values ('Microondas', 2);
insert into categoria (descricao, categoria_pai_id) values ('Refrigeradores', 2);

insert into categoria (descricao, categoria_pai_id) values ('Cadeiras', 3);
insert into categoria (descricao, categoria_pai_id) values ('Mesas', 3);
insert into categoria (descricao, categoria_pai_id) values ('Racks', 3);
insert into categoria (descricao, categoria_pai_id) values ('Sofás', 3);