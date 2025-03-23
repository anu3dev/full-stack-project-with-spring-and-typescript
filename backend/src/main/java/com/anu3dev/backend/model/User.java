package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_list")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String companyId;
    private String emailId;
    private String phoneNo;
    private String password;
    private String name;
    private String uniqueId;
    private String approvalStatus;
    private String approvedBy;
    private String isAdmin;
}