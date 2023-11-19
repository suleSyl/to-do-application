package com.todoapp.taskmanager.controller;

import com.todoapp.taskmanager.models.User;
import com.todoapp.taskmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRegisterUser() {
        User userToRegister = new User();
        userToRegister.setUsername("user");
        userToRegister.setPassword("password");
        when(userService.registerUser(any(User.class))).thenReturn(userToRegister);
        ResponseEntity<User> responseEntity = userController.registerUser(userToRegister);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userToRegister, responseEntity.getBody());
        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    public void shouldReturnOkStatusWhenLoggingInSuccessfully() {
        User userToLogin = new User();
        userToLogin.setUsername("user");
        userToLogin.setPassword("password");
        when(userService.validateUser(anyString(), anyString())).thenReturn(true);
        ResponseEntity<String> responseEntity = userController.loginUser(userToLogin);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful", responseEntity.getBody());
        verify(userService, times(1)).validateUser(anyString(), anyString());
    }

    @Test
    public void shouldReturnUnauthorizedStatusWhenLoginFails() {
        User userToLogin = new User();
        userToLogin.setUsername("user");
        userToLogin.setPassword("password");
        when(userService.validateUser(anyString(), anyString())).thenReturn(false);
        ResponseEntity<String> responseEntity = userController.loginUser(userToLogin);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", responseEntity.getBody());
        verify(userService, times(1)).validateUser(anyString(), anyString());
    }
}
