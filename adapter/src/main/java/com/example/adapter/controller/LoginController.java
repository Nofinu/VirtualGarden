package com.example.adapter.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.spi.port.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register (@RequestBody User user){
//
//    }
}