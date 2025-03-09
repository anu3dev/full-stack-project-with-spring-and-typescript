package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
