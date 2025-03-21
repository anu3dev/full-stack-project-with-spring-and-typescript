package com.anu3dev.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class ResourceUnAuthorizedException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public ResourceUnAuthorizedException(String message){
		super(message);
		System.out.println(message);
	}
}
