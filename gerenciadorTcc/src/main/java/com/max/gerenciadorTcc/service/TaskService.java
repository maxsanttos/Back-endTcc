package com.max.gerenciadorTcc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.max.gerenciadorTcc.domain.tarefas.dto.TaskDTO;
import com.max.gerenciadorTcc.domain.repository.TaskRepository;
import com.max.gerenciadorTcc.domain.tarefas.Task;
import com.max.gerenciadorTcc.exception.TaskNotFoundException;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService){
        this.taskRepository = taskRepository;
        this.userService = userService;
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
        task.setDueDate(taskDTO.getDueDate());
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
            existingTask.setDueDate(taskDTO.getDueDate());
            existingTask.setCompletedStatus(taskDTO.getCompletedStatus());
            return taskRepository.save(existingTask);
        }else{
            throw new TaskNotFoundException("Tarefa com id " + id + " não encontrada");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private boolean isDueDateNear(Date dueDate) {
        if (dueDate != null) {
            // Obtém a data e hora atual
            Date currentDate = new Date();

            // Calcula a diferença em milissegundos entre a data de entrega e a data atual
            long timeDifference = dueDate.getTime() - currentDate.getTime();

            // Define o limite de tempo em milissegundos (5 horas = 5 * 60 * 60 * 1000 milissegundos)
            long timeLimit = 0 * 00 * 30 * 1000;

            // Verifica se a diferença está dentro do limite
            if (timeDifference <= timeLimit) {
                return true; // A data de entrega está próxima
            }
        }
        return false; // A data de entrega não está próxima
    }


    private void sendNotification(Task task) {
        String subject = "Tarefa próxima do prazo de entrega";
        String message = "Sua tarefa \"" + task.getTitle() + "\" está próxima do prazo de entrega.";
        userService.sendNotificationEmail(subject, message);
    }
}
