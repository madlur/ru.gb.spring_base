CREATE TABLE IF NOT EXISTS products
(
    id    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price INT          NOT NULL
);


INSERT INTO products (id, title, price)
VALUES (1, 'Мололко', 112),
       (2, 'Кефир', 80),
       (3, 'Творог', 56),
       (4, 'Сахар', 12),
       (5, 'Cыр', 112);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT              NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100)        NOT NULL,
    email    VARCHAR(100)

);

INSERT INTO users (id, username, password, email)
VALUES (1, 'admin', '$2y$10$72R3hM0PQn6RwRRLs9Vvn.8qiLQ/..o6gcrvxyfodIs73jJEj23da', 'admin@admin.ru'),
       (2, 'user', '$2y$10$YNIoi4EP1oCoXaEx7y3Utur2hGFyPX32Oskd1thyQ1BxuhZwP2ur6', 'user@user.ru');

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO roles (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER');


CREATE TABLE IF NOT EXISTS users_roles
(
    id      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    UNIQUE (user_id, role_id)
);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);


CREATE TABLE IF NOT EXISTS orders
(
    id              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    total_price     INT NOT NULL,
    address         VARCHAR(255),
    phone           VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    id              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id        BIGINT NOT NULL,
    product_id      BIGINT NOT NULL,
    quantity        INT NOT NULL,
    price_per_product INT NOT NULL,
    price           INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);