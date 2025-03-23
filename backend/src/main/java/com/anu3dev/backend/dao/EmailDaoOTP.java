package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.EmailOTP;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDaoOTP extends JpaRepository<EmailOTP, Long> {
    boolean existsByEmailId(String emailId);
    EmailOTP findByEmailId(String emailId);
}