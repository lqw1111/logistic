DROP TABLE IF EXISTS user_order;
CREATE TABLE user_order
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    from_address varchar(255),
    to_address varchar(255),
    status_id int,
    price decimal,
    description varchar(255)
);