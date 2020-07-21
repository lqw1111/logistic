ALTER TABLE parcel ADD comment varchar(300) NULL COMMENT 'for admin';

ALTER TABLE order_history ADD create_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE order_history ADD modified_at timestamp DEFAULT current_timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE order_history ADD deleted boolean DEFAULT false NOT NULL;

ALTER TABLE parcel DROP modified_at;
ALTER TABLE parcel ADD modified_at timestamp DEFAULT current_timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE user_order DROP modified_at;
ALTER TABLE user_order ADD modified_at timestamp DEFAULT current_timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP;
