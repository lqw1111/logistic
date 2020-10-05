CREATE TABLE payment
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    user_email varchar(100),
    user_name varchar(100),
    order_id int NOT NULL,
    promotion_code varchar(100),
    payment_type_id int NOT NULL,
    price decimal(10,2) NOT NULL,
    paid decimal(10,2) NOT NULL,
    actual_paid decimal(10,2),
    validate boolean DEFAULT false NOT NULL,
    paid_at timestamp DEFAULT current_timestamp NOT NULL
);

CREATE TABLE payment_type
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL
);

INSERT INTO logistic.payment_type (id, name) VALUES (1, 'wechat');
INSERT INTO logistic.payment_type (id, name) VALUES (2, 'alipay');
