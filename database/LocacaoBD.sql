CREATE DATABASE locacao_carro;
USE locacao_carro;

CREATE TABLE cliente (
cpf_clie VARCHAR(14) PRIMARY KEY NOT NULL,
nome_clie VARCHAR(80) NOT NULL,
cnh  VARCHAR(11) NOT NULL,
-- Vai ter que converter quando chegar no Java..
data_nasc_clie DATE NOT NULL,
telefone_clie VARCHAR(11) NOT NULL,
email_clie VARCHAR(80) NOT NULL
);

CREATE TABLE categoria_carro (
id_categoria INT PRIMARY KEY AUTO_INCREMENT,
nome_categoria VARCHAR(80) NOT NULL,
descricao VARCHAR(300) NOT NULL,
-- Quando for pro Java, coloca em BigDecimal porque a precisão dele é exata, n arredonda
valor_seguro DECIMAL(10,2) NOT NULL,
valor_caucao DECIMAL(10,2) NOT NULL,
valor_fixo_diaria DECIMAL(10,2) NOT NULL
);

CREATE TABLE carro (
placa CHAR(7) PRIMARY KEY NOT NULL,
modelo VARCHAR(100) NOT NULL,
marca VARCHAR(50) NOT NULL,
ano INT NOT NULL,
-- Aqui eu predefino uma lista na qual ele pode seguir, quando ir pro Java, pode ser tratado como String ou enum msm
status_disponibilidade ENUM("DISPONÍVEL", "ALUGADO", "EM MANUTENÇÃO") NOT NULL,
-- Cada carro está dentro de uma categoria
id_categoria INT NOT NULL, foreign key(id_categoria) REFERENCES categoria_carro(id_categoria)
);

CREATE TABLE funcionario (
id_func INT PRIMARY KEY AUTO_INCREMENT,
nome_func VARCHAR(100) NOT NULL,
cpf_func VARCHAR(14) NOT NULL,
data_nasc_func DATE NOT NULL,
email_func VARCHAR(80) NOT NULL,
telefone_func VARCHAR(11) NOT NULL
);

CREATE TABLE reserva(
codigo_reserva INT PRIMARY KEY,
-- A reserva está no nome do cliente
cpf_clie VARCHAR(14), foreign key(cpf_clie) REFERENCES cliente(cpf_clie),
-- Na hora que o cliente faz a reserva, ele precisa informar a categoria
id_categoria INT NOT NULL, foreign key(id_categoria) REFERENCES categoria_carro(id_categoria),
-- Quandp a reserva foi feita
data_reserva DATE NOT NULL,
-- A data de inicio e fim que o cliente pretende alugar o carro
data_inicio_reserva DATE NOT NULL,
data_fim_reserva DATE NOT NULL,
-- Aqui também fica a critério de virar String ou enum. Aqui diz em relação sobre como está a reserva, se ela está ativa, se foi cancelada, ou se foi convertida para locação
status_reserva ENUM("ATIVA", "CONVERTIDA", "CANCELADA") NOT NULL
);

CREATE TABLE locacao(
id_locacao INT PRIMARY KEY AUTO_INCREMENT,
cpf_clie VARCHAR(14), foreign key(cpf_clie) REFERENCES cliente(cpf_clie),
placa CHAR(7), foreign key(placa) REFERENCES carro(placa),
id_func INT NOT NULL, foreign key(id_func) REFERENCES funcionario(id_func),
data_inicio_loc DATE NOT NULL,
data_fim_loc DATE NOT NULL,
valor_seguro DECIMAL(10,2) NOT NULL,
valor_caucao DECIMAL(10,2) NOT NULL,
valor_fixo_diaria DECIMAL(10,2) NOT NULL,
-- No java isso ficará valor total = (qtd_dias X valor_fixo_diaria) + valor_seguro + valor_caucao
-- O valor_locacao é (qtd_dias X valor_fixo_diaria), eu fiz assim para n depender exclusivamente da Categoria, que pode alterar os preços com o tempo, enquanto locações podem ter valores diferentes com o passar do tempo
valor_locacao DECIMAL(10,2) NOT NULL,
valor_total DECIMAL(10,2) NOT NULL
);

ALTER TABLE cliente MODIFY COLUMN telefone_clie VARCHAR(20) NOT NULL;
ALTER TABLE funcionario MODIFY COLUMN telefone_func VARCHAR(20) NOT NULL;

INSERT INTO categoria_carro
(nome_categoria, descricao, valor_seguro, valor_caucao, valor_fixo_diaria)
VALUES
('Econômico', 'Carros compactos e econômicos para uso urbano.',
 35.00, 500.00, 100.00),
('Intermediário', 'Carros maiores e mais confortáveis para viagens.',
 50.00, 700.00, 150.00),
('SUV', 'Veículos utilitários esportivos com maior espaço interno.',
 70.00, 1000.00, 200.00),
('Luxo', 'Veículos premium com maior conforto e desempenho.',
 100.00, 1500.00, 300.00);
 
 INSERT INTO cliente
(cpf_clie, nome_clie, cnh, data_nasc_clie, telefone_clie, email_clie)
VALUES
('111.111.111-11', 'João da Silva', '12345678901',
 '2005-03-15', '11987654321', 'joao@email.com'),
('222.222.222-22', 'Maria Oliveira', '23456789012',
 '2004-07-22', '11976543210', 'maria@email.com'),
('333.333.333-33', 'Carlos Souza', '34567890123',
 '2003-11-08', '11965432109', 'carlos@email.com'),
('444.444.444-44', 'Ana Santos', '45678901234',
 '2006-01-30', '11954321098', 'ana@email.com');
 
 INSERT INTO reserva
(codigo_reserva, cpf_clie, id_categoria, data_reserva,
 data_inicio_reserva, data_fim_reserva, status_reserva)
VALUES
(1001, '111.111.111-11', 1,
 '2026-09-01', '2026-09-10', '2026-09-13', 'ATIVA'),
(1002, '222.222.222-22', 3,
 '2026-09-02', '2026-09-15', '2026-09-20', 'ATIVA'),
(1003, '333.333.333-33', 2,
 '2026-08-25', '2026-09-05', '2026-09-08', 'CONVERTIDA'),
(1004, '444.444.444-44', 4,
 '2026-09-03', '2026-09-25', '2026-09-28', 'CANCELADA');
 
 INSERT INTO carro
(placa, modelo, marca, ano, status_disponibilidade, id_categoria)
VALUES
('ABC1D23', 'Onix', 'Chevrolet', 2023, 'DISPONÍVEL', 1),
('DEF4G56', 'HB20', 'Hyundai', 2024, 'ALUGADO', 1),
('GHI7J89', 'Corolla', 'Toyota', 2022, 'DISPONÍVEL', 2),
('JKL1M23', 'Civic', 'Honda', 2023, 'DISPONÍVEL', 2),
('MNO4P56', 'T-Cross', 'Volkswagen', 2024, 'ALUGADO', 3),
('QRS7T89', 'Compass', 'Jeep', 2023, 'DISPONÍVEL', 3),
('UVW1X23', 'BMW 320i', 'BMW', 2022, 'EM MANUTENÇÃO', 4),
('YZA4B56', 'Mercedes-Benz C180', 'Mercedes-Benz', 2024, 'DISPONÍVEL', 4);