create table categoria (
codigo bigint primary key auto_increment,
nome varchar(50) not null

)Engine=InnoDB default charset=utf8;

insert into categoria (nome) values ('Lazer');
insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Supermercado');
insert into categoria (nome) values ('Farmacia');
insert into categoria (nome) values ('Outros');