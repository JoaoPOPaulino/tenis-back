insert into usuario (nome, `login` , email, telefone, cpf, data_nascimento, data_cadastro) 
values
    ('joao', 'joo', 'joaodev@email', '63999', '111.111.111-11', '2004-09-16', CURRENT_DATE),
    ('maria feia', 'mari', 'mariafeia@email', '63111', '999.999.999-99', '2000-03-21', CURRENT_DATE),
    ('natalino', 'natal', 'natalino@email', '63333', '999.999.999-12', '1980-12-25', CURRENT_DATE);

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

insert into basqueteira(nome, descricao, peso, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca, id_tamanho)
values
('Nike Air Max', 'Tênis de corrida Nike Air Max', 100, 50, 150.00, 250.00, 1, 2, 4),
('Adidas Ultraboost', 'Tênis de corrida Adidas Ultraboost', 120, 55, 160.00, 270.00, 2, 1, 6);


-- insert into produto (nome, descricao, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca)
-- values 
--    ('Nike Air Max', 'Tênis de corrida Nike Air Max', 50, 150.00, 250.00, 1, 2),
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
--    ('Adidas 3-Stripes Shorts', 'Shorts Adidas 3-Stripes', 30, 25.00, 50.00, 2, 1);