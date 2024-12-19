-- Inserir Estados
INSERT INTO estado (nome, sigla, ativo) VALUES
('Acre', 'AC', true),
('Alagoas', 'AL', true),
('Amapá', 'AP', true),
('Amazonas', 'AM', true),
('Bahia', 'BA', true),
('Ceará', 'CE', true),
('Distrito Federal', 'DF', true),
('Espírito Santo', 'ES', true),
('Goiás', 'GO', true),
('Maranhão', 'MA', true),
('Mato Grosso', 'MT', true),
('Mato Grosso do Sul', 'MS', true),
('Minas Gerais', 'MG', true),
('Pará', 'PA', true),
('Paraíba', 'PB', true),
('Paraná', 'PR', true),
('Pernambuco', 'PE', true),
('Piauí', 'PI', true),
('Rio de Janeiro', 'RJ', true),
('Rio Grande do Norte', 'RN', true),
('Rio Grande do Sul', 'RS', true),
('Rondônia', 'RO', true),
('Roraima', 'RR', true),
('Santa Catarina', 'SC', true),
('São Paulo', 'SP', true),
('Sergipe', 'SE', true),
('Tocantins', 'TO', true);

-- Inserir Cidades
INSERT INTO cidade (nome, estado_id) VALUES
('Palmas', 1),        -- Tocantins
('São Paulo', 2),     -- São Paulo
('Rio de Janeiro', 3), -- Rio de Janeiro
('Belo Horizonte', 4), -- Minas Gerais
('Goiânia', 5);       -- Goiás

-- Inserir Marcas
INSERT INTO marca (nome) VALUES
('Nike'),
('Adidas'),
('Under Armour'),
('Jordan'),
('Puma'),
('Reebok'),
('New Balance'),
('Fila');

-- Inserir Fornecedores
INSERT INTO fornecedor (nome, cnpj) VALUES 
('Nike Brasil LTDA', '12345678000190'), 
('Adidas Brasil LTDA', '98765432000190'), 
('Under Armour Brasil', '45678912000190'), 
('Jordan Brasil Imports', '78912345000190'), 
('Puma Sports Brasil', '32165498000190'), 
('Reebok Brasil LTDA', '90123456000190'), 
('New Balance Brasil', '23456789000190'), 
('Fila Brasil LTDA', '56789012300190');

-- Inserir alguns usuários (senha exemplo: "Senha123")
INSERT INTO usuario (nome, email, login, senha, tipo_usuario) VALUES
('Admin Sistema', 'admin@email.com', 'admin', 'owA4ynbT13Pbe3aXyiXnw3xoH70CCnnbTZdv2zs2EuLrxlzwCByAEAx8JulcRn/+gGwuqaNXhlIuWwniXanK0Q==', 'ADMINISTRADOR'),
('Usuário Comum', 'usuario@email.com', 'usuario', '$2a$10$YourHashedPasswordHere', 'USUARIO'),
('João Silva', 'joao@email.com', 'joao', '$2a$10$YourHashedPasswordHere', 'USUARIO'),
('Maria Santos', 'maria@email.com', 'maria', '$2a$10$YourHashedPasswordHere', 'USUARIO');

-- Inserir Telefones
INSERT INTO telefone (ddd, numero) VALUES
('63', '999999999'),
('11', '988888888'),
('21', '977777777'),
('31', '966666666');

