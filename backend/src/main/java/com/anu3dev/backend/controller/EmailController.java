package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.EmailContact;
import com.anu3dev.backend.model.EmailOTP;
import com.anu3dev.backend.service.IEmailContactService;
import com.anu3dev.backend.service.IEmailOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/email/v1/")
public class EmailController {
	@Autowired
	private IEmailContactService emailService;
	@Autowired
	private IEmailOTPService otpService;
	
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
