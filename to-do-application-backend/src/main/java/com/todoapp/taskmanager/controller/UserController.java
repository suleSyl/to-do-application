package com.todoapp.taskmanager.controller;

import com.todoapp.taskmanager.models.User;
import com.todoapp.taskmanager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "User Api documentation")
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;    }

    @PostMapping("/register")
    @ApiOperation(value = "Registering a new user")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        log.info("REST request to register User: {}", user);
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login to the system")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        log.info("REST request to login User: {}", user);
        if (userService.validateUser(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
