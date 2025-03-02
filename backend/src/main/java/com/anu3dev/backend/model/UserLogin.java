package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_login")
public class UserLogin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="company_id")
    private String companyId;
    private String username;
    private String password;
}