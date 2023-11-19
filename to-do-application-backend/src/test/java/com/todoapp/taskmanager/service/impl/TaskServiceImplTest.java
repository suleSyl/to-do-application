package com.todoapp.taskmanager.service.impl;

import com.todoapp.taskmanager.models.Task;
import com.todoapp.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        String taskId = "1";
        Task expectedTask = new Task(taskId, "example", false, "user");
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));
        Optional<Task> result = taskService.findById(taskId);
        assertTrue(result.isPresent());
        assertEquals(expectedTask, result.get());
        verify(taskRepository, times(1)).findById(taskId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testFindAll() {
        Task task1 = new Task("1", "example1", false, "user");
        Task task2 = new Task("2", "example2", true, "user");
        List<Task> expectedTasks = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(expectedTasks);
        List<Task> result = taskService.findAll();
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testSave() {
        Task taskToSave = new Task("example", false, "user");
        when(taskRepository.save(taskToSave)).thenReturn(taskToSave);
        Task result = taskService.save(taskToSave);
        assertEquals(taskToSave, result);
        verify(taskRepository, times(1)).save(taskToSave);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testUpdateTask() {
        String taskId = "1";
        Task existingTask = new Task(taskId, "Existing Task", false, "user");
        Task updatedTask = new Task(taskId, "Updated Task", true, "user");
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);
        Task result = taskService.updateTask(taskId, updatedTask);
        assertEquals(updatedTask, result);
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(existingTask);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testUpdateTask_NotFound() {
        String taskId = "1";
        Task updatedTask = new Task(taskId, "Updated Task", true, "user");
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> taskService.updateTask(taskId, updatedTask));
        verify(taskRepository, times(1)).findById(taskId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testDeleteById() {
        String taskId = "1";
        doNothing().when(taskRepository).deleteById(taskId);
        taskService.deleteById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
        verifyNoMoreInteractions(taskRepository);
    }
}
