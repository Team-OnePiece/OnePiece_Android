package com.example.onepiece_android;

public class ReactionResponse {
    private int star_counts;
    private boolean star;

    public int getStar_counts() {
        return star_counts;
    }
    public boolean isStar() {
        return star;
    }

    public void setStar_counts(int star_counts) {
        this.star_counts = star_counts;
    }
    public void setStar(boolean star) {
        this.star = star;
    }
}
