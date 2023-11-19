package com.todoapp.taskmanager.service.impl;

import com.todoapp.taskmanager.models.User;
import com.todoapp.taskmanager.repository.UserRepository;
import com.todoapp.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && password.equals(user.getPassword());
    }
}
