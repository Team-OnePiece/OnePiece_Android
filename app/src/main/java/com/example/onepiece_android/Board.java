package com.example.onepiece_android;

public class Board {
    String[] tag;
    String place;
    String nickname;
    String profile;
    String number;
    Boolean createdAt;

//    public Board(String[] tag, String place, String nickname, String profile, String number, Boolean createdAt) {
//        this.tag = tag;
//        this.place = place;
//        this.nickname = nickname;
//        this.profile = profile;
//        this.number = number;
//        this.createdAt = createdAt;
//    }

    public String[] getTag() {
        return tag;
    }
    public void setTag(String[] tag) {
        this.tag = tag;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Boolean getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Boolean createdAt) {
        this.createdAt = createdAt;
    }
}
