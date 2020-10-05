ALTER TABLE user ADD token varchar(100) DEFAULT '' NOT NULL after address;
ALTER TABLE user ADD active boolean DEFAULT false NOT NULL after token;
