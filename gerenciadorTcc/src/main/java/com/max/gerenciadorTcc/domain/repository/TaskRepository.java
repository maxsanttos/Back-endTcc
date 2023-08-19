package com.max.gerenciadorTcc.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.max.gerenciadorTcc.domain.tarefas.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{  
}
