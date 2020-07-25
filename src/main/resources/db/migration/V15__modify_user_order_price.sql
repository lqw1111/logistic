ALTER TABLE user_order MODIFY price decimal(10,2);

ALTER TABLE order_history MODIFY deleted boolean DEFAULT false;
ALTER TABLE parcel MODIFY deleted boolean DEFAULT false;
ALTER TABLE user MODIFY deleted boolean DEFAULT false;
ALTER TABLE user_order MODIFY deleted boolean DEFAULT false;