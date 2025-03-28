package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="email_otp_details")
public class EmailOTP {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String emailId;
	private String otpValue;
}
