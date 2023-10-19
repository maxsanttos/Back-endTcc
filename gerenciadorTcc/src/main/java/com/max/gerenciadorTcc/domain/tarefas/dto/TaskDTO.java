package com.max.gerenciadorTcc.domain.tarefas.dto;

import com.max.gerenciadorTcc.domain.tarefas.CompletedStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private CompletedStatus completedStatus;
}
