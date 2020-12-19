CREATE TABLE invitation_activity
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    order_id int NOT NULL,
    user_id int NOT NULL,
    start_time timestamp DEFAULT current_timestamp NOT NULL,
    order_code varchar(100) NOT NULL,
    total_discount_price decimal(10,2) NOT NULL,
    per_user_discount_price decimal(10,2) NOT NULL,
    invited_user_num int DEFAULT 0 NOT NULL,
    invited_user_email blob,
    create_at timestamp DEFAULT current_timestamp,
    modified timestamp DEFAULT current_timestamp,
    deleted boolean DEFAULT '0' NOT NULL,
    deleted_at timestamp DEFAULT NULL
);

CREATE TABLE invitation_activity_user
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    order_code varchar(100) NOT NULL
);
