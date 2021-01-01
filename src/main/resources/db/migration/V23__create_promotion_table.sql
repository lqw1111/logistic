DROP TABLE IF EXISTS promotion;
CREATE TABLE promotion
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    promotion_code varchar(50) NOT NULL,
    user_id int NOT NULL,
    discount int,
    price int,
    promotion_type_id int NOT NULL,
    expire_date timestamp,
    validate boolean DEFAULT true  NOT NULL,
    create_at timestamp DEFAULT current_timestamp NOT NULL
);

DROP TABLE IF EXISTS promotion_type;
CREATE TABLE promotion_type
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL
);

INSERT INTO promotion_type (id,name) VALUES (1,'discount');
INSERT INTO promotion_type (id,name) VALUES (2,'price');
