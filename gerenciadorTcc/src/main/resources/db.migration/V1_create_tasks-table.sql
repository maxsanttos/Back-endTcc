CREATE TABLE tarefa(
    id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    completed_status TEXT NOT NULL
);