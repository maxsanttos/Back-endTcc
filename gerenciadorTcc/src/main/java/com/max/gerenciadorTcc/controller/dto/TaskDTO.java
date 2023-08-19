package com.max.gerenciadorTcc.controller.dto;

import com.max.gerenciadorTcc.domain.enums.CompletedStatus;
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
