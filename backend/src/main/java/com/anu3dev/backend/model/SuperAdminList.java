package com.anu3dev.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="super_admin_list")
@Data
public class SuperAdminList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email_id")
	private String emailId;

	public SuperAdminList() {}

	public SuperAdminList(long id, String firstName, String lastName, String emailId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
}
