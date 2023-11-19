package com.todoapp.taskmanager.controller;

import com.todoapp.taskmanager.models.Task;
import com.todoapp.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskControllerTest {
    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnTaskByIdWhenFound() {
        String taskId = "1";
        Task expectedTask = new Task(taskId, "example1", false, "user");
        when(taskService.findById(taskId)).thenReturn(Optional.of(expectedTask));
        ResponseEntity<Task> response = taskController.getTaskById(taskId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTask, response.getBody());
        verify(taskService, times(1)).findById(taskId);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldReturnNotFoundWhenTaskNotFound() {
        String taskId = "1";
        when(taskService.findById(taskId)).thenReturn(Optional.empty());
        ResponseEntity<Task> response = taskController.getTaskById(taskId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(taskService, times(1)).findById(taskId);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldReturnAllTasks() {
        List<Task> expectedTasks = Arrays.asList(
                new Task("1", "example1", false, "user"),
                new Task("2", "example2", true, "user")
        );
        when(taskService.findAll()).thenReturn(expectedTasks);
        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService, times(1)).findAll();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldReturnNotFoundWhenNoTasksFound() {
        when(taskService.findAll()).thenReturn(Arrays.asList());
        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(taskService, times(1)).findAll();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldCreateNewTask() {
        Task newTask = new Task( "example", false, "user");
        when(taskService.save(newTask)).thenReturn(newTask);
        ResponseEntity<Task> response = taskController.createTask(newTask);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newTask, response.getBody());
        verify(taskService, times(1)).save(newTask);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldUpdateTaskByIdWhenFound() {
        String taskId = "1";
        Task existingTask = new Task(taskId, "Existing Task", false, "user");
        Task updatedTask = new Task(taskId, "Updated Task", true, "user");
        Task updatedTaskResult = new Task(taskId, "Updated Task Result", true, "user");
        when(taskService.updateTask(eq(existingTask.getId()), any(Task.class))).thenReturn(updatedTaskResult);
        ResponseEntity<Task> response = taskController.updateTaskById(existingTask.getId(), updatedTask);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTaskResult, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenTaskToUpdateNotFound() {
        String taskId = "1";
        Task existingTask = new Task(taskId, "Existing Task", false, "user");
        Task updatedTask = new Task(taskId, "Updated Task", true, "user");
        when(taskService.updateTask(eq(existingTask.getId()), any(Task.class))).thenThrow(new RuntimeException("Task not found"));
        ResponseEntity<Task> response = taskController.updateTaskById(existingTask.getId(), updatedTask);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldDeleteTaskByIdWhenFound() {
        String taskId = "1";
        Task existingTask = new Task(taskId, "example1", false, "user");
        when(taskService.findById(taskId)).thenReturn(Optional.of(existingTask));
        ResponseEntity<Void> response = taskController.deleteTaskById(taskId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService, times(1)).findById(taskId);
        verify(taskService, times(1)).deleteById(taskId);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void shouldReturnNotFoundWhenTaskToDeleteNotFound() {
        String taskId = "1";
        when(taskService.findById(taskId)).thenReturn(Optional.empty());
        ResponseEntity<Void> response = taskController.deleteTaskById(taskId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskService, times(1)).findById(taskId);
        verifyNoMoreInteractions(taskService);
    }
}
