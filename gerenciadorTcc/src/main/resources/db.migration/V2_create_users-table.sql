CREATE TABLE users (
    id INT UNIQUE AUTO_INCREMENT PRIMARY KEY NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);
