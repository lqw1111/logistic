DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  uid int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  role varchar(50)
);
CREATE UNIQUE INDEX user_username_uindex ON user (username);