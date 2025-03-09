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
    private String password;
    private String firstName;
    private String lastName;
    private long universalId;
    private String approvalStatus;
    private String approvedBy;
    private String isCompanyAdmin;
}