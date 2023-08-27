package com.example.onepiece_android;

import com.google.gson.annotations.SerializedName;

public class ReactionResponse {
    private int star_counts;

    @SerializedName("star_counts")
    private int starCounts;

    public int getStar_counts() {
        return star_counts;
    }

    public void setStar_counts(int star_counts) {
        this.star_counts = star_counts;
    }
}
