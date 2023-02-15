package com.sebas.demo.controller;

import com.sebas.demo.persistence.entity.TaskStatus;
import com.sebas.demo.service.TaskService;
import com.sebas.demo.service.dto.TaskInDTO;
import com.sebas.demo.persistence.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping //notacion para crear elementos
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }
// tercero servicio
    @GetMapping
    public List<Task> getAllTasks(){
        return this.taskService.getAllTasks();
    }
    @GetMapping("/status/{status}")
    public  List<Task> getAllByTaskStatus(@PathVariable("status") TaskStatus status){
        return this.taskService.getAllByTaskStatus(status);
    }

    @PatchMapping("/markAsFinished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
