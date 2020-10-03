CREATE TABLE promotion
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    promotion_code varchar(50) NOT NULL,
    user_id int NOT NULL,
    discount int,
    price int,
    promotion_type_id int NOT NULL,
    expire_date timestamp NOT NULL,
    validate boolean DEFAULT false  NOT NULL,
    create_at timestamp DEFAULT current_timestamp NOT NULL
);

CREATE TABLE promotion_type
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL
);

INSERT INTO logistic.promotion_type (id, name) VALUES (1, 'discount');
INSERT INTO logistic.promotion_type (id, name) VALUES (2, 'price');
