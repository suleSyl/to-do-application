package com.todoapp.taskmanager.service;

import com.todoapp.taskmanager.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public List<User> findAll();
    public boolean validateUser(String username, String password);
}
