package com.max.gerenciadorTcc.domain.tarefas.dto;

import com.max.gerenciadorTcc.domain.tarefas.CompletedStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private CompletedStatus completedStatus;
}
