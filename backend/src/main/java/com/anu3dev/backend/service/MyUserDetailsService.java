package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.UserLoginDAO;
import com.anu3dev.backend.model.User;
import com.anu3dev.backend.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserLoginDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUsername(username);
        System.out.print("user"+ user);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}