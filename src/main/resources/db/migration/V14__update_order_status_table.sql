DROP TABLE IF EXISTS order_status;
CREATE TABLE order_status
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL
);
insert into order_status (id, name) values (1, 'NEW');
insert into order_status (id, name) values (2, 'SUBMIT');
insert into order_status (id, name) values (3, 'APPROVED');
insert into order_status (id, name) values (4, 'CLOSED');
insert into order_status (id, name) values (5, 'PROCESSING');
insert into order_status (id, name) values (6, 'FINISH');