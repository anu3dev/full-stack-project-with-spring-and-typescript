package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {
    boolean existsByUniqueId(String uniqueId);
    boolean existsByEmailId(String email);
    Company findByEmailId(String emailId);
}
