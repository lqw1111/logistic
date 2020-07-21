ALTER TABLE order_history MODIFY create_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE order_history MODIFY modified_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE order_history MODIFY deleted tinyint(1) DEFAULT '0';

ALTER TABLE parcel MODIFY create_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE parcel MODIFY deleted tinyint(1) DEFAULT '0';
ALTER TABLE parcel MODIFY modified_at timestamp DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE user MODIFY create_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE user MODIFY modified_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE user MODIFY deleted tinyint(1) DEFAULT '0';

ALTER TABLE user_order MODIFY create_at timestamp DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE user_order MODIFY deleted tinyint(1) DEFAULT '0';
ALTER TABLE user_order MODIFY modified_at timestamp DEFAULT CURRENT_TIMESTAMP;