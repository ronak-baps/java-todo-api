package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.User;
import com.example.todo.service.UserService;

@RestController
@RequestMapping
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        ResponseEntity<?> dbUser = userService.registerUser(user);
        return dbUser;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        ResponseEntity<?> dbUser = userService.userLogin(user);
        return dbUser;
    }
}
