package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login/v1/")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping("/get-company-list")
    public List<Company> getCompanyList() {
        return loginService.getCompanyList();
    }

    @PostMapping("/login-user")
    public String loginUser(@RequestBody User user) {
        return loginService.verifyUserLogin(user);
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return loginService.getAllUsers();
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return loginService.saveUserData(user);
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return loginService.getUserData(id);
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return loginService.updateUserData(id, user);
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable int id){
        return loginService.deleteUserData(id);
    }
}
