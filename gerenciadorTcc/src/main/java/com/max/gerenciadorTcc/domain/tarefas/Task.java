package com.max.gerenciadorTcc.domain.tarefas;



import com.max.gerenciadorTcc.domain.enums.CompletedStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tarefa")
@Table(name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;


    @Enumerated(EnumType.STRING) // Mapeia o enum como string
    @Column(name = "completed_status", nullable = false) // Nome da coluna no banco de dados
    private CompletedStatus completedStatus;

}
