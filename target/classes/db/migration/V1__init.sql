CREATE TABLE IF NOT EXISTS products
(
    id    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price INT          NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);


INSERT INTO products (id, title, price)
VALUES (1, 'Мололко', 100),
       (2, 'Кефир', 80),
       (3, 'Творог', 56),
       (4, 'Сахар', 12),
       (5, 'Cыр', 112);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT              NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100)        NOT NULL,
    email    VARCHAR(100),
    address  VARCHAR(100),
    phone    VARCHAR(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

INSERT INTO users (id, username, password, email, address, phone)
VALUES (1, 'admin', '$2y$10$72R3hM0PQn6RwRRLs9Vvn.8qiLQ/..o6gcrvxyfodIs73jJEj23da', 'admin@admin.ru', '675425, Новгородская область, город Москва, наб. Домодедовская, 58', '+79000010203'),
       (2, 'user', '$2y$10$YNIoi4EP1oCoXaEx7y3Utur2hGFyPX32Oskd1thyQ1BxuhZwP2ur6', 'user@user.ru', '330250, Магаданская область, город Луховицы, въезд Гагарина, 03', '+79990154693');

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');


CREATE TABLE IF NOT EXISTS users_roles
(
    PRIMARY KEY (user_id, role_id),
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);


CREATE TABLE IF NOT EXISTS orders
(
    id              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT,
    total_price     INT NOT NULL,
    address         VARCHAR(255),
    phone           VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
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
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

insert into orders (user_id, total_price, address, phone)
values (1, 200, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);