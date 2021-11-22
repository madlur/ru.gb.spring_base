CREATE TABLE IF NOT EXISTS products(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100) NOT NULL, price INT NOT NULL, description VARCHAR(100) NOT NULL,quantity INT);

INSERT INTO products (id, title,price,description,quantity) VALUES (1,'Мололко', 112, 'Домик в деревне', 3), (2,'Кефир', 80, 'Избенка', 4), (3,'Творог', 56, 'Фермерский', 10),(4,'Сахар', 12, 'Кусковой', 3);
