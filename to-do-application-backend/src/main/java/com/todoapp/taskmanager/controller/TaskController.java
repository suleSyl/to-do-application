package com.todoapp.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapp.taskmanager.service.util.Constants;
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
    public void initExampleTaskRepo() {
        try {
            taskService.save(new Task(2, "example2", "ex", Constants.high, true, "exby"));
            log.info("Task saved successfully");
        } catch (Exception e) {
            log.error("Error occured while saving");
        }
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation(value = "Fetching task by Id")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
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
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("REST request to create Task: {}", task);
        Task savedTask = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/tasks")
    @ApiOperation(value = "Updating task")
    public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask) {
        log.info("REST request to update Task: {} " + updatedTask);
        Optional<Task> existingTask = taskService.findById(updatedTask.getId());
        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            taskToUpdate.setName(updatedTask.getName());
            taskToUpdate.setDesc(updatedTask.getDesc());
            taskToUpdate.setPriority(updatedTask.getPriority());
            taskToUpdate.setDone(updatedTask.isDone());
            taskToUpdate.setCreatedBy(updatedTask.getCreatedBy());
            Task updatedTaskResult = taskService.save(taskToUpdate);
            return ResponseEntity.ok(updatedTaskResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation(value = "Deleting task by Id")
    public ResponseEntity<Void> deleteTaskById(@PathVariable int id) {
        log.info("REST request to delete Task. Id: " + id);
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()) {
            taskService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/test")
    public Task getExample() {
        return Task.builder().desc("test").id(3).name("exmp").build();
    }
}
