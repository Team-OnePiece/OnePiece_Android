package com.example.onepiece_android;

public class DuplicateIdRequest {
    private String userId;

    public DuplicateIdRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
