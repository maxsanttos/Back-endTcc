package com.max.gerenciadorTcc.domain.tarefas;

import com.max.gerenciadorTcc.domain.users.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

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

    @Column(name = "due_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "completed_status", nullable = false)
    private CompletedStatus completedStatus;

    public void validate() {
        if (title == null || description == null || dueDate == null || completedStatus == null) {
            throw new IllegalArgumentException("Todos os campos da tarefa devem ser preenchidos.");
        }
    }
}
