package com.todoapp.taskmanager.service.impl;

import com.todoapp.taskmanager.models.Task;
import com.todoapp.taskmanager.repository.TaskRepository;
import com.todoapp.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) { this.taskRepository = taskRepository; }
    @Override
    public Optional<Task> findById(String id) {
        return taskRepository.findById(id);
    }
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }
    @Override
    public Task updateTask(String id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setDesc(updatedTask.getDesc());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    existingTask.setCreatedBy(updatedTask.getCreatedBy());
                    return taskRepository.save(existingTask);
                })
                .orElseThrow(() -> new RuntimeException("No task found with id: " + id));
    }
    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findTasksByCreatedBy(String createdBy) {
        return taskRepository.findByCreatedBy(createdBy);
    }
}
