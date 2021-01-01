ALTER TABLE user_order ADD shipping_method int DEFAULT '1' NOT NULL after expect_delivery_date;
ALTER TABLE user_order ADD delivery_type int DEFAULT '1' NOT NULL after shipping_method;

CREATE TABLE delivery_type
(
    id int NOT NULL,
    name varchar(20) NOT NULL
);
INSERT INTO delivery_type (id, name) VALUES (1, '自取');
INSERT INTO delivery_type (id, name) VALUES (2, '配送');

CREATE TABLE shipping_method
(
    id int NOT NULL,
    name varchar(20) NOT NULL
);
INSERT INTO shipping_method (id, name) VALUES (1, '空运');
INSERT INTO shipping_method (id, name) VALUES (2, '海运');
