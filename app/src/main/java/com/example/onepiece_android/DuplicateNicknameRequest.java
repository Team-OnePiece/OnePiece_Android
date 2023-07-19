package com.example.onepiece_android;

public class DuplicateNicknameRequest {
    private String nickname;
    public DuplicateNicknameRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
