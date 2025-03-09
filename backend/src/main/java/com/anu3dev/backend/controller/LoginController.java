package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login/v1/")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/register-company")
    public String registerCompany(@RequestBody Company company) throws Exception  {
        return loginService.registerCompany(company);
    }

    @GetMapping("/get-company-list")
    public List<Company> getCompanyList() {
        return loginService.getCompanyList();
    }

    @GetMapping("/get-approved-company-list")
    public List<Company> getApprovedCompanyList() {
        return loginService.getApprovedCompanyList();
    }
}
