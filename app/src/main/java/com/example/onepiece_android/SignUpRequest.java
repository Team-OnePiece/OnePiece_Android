package com.example.onepiece_android;

public class SignUpRequest {
    private String account_id;
    private String password;
    private String password_valid;
    private String nickname;
    private int grade;
    private int class_number;
    private int number;

    public String getAccount_id() {
        return account_id;
    }
    public String getPassword() {
        return password;
    }
    public String getPassword_valid() {
        return password_valid;
    }
    public String getNickname() {
        return nickname;
    }
    public int getGrade() {
        return grade;
    }
    public int getClass_number() {
        return class_number;
    }
    public int getNumber() {
        return number;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPassword_valid(String password_valid) {
        this.password_valid = password_valid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public void setClass_number(int class_number) {
        this.class_number = class_number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
