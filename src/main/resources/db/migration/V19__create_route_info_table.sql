CREATE TABLE route_info
(
    id int NOT NULL,
    name varchar(20) NOT NULL
);

INSERT INTO logistic.route_info (id, name) VALUES (1, '燕文航空挂号-普货');
INSERT INTO logistic.route_info (id, name) VALUES (2, '燕邮宝挂号-特货');
INSERT INTO logistic.route_info (id, name) VALUES (3, '燕文专线追踪小包-普货');
INSERT INTO logistic.route_info (id, name) VALUES (4, '燕文专线追踪小包-特货');
INSERT INTO logistic.route_info (id, name) VALUES (5, '中邮华南E特快');
INSERT INTO logistic.route_info (id, name) VALUES (6, '燕文专线快递-普货');