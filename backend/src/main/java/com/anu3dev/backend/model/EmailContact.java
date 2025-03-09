package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="email_contact_details")
public class EmailContact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	@Column(name="email_id")
	private String emailId;
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String message;
}
