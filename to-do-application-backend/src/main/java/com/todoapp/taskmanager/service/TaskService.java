package com.todoapp.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.taskmanager.models.Task;
import com.todoapp.taskmanager.repository.TaskRepository;


public interface TaskService {

	public Optional<Task> findById(String id);
	public List<Task> findAll();
	public Task save(Task task);
	public Task updateTask(String id, Task updatedTask);
	public void deleteById(String id);
	public List<Task> findTasksByCreatedBy(String createdBy);

}
