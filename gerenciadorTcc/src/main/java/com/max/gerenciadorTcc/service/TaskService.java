package com.max.gerenciadorTcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.max.gerenciadorTcc.controller.dto.TaskDTO;
import com.max.gerenciadorTcc.domain.repository.TaskRepository;
import com.max.gerenciadorTcc.domain.tarefas.Task;
import com.max.gerenciadorTcc.exception.TaskNotFoundException;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompletedStatus(taskDTO.getCompletedStatus());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDTO taskDTO){

        if (id == null || taskDTO == null) {
            throw new IllegalArgumentException("id e taskDTO não devem ser nulos");
        }

        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(taskDTO.getTitle());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setCompletedStatus(taskDTO.getCompletedStatus());
            return taskRepository.save(existingTask);
        }else{
            throw new TaskNotFoundException("Tarefa com id " + id + " não encontrada");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }  
}