-- Associar Telefones aos Usuários
INSERT INTO usuario_telefone (usuario_id, telefones_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- Inserir Endereços
INSERT INTO endereco (cep, quadra, rua, numero, complemento, cidade_id, usuario_id, principal, ativo) VALUES
('77000000', 'Quadra 1', 'Rua 1', '1', 'Apt 101', 1, 1, true, true),
('01000000', 'Quadra 2', 'Rua 2', '2', 'Casa 2', 2, 2, true, true),
('20000000', 'Quadra 3', 'Rua 3', '3', 'Apt 303', 3, 3, true, true),
('30000000', 'Quadra 4', 'Rua 4', '4', 'Casa 4', 4, 4, true, true);

-- Inserir Tênis
INSERT INTO produto (nome, preco, estoque, fornecedor_id, dtype) VALUES
('Nike LeBron XX', 999.99, 50, 1, 'Tenis'),
('Adidas Harden Vol. 7', 899.99, 45, 2, 'Tenis'),
('Under Armour Curry 10', 849.99, 30, 3, 'Tenis'),
('Air Jordan XXXVII', 1299.99, 25, 4, 'Tenis'),
('Puma MB.02', 799.99, 35, 5, 'Tenis'),
('Reebok Classic Leather', 699.99, 40, 6, 'Tenis'),
('New Balance 574', 799.99, 30, 7, 'Tenis'),
('Fila Disruptor II', 899.99, 25, 8, 'Tenis'),
('Nike Air Max 270', 999.99, 35, 1, 'Tenis'),
('Adidas Yung-1', 799.99, 40, 2, 'Tenis'),
('Under Armour Micro G Pursuit', 699.99, 30, 3, 'Tenis'),
('Air Jordan 1', 1299.99, 25, 4, 'Tenis'),
('Puma Future 4.1', 899.99, 35, 5, 'Tenis'),
('Puma MB.03', 799.99, 35, 5, 'Tenis'),
('Reebok Nano X2', 749.99, 40, 6, 'Tenis'),
('New Balance Fresh Foam 1080', 949.99, 30, 7, 'Tenis'),
('Fila Ray Tracer', 699.99, 25, 8, 'Tenis'),
('Nike Air Max 90', 999.99, 35, 1, 'Tenis'),
('Adidas Ultraboost 22', 1149.99, 40, 2, 'Tenis'),
('Under Armour HOVR Phantom 3', 899.99, 30, 3, 'Tenis'),
('Air Jordan 1 Retro High OG', 1399.99, 25, 4, 'Tenis'),
('Puma RS-X3', 749.99, 35, 5, 'Tenis'),
('Reebok Zig Kinetica 2.5', 799.99, 40, 6, 'Tenis'),
('New Balance 327', 849.99, 30, 7, 'Tenis'),
('Fila Renno', 649.99, 25, 8, 'Tenis'),
('Nike Air Force 1', 799.99, 35, 1, 'Tenis'),
('Adidas Superstar', 699.99, 40, 2, 'Tenis');


-- Atualizar tabela Tenis com informações específicas (sem duplicatas)
INSERT INTO tenis (id, descricao, marca_id, modelo, tamanho) VALUES
(1, 'Tênis signature do LeBron James', 1, 'LeBron XX', 'GG'),
(2, 'Tênis signature do James Harden', 2, 'Harden Vol. 7', 'G'),
(3, 'Tênis signature do Stephen Curry', 3, 'Curry 10', 'M'),
(4, 'Air Jordan nova geração', 4, 'XXXVII', 'GG'),
(5, 'Tênis signature do LaMelo Ball', 5, 'MB.02', 'G'),
(6, 'Tênis clássico casual', 6, 'Classic Leather', 'M'),
(7, 'Tênis lifestyle retrô', 7, '574', 'G'),
(8, 'Tênis chunky lifestyle', 8, 'Disruptor II', 'M'),
(9, 'Tênis lifestyle com amortecimento Air', 1, 'Air Max 270', 'GG'),
(10, 'Tênis retrô runner', 2, 'Yung-1', 'G'),
(11, 'Tênis para corrida leve', 3, 'Micro G Pursuit', 'M'),
(12, 'Tênis clássico basquete', 4, '1', 'GG'),
(13, 'Tênis para futebol', 5, 'Future 4.1', 'G'),
(14, 'Tênis para CrossFit', 6, 'Nano X2', 'M'),
(15, 'Tênis para corrida de alto desempenho', 7, 'Fresh Foam 1080', 'G'),
(16, 'Tênis lifestyle moderno', 8, 'Ray Tracer', 'M'),
(17, 'Tênis para corrida premium', 2, 'Ultraboost 22', 'G'),
(18, 'Tênis para corrida com tecnologia HOVR', 3, 'HOVR Phantom 3', 'M'),
(19, 'Tênis clássico Jordan retro', 4, '1 Retro High OG', 'GG'),
(20, 'Tênis lifestyle com design futurista', 5, 'RS-X3', 'G'),
(21, 'Tênis com tecnologia Zig', 6, 'Zig Kinetica 2.5', 'M'),
(22, 'Tênis lifestyle retrô-moderno', 7, '327', 'G'),
(23, 'Tênis lifestyle contemporâneo', 8, 'Renno', 'M'),
(24, 'Tênis clássico urbano', 1, 'Air Force 1', 'G'),
(25, 'Tênis clássico lifestyle', 2, 'Superstar', 'M');

-- Inserir alguns Cartões
INSERT INTO cartao (tipo_cartao, numero, cvv, validade, titular, cpf, usuario_id, ativo) VALUES
('CREDITO', '1111222233334444', '123', '2025-12-31', 'Admin Sistema', '12345678900', 1, true),
('DEBITO', '4444333322221111', '456', '2025-12-31', 'Usuário Comum', '98765432100', 2, true);

-- Criando alguns pedidos
INSERT INTO pedido (data, usuario_id, total, endereco_id, cartao_id) VALUES
-- Pedido 1: Usuário 1 comprando dois tênis
(NOW(), 1, 2299.98, 1, 1),
-- Pedido 2: Usuário 2 comprando um tênis
(NOW() - INTERVAL '1 day', 2, 899.99, 2, 2),
-- Pedido 3: Usuário 3 comprando três tênis
(NOW() - INTERVAL '2 day', 3, 3149.97, 3, NULL),
-- Pedido 4: Usuário 4 comprando um tênis
(NOW() - INTERVAL '3 day', 4, 1299.99, 4, NULL);

-- Criando os itens dos pedidos
INSERT INTO item (quantidade, preco, id_tenis, id_pedido) VALUES
-- Itens do Pedido 1
(1, 999.99, 1, 1),   -- Nike LeBron XX
(1, 1299.99, 4, 1),  -- Air Jordan XXXVII

-- Itens do Pedido 2
(1, 899.99, 2, 2),   -- Adidas Harden Vol. 7

-- Itens do Pedido 3
(2, 849.99, 3, 3),   -- 2x Under Armour Curry 10
(1, 1299.99, 4, 3),  -- Air Jordan XXXVII

-- Itens do Pedido 4
(1, 1299.99, 4, 4);  -- Air Jordan XXXVII

-- Criando os pagamentos
INSERT INTO pagamento (tipo_pagamento, status_pagamento, valor, pedido_id, cartao_id, data_pagamento) VALUES
-- Pagamento do Pedido 1 (Cartão de Crédito)
('CARTAO_CREDITO', 'APROVADO', 2299.98, 1, 1, NOW()),

-- Pagamento do Pedido 2 (Cartão de Débito)
('CARTAO_DEBITO', 'APROVADO', 899.99, 2, 2, NOW() - INTERVAL '1 day'),

-- Pagamento do Pedido 3 (PIX)
('PIX', 'APROVADO', 3149.97, 3, NULL, NOW() - INTERVAL '2 day'),

-- Pagamento do Pedido 4 (Boleto)
('BOLETO', 'PENDENTE', 1299.99, 4, NULL, NOW() - INTERVAL '3 day');

-- Atualizar estoque dos tênis após as vendas
UPDATE produto 
SET estoque = CASE id
    WHEN 1 THEN estoque - 1  -- Nike LeBron XX (-1)
    WHEN 2 THEN estoque - 1  -- Adidas Harden (-1)
    WHEN 3 THEN estoque - 2  -- Curry 10 (-2)
    WHEN 4 THEN estoque - 3  -- Air Jordan (-3)
    ELSE estoque
END
WHERE id IN (1, 2, 3, 4);