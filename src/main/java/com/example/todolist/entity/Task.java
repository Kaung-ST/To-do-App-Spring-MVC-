package com.example.todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String description;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private  Priority priority;
  //  @ManyToOne
   // private  User user;

    public Task( String description, LocalDate dueDate,Status status, Priority priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }
}
