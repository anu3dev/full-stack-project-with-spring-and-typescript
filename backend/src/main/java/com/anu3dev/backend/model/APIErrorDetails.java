package com.anu3dev.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorDetails {
	private LocalDateTime time;
	private String msg;
	private String status;
}