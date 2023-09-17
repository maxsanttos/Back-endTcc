CREATE TABLE tarefa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    completed_status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    due_date DATE NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);