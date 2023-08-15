package com.example.onepiece_android;

public class Board {
    private Long id;
    private String place;
    private String board_image_url;
    private String create_at;
    private String nickname;
    private String profile_image_url;
    private String[] tags;
    private int grade;
    private int class_number;
    private int number;
    private int star_count;

    public Board(Long id, String place, String board_image_url, String create_at, String nickname, String profile_image_url, String[] tags, int grade, int class_number, int number, int star_count) {
        this.id = id;
        this.place = place;
        this.board_image_url = board_image_url;
        this.create_at = create_at;
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.tags = tags;
        this.grade = grade;
        this.class_number = class_number;
        this.number = number;
        this.star_count = star_count;
    }

    public Long getId() {
        return id;
    }
    public String getPlace() {
        return place;
    }
    public String getBoard_image_url() {
        return board_image_url;
    }
    public String getCreate_at() {
        return create_at;
    }
    public String getNickname() {
        return nickname;
    }
    public String getProfile_image_url() {
        return profile_image_url;
    }
    public String[] getTags() {
        return tags;
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

    public int getStar_count() {
        return star_count;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setBoard_image_url(String board_image_url) {
        this.board_image_url = board_image_url;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
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

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }
}
