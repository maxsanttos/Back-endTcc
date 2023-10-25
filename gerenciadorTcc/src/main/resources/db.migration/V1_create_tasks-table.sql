CREATE TABLE tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    due_date TEXT NOT NULL,
    completed_status VARCHAR(20) NOT NULL
);