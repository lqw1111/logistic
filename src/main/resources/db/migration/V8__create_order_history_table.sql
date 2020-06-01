DROP TABLE IF EXISTS order_history;
CREATE TABLE order_history
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    user_order_id int NOT NULL,
    finish_time date,
    comment varchar(300),
    score int
);