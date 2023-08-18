package com.example.onepiece_android;

import com.google.gson.annotations.SerializedName;

public class TagResponse    {
    @SerializedName("tag_id")
    private int tagId;

    public int getTagId(){
        return tagId;
    }
}
