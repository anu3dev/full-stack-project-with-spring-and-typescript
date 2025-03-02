package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.EmailContact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailContactDAO extends JpaRepository<EmailContact, Long> {}