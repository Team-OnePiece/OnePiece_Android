package com.example.onepiece_android;

public class UserInfoResponse {
    private String nickname;
    private String profileImageUrl;

    public String getNickname() {
        return nickname;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
