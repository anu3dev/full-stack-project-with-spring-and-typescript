package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.UserLoginDAO;
import com.anu3dev.backend.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

	@Autowired
	private JWTService jwtService;
	 
	@Autowired
    AuthenticationManager authManager;
	
    @Autowired
    private UserLoginDAO dao;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public String register(UserLogin user) {
        user.setPassword(encoder.encode(user.getPassword()));
        dao.save(user);
        return "registration successful";
    }
    
    public String verify(UserLogin user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}