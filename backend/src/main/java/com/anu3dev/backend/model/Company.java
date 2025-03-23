package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="company_list")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private String emailId;
    private String name;
    private String uniqueId;
    private String approvalStatus;
    private String approvedBy;
    private String phoneNo;
    private String registeredBy;
}
