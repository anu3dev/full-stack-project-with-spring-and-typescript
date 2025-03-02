package com.anu3dev.backend.dao;

import com.anu3dev.backend.model.SuperAdminList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminListDAO extends JpaRepository<SuperAdminList, Long> {}
