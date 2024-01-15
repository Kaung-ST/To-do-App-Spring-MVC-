package com.example.todolist.dao;

import com.example.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task,Integer> {
}
