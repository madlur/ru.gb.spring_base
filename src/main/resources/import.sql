CREATE TABLE IF NOT EXISTS products(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100) NOT NULL, price INT NOT NULL, description VARCHAR(100) NOT NULL,quantity INT);

INSERT INTO products (id, title,price,description,quantity) VALUES (1,'Мололко', 112, 'Домик в деревне', 3), (2,'Кефир', 80, 'Избенка', 4), (3,'Творог', 56, 'Фермерский', 10),(4,'Сахар', 12, 'Кусковой', 3);


CREATE TABLE IF NOT EXISTS customers(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL);

INSERT INTO customers (id, name) VALUES (1,'Bob'), (2,'John'),(3,'Jason');

CREATE TABLE IF NOT EXISTS customers_products(customer_id BIGINT, product_id BIGINT NOT NULL, FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products(id));

INSERT INTO customers_products (customer_id, product_id) VALUES (1,1),(1,4),(2,2),(2,3),(2,4),(3,1);


/*
4. ** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;

Добавляем 2 таблицы: заказы (обобщенный заказ покупателя, содержащий данные об оплате) и расшифровку заказа по линиям order_lines (ссылка на продукт, его количество в "корзине"/заказе и стоимость)

CREATE TABLE IF NOT EXISTS orders(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, customer_id BIGINT NOT NULL, paid boolean DEFAULT false NOT NULL, created_at TIMESTAMP NOT NULL, FOREIGN KEY (customer_id) REFERENCES customers (id));

CREATE TABLE IF NOT EXISTS order_lines(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, product_id BIGINT NOT NULL, order_id BIGINT NOT NULL, price INT NOT NULL, amount INT DEFAULT 1, created_at TIMESTAMP NOT NULL, FOREIGN KEY (product_id) REFERENCES products(id),  FOREIGN KEY (order_id) REFERENCES orders(id));
*/