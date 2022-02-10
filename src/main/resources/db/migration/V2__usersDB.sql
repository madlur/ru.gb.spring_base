CREATE TABLE IF NOT EXISTS users(
                                    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(100) UNIQUE NOT NULL,
                                    password VARCHAR(100) NOT NULL,
                                    email VARCHAR(100)

);

INSERT INTO users (id, username,password,email) VALUES
                                                                         (1,'admin', '$2y$10$72R3hM0PQn6RwRRLs9Vvn.8qiLQ/..o6gcrvxyfodIs73jJEj23da', 'admin@admin.ru'),
                                                                         (2,'user', '$2y$10$YNIoi4EP1oCoXaEx7y3Utur2hGFyPX32Oskd1thyQ1BxuhZwP2ur6', 'user@user.ru');

CREATE TABLE IF NOT EXISTS  roles(
                                     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL
);

INSERT INTO roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'USER');


CREATE TABLE IF NOT EXISTS  users_roles(
                                           id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           user_id BIGINT NOT NULL,
                                           role_id BIGINT NOT NULL,
                                           FOREIGN KEY (user_id) REFERENCES users (id),
                                           FOREIGN KEY (role_id) REFERENCES roles (id),
                                           UNIQUE (user_id, role_id)
);

INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1,1),
                                               (2,2);
