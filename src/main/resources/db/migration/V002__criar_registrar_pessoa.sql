create table pessoa(
codigo bigint primary key auto_increment,
nome varchar(50) not null,
logradouro varchar(255) ,
numero varchar(20) ,
complemento varchar(50) ,
bairro varchar(50),
cep varchar(20) ,
cidade varchar(60),
estado varchar(60),
ativo boolean Not null
);

insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Romario Ribeiro', 'Espanha', '357', 'casa', 'jardim das flores', '89713121', 'Sabaudia', 'PR', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Mario Ribeiro', 'Larajau', '300', 'casa', 'centro', '89713121', 'Sabaudia', 'PR', false);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Larissa souza', 'Alemanha', '350', 'casa', 'jardim das algacia', '89713121', 'Sabaudia', 'PR', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('João Silva', 'Portugal', '347', 'casa', 'jardim dos passaros', '89713121', 'Sabaudia', 'PR', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Maria das Couves', 'Canada', '317', 'casa', 'jardim', '89713121', 'Sabaudia', 'PR', false);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('José da Silva', 'São Paulo', '257', 'casa', 'jardim da acacia', '89713121', 'Sabaudia', 'PR', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Jessica Fernanda', 'Paulista', '157', 'casa', 'jardim do giraçou', '89713121', 'Sabaudia', 'PR', true);
insert into pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Hosana  Andrade', 'Espanha', '457', 'casa', 'jardim das flores', '89713121', 'Sabaudia', 'PR', false);