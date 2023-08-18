package com.max.gerenciadorTcc.exception;

public class TaskNotFoundException extends RuntimeException {
    
    public TaskNotFoundException(String message){
        super(message);
    }
}
