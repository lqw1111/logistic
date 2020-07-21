ALTER TABLE user ADD create_at timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE user ADD modified_at timestamp DEFAULT current_timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE user ADD deleted boolean DEFAULT false NOT NULL;