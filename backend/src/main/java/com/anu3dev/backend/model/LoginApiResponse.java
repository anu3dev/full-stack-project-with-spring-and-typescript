package com.anu3dev.backend.model;

import lombok.Data;

@Data
public class LoginApiResponse {
    private String message;
    private String AccessToken;
    private String firstName;
    private String role;

    public LoginApiResponse(String message, String accessToken, String firstName, String role) {
        this.message = message;
        AccessToken = accessToken;
        this.firstName = firstName;
        this.role = role;
    }
}
