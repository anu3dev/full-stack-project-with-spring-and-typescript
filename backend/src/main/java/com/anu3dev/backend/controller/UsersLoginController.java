package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user/v1/")
public class UsersLoginController {

    @Autowired
    private UserLoginService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.verify(user);
    }
}