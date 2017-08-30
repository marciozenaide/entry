CREATE TABLE tb_venda(
	id_venda BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data DATE NOT NULL,
	loja INT NOT NULL,
	pdv INT NOT NULL,
	status VARCHAR(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_item_venda(
	id_venda BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_item_venda BIGINT(20) NOT NULL,
	produto INT NOT NULL,
	preco_unitario DECIMAL(10,2) NOT NULL,
	desconto DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (id_item_venda) REFERENCES tb_venda(id_venda)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_venda values(1,'2017-06-26 18:41',1,1,'NÃO PROCESSADO');
INSERT INTO tb_venda values(2,'2017-06-27 18:41',2,1,'NÃO PROCESSADO');
INSERT INTO tb_venda values(3,'2017-06-28 18:41',3,1,'NÃO PROCESSADO');
INSERT INTO tb_venda values(4,'2017-06-29 18:41',1,2,'NÃO PROCESSADO');
INSERT INTO tb_venda values(5,'2017-06-30 18:41',2,2,'NÃO PROCESSADO');
INSERT INTO tb_venda values(6,'2017-07-01 18:41',3,2,'NÃO PROCESSADO');
INSERT INTO tb_venda values(7,'2017-07-02 18:41',1,3,'NÃO PROCESSADO');
INSERT INTO tb_venda values(8,'2017-07-03 18:41',2,3,'NÃO PROCESSADO');
INSERT INTO tb_venda values(9,'2017-07-04 18:41',3,3,'NÃO PROCESSADO');
INSERT INTO tb_venda values(10,'2017-07-05 18:41',1,4,'NÃO PROCESSADO');
INSERT INTO tb_venda values(11,'2017-07-06 18:41',2,4,'NÃO PROCESSADO');
INSERT INTO tb_venda values(12,'2017-07-07 18:41',3,4,'NÃO PROCESSADO');
INSERT INTO tb_venda values(13,'2017-07-08 18:41',1,5,'NÃO PROCESSADO');
INSERT INTO tb_venda values(14,'2017-07-09 18:41',2,5,'NÃO PROCESSADO');
INSERT INTO tb_venda values(15,'2017-07-10 18:41',3,5,'NÃO PROCESSADO');
INSERT INTO tb_venda values(16,'2017-07-11 18:41',1,6,'NÃO PROCESSADO');
INSERT INTO tb_venda values(17,'2017-07-12 18:41',2,6,'NÃO PROCESSADO');
INSERT INTO tb_venda values(18,'2017-07-13 18:41',3,6,'NÃO PROCESSADO');
INSERT INTO tb_venda values(19,'2017-07-14 18:41',1,7,'NÃO PROCESSADO');
INSERT INTO tb_venda values(20,'2017-07-15 18:41',2,7,'NÃO PROCESSADO');
INSERT INTO tb_venda values(21,'2017-07-16 18:41',3,7,'NÃO PROCESSADO');
INSERT INTO tb_venda values(22,'2017-07-17 18:41',1,8,'NÃO PROCESSADO');
INSERT INTO tb_venda values(23,'2017-07-17 18:41',2,8,'NÃO PROCESSADO');
INSERT INTO tb_venda values(24,'2017-07-17 18:41',3,8,'NÃO PROCESSADO');

INSERT INTO tb_item_venda values(1,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(2,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(3,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(4,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(5,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(6,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(7,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(8,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(9,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(10,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(11,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(12,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(13,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(14,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(15,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(16,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(17,1,0987654329,1.99,0.00);
INSERT INTO tb_item_venda values(18,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(19,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(20,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(21,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(22,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(23,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(24,1,1234567890,2.99,1.00);
INSERT INTO tb_item_venda values(25,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(26,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(27,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(28,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(29,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(30,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(31,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(32,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(33,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(34,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(35,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(36,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(37,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(38,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(39,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(40,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(41,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(42,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(43,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(44,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(45,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(46,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(47,2,2123456789,3.99,0.99);
INSERT INTO tb_item_venda values(48,2,2123456789,3.99,0.99);








