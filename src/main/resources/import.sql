insert into fornecedor(nome_empresa, email, telefone, data_cadastro) 
values
    ('nike', 'niketop@gmail.com', '999', CURRENT_DATE),
    ('adidas', 'adidaslegal@gmail.com', '888', CURRENT_DATE);

insert into marca(nome, data_cadastro) 
values 
('adidas', CURRENT_DATE), 
('nike', CURRENT_DATE);

insert into tamanho(numeracao, tamanho_em_cm, data_cadastro) 
values 
(37, '25', CURRENT_DATE),
(38, '25,5', CURRENT_DATE),
(39, '26', CURRENT_DATE),
(40, '26,5', CURRENT_DATE),
(41, '27', CURRENT_DATE),
(42, '27,5', CURRENT_DATE),
(43, '28', CURRENT_DATE);

insert into cor(nome, data_cadastro)
values
('preto', CURRENT_DATE),
('branco', CURRENT_DATE);

-- ADICIONANDO CLIENTE
insert into usuario (username, `password`) 
values ('joao', 'Z7dL+3VaMV++fdWH0b8S3NV26muviRKuWXNk5ayr2RVBF9BE8tMorc/G7NB1P51lHzLhjc7irjXu+Q5f3T997w==');

insert into pessoafisica (cpf, nome, telefone, data_nascimento, id_usuario)
values ('999.999.999-00','João Víttor O','(99)9999-9999', CURRENT_DATE, 1);

insert into cliente (saldo, id_pessoa_fisica)
values (1000.00, 1);

insert into endereco (cep, rua, complemento, id_cliente)
values ('77001-000', 'Com asfalto', 'casa 2', 1);

-- ADICIONANDO FUNCIONARIO
insert into usuario (username, `password`) 
values ('rona', 'xMjCHZuQU+YIM0rmuq63vX4UgfwSDSsKE+9a+njtZWkjyD9dE9q6eZP7S5DMoRKXICJ//q4op6+AUmEVeMzkyw==');

insert into pessoafisica (cpf, nome, telefone, data_nascimento, id_usuario)
values ('000.111.222-33','Ronaldo','(00)0000-0000', CURRENT_DATE, 2);

insert into funcionario(codigo_contrato, data_admissao, id_pessoa_fisica)
values ('PJ#0001', CURRENT_DATE, 2);

-- nao adiciona por causa da criação das tabelas geradas pelo ORM

-- insert into meia(descricao, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca, id_cor)
-- values
-- ("meia nike", "pares de meias da nike", 10, 50.00, 109.99, 1, 2, 2),
-- ("meia adidas", "pares de meias da adidas", 10, 45.00, 99.99, 2, 1, 2);

-- insert into basqueteira(nome, descricao, peso, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca, id_tamanho)
-- values
-- ('Nike Air Max', 'Tênis de corrida Nike Air Max', 100, 50, 150.00, 250.00, 1, 2, 4),
-- ('Adidas Ultraboost', 'Tênis de corrida Adidas Ultraboost', 120, 55, 160.00, 270.00, 2, 1, 6);


-- insert into produto (nome, descricao, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca)
-- values 
-- ('Nike Air Max', 'Tênis de corrida Nike Air Max', 50, 150.00, 250.00, 1, 2),
--    ('Nike Air Force 1', 'Tênis casual Nike Air Force 1', 40, 120.00, 200.00, 1, 2),
--    ('Nike Dri-FIT', 'Camiseta Nike Dri-FIT', 30, 25.00, 50.00, 1, 2),
--    ('Nike Tech Fleece', 'Moletom Nike Tech Fleece', 20, 80.00, 150.00, 1, 2),
--    ('Nike Elite Backpack', 'Mochila Nike Elite', 15, 50.00, 100.00, 1, 2),
--    ('Nike Pro Shorts', 'Shorts de compressão Nike Pro', 35, 30.00, 60.00, 1, 2),
--    ('Adidas Ultraboost', 'Tênis de corrida Adidas Ultraboost', 55, 160.00, 270.00, 2, 1),
--    ('Adidas Stan Smith', 'Tênis casual Adidas Stan Smith', 45, 100.00, 180.00, 2, 1),
--    ('Adidas Essentials Tee', 'Camiseta Adidas Essentials', 25, 20.00, 40.00, 2, 1),
--    ('Adidas Originals Hoodie', 'Moletom Adidas Originals', 18, 70.00, 130.00, 2, 1),
--    ('Adidas Classic Backpack', 'Mochila Adidas Classic', 12, 40.00, 90.00, 2, 1),
-- ('Adidas 3-Stripes Shorts', 'Shorts Adidas 3-Stripes', 30, 25.00, 50.00, 2, 1);
