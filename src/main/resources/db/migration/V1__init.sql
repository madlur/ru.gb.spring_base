CREATE TABLE IF NOT EXISTS products(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price INT NOT NULL);


INSERT INTO products (id, title,price)
VALUES
       (1,'Мололко', 112),
       (2,'Кефир', 80),
       (3,'Творог', 56),
       (4,'Сахар', 12),
       (5,'Cыр', 112);
