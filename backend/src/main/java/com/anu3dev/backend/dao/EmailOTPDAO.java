package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.EmailOTP;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailOTPDAO extends JpaRepository<EmailOTP, Long> {}