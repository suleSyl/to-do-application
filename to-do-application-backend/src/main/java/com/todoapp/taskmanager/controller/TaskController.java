package com.todoapp.taskmanager.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapp.taskmanager.models.Task;
import com.todoapp.taskmanager.service.TaskService;
import org.slf4j.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.PostConstruct;

@RestController
@Api(value = "Task Api documentation")
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostConstruct
    public void initSampleTaskRepo() {
        try {
            taskService.save(new Task( "example1",  false, "user"));
            log.info("Task saved successfully");
        } catch (Exception e) {
            log.error("Error occured while saving");
        }
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation(value = "Fetching task by Id")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        log.info("REST request to get Task. Id: " + id);
        return taskService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks")
    @ApiOperation(value = "Fetching all tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("REST request to get all tasks");
        List<Task> tasks = taskService.findAll();
        if (!tasks.isEmpty()) {
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/tasks")
    @ApiOperation(value = "Creating a new task")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        log.info("REST request to create Task: {}", task);
        Task savedTask = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/tasks/{id}")
    @ApiOperation(value = "Updating task by Id")
    public ResponseEntity<Task> updateTaskById(@PathVariable String id, @RequestBody Task updatedTask) {
        log.info("REST request to update Task. Id: {} Task: {} ", id, updatedTask);
        try {
            Task updatedTaskResult = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(updatedTaskResult);
        } catch (RuntimeException e) {
            log.error("Error updating task: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation(value = "Deleting task by Id")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String id) {
        log.info("REST request to delete Task. Id: " + id);
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            try {
                taskService.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                log.error("Error deleting task: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
