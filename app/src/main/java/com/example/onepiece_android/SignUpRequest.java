package com.example.onepiece_android;

public class SignUpRequest {
    private String userId;
    private String userPassword;
    private String nickname;
    private int grade;
    private int classNumber;
    private int number;

    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getNickname() {
        return nickname;
    }
    public int getSchoolNumber() {
        return grade;
    }
    public int getClassNumber() {
        return classNumber;
    }
    public int getStudentNumber() {
        return number;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
