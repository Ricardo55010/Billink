package com.example.billink.Controllers;

import com.example.billink.Models.User;
import com.example.billink.Services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserServiceImpl userService;
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }
@GetMapping
    public ResponseEntity<User> getUser(){
        return ResponseEntity.ok(new User("Ricardo",10,1500));
    }
}