package com.max.gerenciadorTcc.domain.tarefas;

import com.max.gerenciadorTcc.domain.enums.CompletedStatus;
import com.max.gerenciadorTcc.domain.users.User;

import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "completed_status", nullable = false)
    private CompletedStatus completedStatus;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String description, CompletedStatus completedStatus, Date createdAt, Date dueDate, User user) {
        this.title = title;
        this.description = description;
        this.completedStatus = completedStatus;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.user = user;
    }
}
