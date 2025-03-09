package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.SecureRandom;

public interface LoginDao extends JpaRepository<Company, Integer> {
    boolean existsByUniqueId(String uniqueId);
    boolean existsByCompanyEmail(String email);
}
