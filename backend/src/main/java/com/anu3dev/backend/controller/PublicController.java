package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public/v1/")
public class PublicController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/register-company")
    public String registerCompany(@RequestBody Company company) throws Exception  {
        return loginService.registerCompany(company);
    }

    @GetMapping("/get-approved-company-list")
    public List<Company> getApprovedCompanyList() {
        return loginService.getApprovedCompanyList();
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody User user) throws Exception {
        return loginService.registerUser(user);
    }
}
