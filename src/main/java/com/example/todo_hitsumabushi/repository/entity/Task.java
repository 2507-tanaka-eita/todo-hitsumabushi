package com.example.todo_hitsumabushi.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private Integer status;

    @Column
    private LocalDateTime limitDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}