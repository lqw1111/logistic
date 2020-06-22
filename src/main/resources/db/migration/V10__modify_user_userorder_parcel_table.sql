ALTER TABLE user ADD wx_id varchar(200) DEFAULT null NULL;
ALTER TABLE user ADD last_active_time timestamp DEFAULT current_timestamp NULL;

ALTER TABLE user_order ADD order_comment varchar(300) NULL;
ALTER TABLE user_order ADD path_info varchar(300) NULL;
ALTER TABLE user_order ADD weight varchar(100) NULL;
ALTER TABLE user_order ADD volumn varchar(100) NULL;
ALTER TABLE user_order ADD expect_delivery_date varchar(200) NULL;
ALTER TABLE user_order ADD create_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE user_order ADD modified_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE user_order ADD payment_info varchar(200) NULL;
ALTER TABLE user_order ADD deleted boolean DEFAULT false  NOT NULL;

ALTER TABLE parcel ADD create_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE parcel ADD modified_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE parcel ADD deleted boolean DEFAULT false  NOT NULL;