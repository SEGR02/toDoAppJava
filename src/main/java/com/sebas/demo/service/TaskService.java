package com.sebas.demo.service;

import com.sebas.demo.exceptions.ToDoExceptions;
import com.sebas.demo.mapper.TaskInDTOToTask;
import com.sebas.demo.persistence.entity.Task;
import com.sebas.demo.persistence.entity.TaskStatus;
import com.sebas.demo.persistence.repository.TaskRepository;
import com.sebas.demo.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService { //primer paso: usar el repositorio, el servicio se conecta al repositorio y el repo a la db

    private final TaskRepository repository;
        private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }
// segundo servicio
    public List<Task> getAllTasks(){
        return this.repository.findAll();
    }
    public List<Task> getAllByTaskStatus(TaskStatus status){ //no debe llamarse igual que el del repositorio
        return this.repository.findAllByTaskStatus(status); //aca si debe ir el del repositorio
    }
    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }

}
