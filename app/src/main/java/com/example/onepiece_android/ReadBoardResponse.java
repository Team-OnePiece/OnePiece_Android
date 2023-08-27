package com.example.onepiece_android;

import com.google.gson.JsonObject;

import java.util.List;

public class ReadBoardResponse {
    public List<JsonObject> boardInfo;

    public List<JsonObject> getBoardInfo() {
        return boardInfo;
    }
}
