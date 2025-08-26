package com.example.billink.Services;

import com.example.billink.Models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public User getUser(){
        return new User("Marisol",1,1000);
    }
}