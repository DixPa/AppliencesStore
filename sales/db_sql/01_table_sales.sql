CREATE TABLE sales(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_user BIGINT,
    sale_number varchar(200),
    price_total DOUBLE,
    created_date DATETIME
);