package com.max.gerenciadorTcc.domain.tarefas;

import lombok.Getter;

@Getter
public enum CompletedStatus {
    
    PENDING("pending"),
    COMPLETED("completed"),
    IN_PROGRESS("in progress");

    private String label;

    CompletedStatus(String label){
        this.label = label;
    }

}
