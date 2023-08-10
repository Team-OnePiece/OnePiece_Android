package com.example.onepiece_android;

public class LoginResponse {

    private String access_token;

    public LoginResponse(String accessToken) {
        this.access_token = accessToken;
    }

    public String getAccessToken() {
        return access_token;
    }
}