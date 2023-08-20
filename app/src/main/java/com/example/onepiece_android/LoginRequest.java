package com.example.onepiece_android;

public class LoginRequest {
    private String userId;
    private String userPassword;


    public LoginRequest(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}