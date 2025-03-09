package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.EmailContact;
import com.anu3dev.backend.model.EmailOTP;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.IEmailContactService;
import com.anu3dev.backend.service.IEmailOTPService;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public/v1/")
public class PublicController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IEmailContactService emailService;
    @Autowired
    private IEmailOTPService otpService;

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

    @PostMapping("/contact")
    public ResponseEntity<?> sendWelcomeEmail(@RequestBody EmailContact email) {
        try {
            return new ResponseEntity<String>(emailService.sendContactEmail(email), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/otp")
    public ResponseEntity<?> sendWelcomeEmail(@RequestBody EmailOTP email) {
        try {
            return new ResponseEntity<String>(otpService.sendOTPEmail(email), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
