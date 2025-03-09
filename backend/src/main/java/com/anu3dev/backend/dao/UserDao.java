package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    boolean existsByEmailId(String email);
    boolean existsByUniqueId(long uniqueId);
    User findByEmailId(String emailId);
}
