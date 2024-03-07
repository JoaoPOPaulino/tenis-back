insert into usuario (nome, email, telefone, cpf, datanascimento) 
values
    ('joao', 'joaodev@email', '63999', '111.111.111-11', '2004-09-16'),
    ('maria feia', 'mariafeia@email', '63111', '999.999.999-99', '2000-03-21'),
    ('natalino', 'natalino@email', '63333', '999.999.999-12', '1980-12-25');

insert into fornecedor(nome, email, telefone, data_cadastro) 
values
    ('nike', 'niketop@gmail.com', '999', 'NOW()'),
    ('adidas', 'adidaslegal@gmail.com', '888', 'NOW()');

insert into produto (nome, descricao, quantidade, preco_compra, preco_venda, id_fornecedor)
values 
    ('Nike Air Max', 'Tênis de corrida Nike Air Max', 50, 150.00, 250.00, 1),
    ('Nike Air Force 1', 'Tênis casual Nike Air Force 1', 40, 120.00, 200.00, 1),
    ('Nike Dri-FIT', 'Camiseta Nike Dri-FIT', 30, 25.00, 50.00, 1),
    ('Nike Tech Fleece', 'Moletom Nike Tech Fleece', 20, 80.00, 150.00, 1),
    ('Nike Elite Backpack', 'Mochila Nike Elite', 15, 50.00, 100.00, 1),
    ('Nike Pro Shorts', 'Shorts de compressão Nike Pro', 35, 30.00, 60.00, 1),
    ('Adidas Ultraboost', 'Tênis de corrida Adidas Ultraboost', 55, 160.00, 270.00, 2),
    ('Adidas Stan Smith', 'Tênis casual Adidas Stan Smith', 45, 100.00, 180.00, 2),
    ('Adidas Essentials Tee', 'Camiseta Adidas Essentials', 25, 20.00, 40.00, 2),
    ('Adidas Originals Hoodie', 'Moletom Adidas Originals', 18, 70.00, 130.00, 2),
    ('Adidas Classic Backpack', 'Mochila Adidas Classic', 12, 40.00, 90.00, 2),
    ('Adidas 3-Stripes Shorts', 'Shorts Adidas 3-Stripes', 30, 25.00, 50.00, 2);