package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {
    boolean existsByCompanyUniqueId(String uniqueId);
    boolean existsByCompanyEmail(String email);
}
