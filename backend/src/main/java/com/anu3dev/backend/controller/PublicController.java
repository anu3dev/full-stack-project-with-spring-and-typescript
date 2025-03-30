package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.EmailContact;
import com.anu3dev.backend.model.EmailOTP;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.service.IEmailContactService;
import com.anu3dev.backend.service.IRegistrationService;
import com.anu3dev.backend.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/public/v1/")
public class PublicController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IEmailContactService emailService;
    @Autowired
    private IRegistrationService registrationService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendEmailOTP(@RequestBody EmailOTP email) {
        try {
            return new ResponseEntity<String>(registrationService.sendOTPEmail(email), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyEmailOtp(@RequestBody EmailOTP email) {
        try {
            return new ResponseEntity<String>(registrationService.verifyOTPEmail(email), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-company")
    public ResponseEntity<?> registerCompany(@RequestBody Company company)  {
        try {
            return new ResponseEntity<String>(registrationService.registerCompany(company), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody User user) throws Exception {
        return registrationService.registerUser(user);
    }

    @GetMapping("/company-list")
    public List<String> approvedCompanyList() {
        return registrationService.approvedCompanyNameList();
    }























    @PostMapping("/contact")
    public ResponseEntity<?> sendWelcomeEmail(@RequestBody EmailContact email) {
        try {
            return new ResponseEntity<String>(emailService.sendContactEmail(email), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("request can't be completed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
