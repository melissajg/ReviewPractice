package com.example.reviewpractice;

import android.media.Image;

public class Review {
    public Float rating;
    public String comment;
    public String timestamp;
    public Integer helpfulness;
    public String imgUrl;
    public String userName;

    public Review() {
    }

    public Review(Float rating, String comment, String timestamp, Integer helpfulness, String imgUrl, String userName) {
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
        this.helpfulness = helpfulness;
        this.imgUrl = imgUrl;
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public Float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getHelpfulness() {
        return helpfulness;
    }


}
