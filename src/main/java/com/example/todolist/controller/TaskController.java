package com.example.todolist.controller;

import com.example.todolist.dao.TaskDao;
import com.example.todolist.entity.Priority;
import com.example.todolist.entity.Status;
import com.example.todolist.entity.Task;
import com.example.todolist.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskDao taskDao;
    private final TaskService taskService;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("task",new Task());
        model.addAttribute("tasks",taskDao.findAll());
        return "index";
    }
    @PostMapping("/add")
    public String addTask(@RequestParam("description") @NotBlank(message = "this cannot be empty") String description,
                          @RequestParam("Status")Status status,
                          @RequestParam("Priority")Priority priority,
                          Model model
                         ){
        model.addAttribute("descriptionError", "Description cannot be empty");
        Task task=new Task(
                description,
                LocalDate.now(),
                status,
                priority
        );

        taskDao.save(task);
        return "redirect:/";
    }
    @RequestMapping("/delete")
    public String deleteTask(@RequestParam("id")int id){
        taskDao.deleteById(id);
        return "redirect:/";
    }
    private int tid;
    @GetMapping("/edit")
    public String updateTask(@RequestParam("id")int id,Model model){
        model.addAttribute("task", new Task());
        model.addAttribute("customer", taskService.findTaskById(id));
        this.tid = id;
        return "edit";
    }
    @PostMapping("/editTask")
    public String updateSaveTask(@Valid Task task, BindingResult result){

        if(result.hasErrors()){
            return "edit";
        }taskService.updateTask(tid,task);
        return "redirect:/";
    }
    @GetMapping("/complete")
    public String completeTask(@RequestParam("id") int id,
                               @RequestParam("description") String description,
                               @RequestParam("dueDate") LocalDate date,
                               @RequestParam("priority") Priority priority,
                               @ModelAttribute Task task) {
        System.out.println("id : " + id);
        task.setStatus(Status.COMPLETED);
        taskDao.save(task);
        this.nid = id + 1;
        System.out.println("nid  = " + nid);
        taskService.changeStatus(nid, task);
        return "redirect:/";
    }

    private int nid;

    @GetMapping("/clearAll")
        public String clearAll(){
        taskService.clearAll();
        return "redirect:/";
        }
}
