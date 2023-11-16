package com.todoapp.taskmanager.service.impl;

import com.todoapp.taskmanager.models.User;
import com.todoapp.taskmanager.repository.UserRepository;
import com.todoapp.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
