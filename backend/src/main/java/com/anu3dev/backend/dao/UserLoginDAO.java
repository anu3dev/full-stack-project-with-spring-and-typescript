package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}