package com.example.todolist;

import com.example.todolist.dao.TaskDao;
import com.example.todolist.entity.Priority;
import com.example.todolist.entity.Status;
import com.example.todolist.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class ToDoListApplication {
private final TaskDao taskDao;
    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }
    @Bean
    public ApplicationRunner runner(){
        return r->{

            Task task1=new Task("Reading Book", LocalDate.now(), Status.ON_PROGRESS, Priority.MEDIUM);
            Task task2=new Task("Shopping", LocalDate.now(),Status.NEXT_COMING, Priority.MEDIUM);
            Task task3=new Task("Shopping", LocalDate.now(),Status.NEXT_COMING, Priority.MEDIUM);
           // taskDao.save(task1);
        };
    }

}
