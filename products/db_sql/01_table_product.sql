CREATE TABLE product(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand varchar(200) NOT NULL,
    code BIGINT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE,
    type VARCHAR(100) NOT NULL
);