CREATE TABLE cart(

    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_user BIGINT,
    email VARCHAR(255),
    created_date DATETIME,
    price DOUBLE
)