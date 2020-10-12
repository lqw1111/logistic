ALTER TABLE user_order DROP issue_message;
ALTER TABLE user_order ADD issue_message varchar(300) NULL;
