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
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Optional<Task> findById(int id) {
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
    public Task updateTask(int id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            taskToUpdate.setName(updatedTask.getName());
            taskToUpdate.setDesc(updatedTask.getDesc());
            taskToUpdate.setPriority(updatedTask.getPriority());
            taskToUpdate.setCompleted(updatedTask.isCompleted());
            taskToUpdate.setCreatedBy(updatedTask.getCreatedBy());
            return taskRepository.save(taskToUpdate);
        } else {
            throw new RuntimeException("No task found with id: " + id);
        }
    }
    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }
}
