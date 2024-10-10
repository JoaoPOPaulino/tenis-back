--insert into fornecedor(nome_empresa, email, telefone, data_cadastro) 
--values
    --('nike', 'niketop@gmail.com', '999', CURRENT_DATE),
    --('meia', 'meialegal@gmail.com', '888', CURRENT_DATE);

insert into marca(nome) 
values 
('nike'), 
('adidas');

--insert into tamanho(numeracao, tamanho_em_cm, data_cadastro) 
--values 
--(37, '25', CURRENT_DATE),
--(38, '25,5', CURRENT_DATE),
--(39, '26', CURRENT_DATE),
--(40, '26,5', CURRENT_DATE),
--(41, '27', CURRENT_DATE),
--(42, '27,5', CURRENT_DATE),
--(43, '28', CURRENT_DATE);

--insert into cor(nome, data_cadastro)
--values
--('preto', CURRENT_DATE),
--('branco', CURRENT_DATE);

-- ADICIONANDO CLIENTE
--insert into usuario (username, `password`)
--values ('joao', 'Z7dL+3VaMV++fdWH0b8S3NV26muviRKuWXNk5ayr2RVBF9BE8tMorc/G7NB1P51lHzLhjc7irjXu+Q5f3T997w==');

--#insert into pessoafisica (cpf, nome, telefone, data_nascimento, id_usuario)
--values ('999.999.999-00','João Víttor O','(99)9999-9999', CURRENT_DATE, 1);

--insert into cliente (saldo, id_pessoa_fisica)
--values (1000.00, 1);

--insert into endereco (cep, rua, complemento, id_cliente)
--values ('77001-000', 'Com asfalto', 'casa 2', 1);

-- ADICIONANDO FUNCIONARIO
--insert into usuario (username, `password`) 
--values ('rona', 'xMjCHZuQU+YIM0rmuq63vX4UgfwSDSsKE+9a+njtZWkjyD9dE9q6eZP7S5DMoRKXICJ//q4op6+AUmEVeMzkyw==');

--insert into pessoafisica (cpf, nome, telefone, data_nascimento, id_usuario)
--values ('000.111.222-33','Ronaldo','(00)0000-0000', CURRENT_DATE, 2);

--insert into funcionario(codigo_contrato, data_admissao, id_pessoa_fisica)
--values ('PJ#0001', CURRENT_DATE, 2);

--insert into produto (nome, descricao, quantidade, preco_compra, preco_venda, id_fornecedor, id_marca)
--values 
--('Nike Air Max', 'Tênis de corrida Nike Air Max', 50, 150.00, 250.00, 1, 2),
--('Nike Air Force 1', 'Tênis casual Nike Air Force 1', 40, 120.00, 200.00, 1, 2),
--('Nike Dri-FIT', 'Camiseta Nike Dri-FIT', 30, 25.00, 50.00, 1, 2),
--('Nike Tech Fleece', 'Moletom Nike Tech Fleece', 20, 80.00, 150.00, 1, 2),
--('Nike Elite Backpack', 'Mochila Nike Elite', 15, 50.00, 100.00, 1, 2),
--('Nike Pro Shorts', 'Shorts de compressão Nike Pro', 35, 30.00, 60.00, 1, 2),
--('Meia Ultraboost', 'Meia Ultraboost', 55, 160.00, 270.00, 2, 1),
--('Meia Stan Smith', 'Meia Stan Smith', 45, 100.00, 180.00, 2, 1),
--('Meia Essentials Tee', 'Meia Essentials', 25, 20.00, 40.00, 2, 1),
--('Meia Originals Hoodie', 'Meia Originals', 18, 70.00, 130.00, 2, 1),
--('Meia Classic Backpack', 'Meia Classic', 12, 40.00, 90.00, 2, 1),
--('Meia 3-Stripes Shorts', 'Meia 3-Stripes', 30, 25.00, 50.00, 2, 1);

--insert into meia (id, qtd_pares, id_cor)
--values
--(7, 4, 1),
--(8, 2, 2),
--(9, 6, 1),
--(10, 1, 2),
--(11, 2, 1),
--(12, 2, 2);

--insert into basqueteira (id, tamanho_cano, id_tamanho, peso)
--values
--(1, 2, 2, 200),
--(2, 0, 1, 320),
--(3, 2, 3, 190),
--(4, 1, 4, 450),
--(5, 0, 2, 220),
--(6, 1, 1, 210);

-- Inserindo um registro na tabela usuario
--INSERT INTO usuario (id, nome, email, login, senha, tipo_usuario)
--VALUES (1, 'João da Silva', 'joao.silva@example.com', 'joao123', 'senha123', 1);

INSERT INTO usuario (id, nome, email, login, senha, tipo_usuario) 
VALUES (1, 'João da Silva', 'joao.silva@example.com', 'joao123', 'senha123', 'ADMINISTRADOR');


