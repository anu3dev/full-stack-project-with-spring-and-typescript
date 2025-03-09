package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_profile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String companyId;
    private String username;
    private String password;
    private Boolean approvalStatus;
    private String approvedBy;
    private String firstName;
    private String lastName;
    private String universalId;
}