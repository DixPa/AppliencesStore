CREATE TABLE cart_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_product BIGINT,
    code BIGINT,
    name VARCHAR(250),
    brand VARCHAR(150),
    price DOUBLE,
    quantity BIGINT,
    id_cart BIGINT,
    FOREIGN KEY (id_cart) REFERENCES `cart` (id)
);