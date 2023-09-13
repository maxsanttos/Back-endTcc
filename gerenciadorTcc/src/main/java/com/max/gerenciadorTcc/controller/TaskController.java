package com.max.gerenciadorTcc.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.gerenciadorTcc.controller.dto.TaskDTO;
import com.max.gerenciadorTcc.domain.tarefas.Task;
import com.max.gerenciadorTcc.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if (taskOptional.isPresent()) {
            TaskDTO taskDTO = convertToDTO(taskOptional.get());
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        TaskDTO createdTaskDTO = convertToDTO(createdTask);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskService.updateTask(id, taskDTO);
        if (updatedTask != null) {
            TaskDTO updatedTaskDTO = convertToDTO(updatedTask);
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllTasks() {
        taskService.deleteAllTasks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // metodo que converter um objeto Task em um objeto TaskDTO
    private TaskDTO convertToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completedStatus(task.getCompletedStatus())
                .build();
    }
}
