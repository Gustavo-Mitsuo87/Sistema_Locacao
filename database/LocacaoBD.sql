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

CREATE TABLE carro (
placa CHAR(7) PRIMARY KEY NOT NULL,
modelo VARCHAR(100) NOT NULL,
marca VARCHAR(50) NOT NULL,
ano INT NOT NULL,
-- Aqui eu predefino uma lista na qual ele pode seguir, quando ir pro Java, pode ser tratado como String ou enum msm
status_disponibilidade ENUM("DISPONÍVEL", "ALUGADO", "EM MANUTENÇÃO") NOT NULL,
-- Cada carro está dentro de uma categoria
foreign key(id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE categoria_carro (
id_categoria INT PRIMARY KEY AUTO_INCREMENT,
nome_categoria VARCHAR(80) NOT NULL,
descricao VARCHAR(300) NOT NULL,
-- Quando for pro Java, coloca em BigDecimal porque a precisão dele é exata, n arredonda
preco_diaria DECIMAL(10, 2) NOT NULL,
valor_seguro DECIMAL(10,2) NOT NULL,
valor_caucao DECIMAL(10,2) NOT NULL,
valor_fixo_diaria DECIMAL(10,2) NOT NULL
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
foreign key(cpf_clie) REFERENCES cliente(cpf_clie),
-- Na hora que o cliente faz a reserva, ele precisa informar a categoria
foreign key(id_categoria) REFERENCES categoria(id_categoria),
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
foreign key(cpf_clie) REFERENCES cliente(cpf_clie),
foreign key(placa) REFERENCES carro(placa),
foreign key(id_func) REFERENCES funcionario(id_func),
data_inicio_loc DATE NOT NULL,
data_fim_loc DATE NOT NULL,
valor_seguro DECIMAL(10,2) NOT NULL,
valor_caucao DECIMAL(10,2) NOT NULL,
valor_fixo_diaria DECIMAL(10,2) NOT NULL,
-- No java isso ficará valor total = (qtd_dias X valor_fixo_diaria) + valor_seguro + valor_caucao
valor_total DECIMAL(10,2) NOT NULL
);