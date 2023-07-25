package com.example.onepiece_android;

public class SignUpRequest {
    private String userId;
    private String userPassword;
    private String nickname;
    private int schoolNumber;
    private int classNumber;
    private int studentNumber;

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
        return schoolNumber;
    }
    public int getClassNumber() {
        return classNumber;
    }
    public int getStudentNumber() {
        return studentNumber;
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
    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
