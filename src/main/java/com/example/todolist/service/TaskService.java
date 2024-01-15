package com.example.todolist.service;

import com.example.todolist.dao.TaskDao;
import com.example.todolist.entity.Priority;
import com.example.todolist.entity.Status;
import com.example.todolist.entity.Task;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDao taskDao;

    public Task findTaskById(int id){
        return taskDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    public void updateTask(int id, Task task) {
        task.setId(id);
        task.setDueDate(LocalDate.now());
        taskDao.save(task);
    }
    public  void completeTask(int id, String des, Priority p, Task task){
        task.setDescription(des);
        task.setPriority(p);
        task.setStatus(Status.COMPLETED);
        taskDao.save(task);
    }
        public List<Task>tasks=new ArrayList<>();
    public List<Task>getTasks(){
        return this.getTasks();
    }
    public void clearAll(){
            taskDao.deleteAll();
    }

    public void changeStatus(int id, Task task) {
        Optional<Task> optionalTask = taskDao.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setStatus(Status.ON_PROGRESS);
            taskDao.save(existingTask);
        }
    }

}
