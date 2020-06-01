DROP TABLE IF EXISTS parcel;
CREATE TABLE parcel
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    order_number varchar(255) NOT NULL COMMENT 'the parcels order on the box',
    sender_name varchar(50) NOT NULL,
    sender_address varchar(255),
    sender_phone varchar(50),
    sender_postcode varchar(50),
    user_id int NOT NULL,
    receiver_name varchar(50) NOT NULL,
    receiver_address varchar(255) NOT NULL,
    receiver_phone varchar(50) NOT NULL,
    receiver_postcode varchar(50) NOT NULL,
    content_type varchar(50),
    description varchar(300),
    parcel_status varchar(50) NOT NULL,
    user_order_id int NOT NULL
);