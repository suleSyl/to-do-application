package com.todoapp.taskmanager.service.impl;

import com.todoapp.taskmanager.models.User;
import com.todoapp.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRegisterUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        when(userRepository.save(user)).thenReturn(user);
        User registeredUser = userService.registerUser(user);
        assertNotNull(registeredUser);
        assertEquals("user", registeredUser.getUsername());
        assertEquals("password", registeredUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldValidateUser() {
        String username = "user";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(user);
        boolean isValid = userService.validateUser(username, password);
        assertTrue(isValid);
        verify(userRepository, times(1)).findByUsername(username);
    }
}
