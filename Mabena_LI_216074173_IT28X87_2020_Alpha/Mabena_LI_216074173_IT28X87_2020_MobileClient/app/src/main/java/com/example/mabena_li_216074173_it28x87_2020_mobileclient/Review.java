package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

public class Review {
    int review_id;
    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }
    int review_rating;
    String review_description;
    int review_Reviewer_id;
    int review_Accommodation_Reviewed;

    public int getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(int review_rating) {
        this.review_rating = review_rating;
    }

    public String getReview_description() {
        return review_description;
    }

    public void setReview_description(String review_description) {
        this.review_description = review_description;
    }

    public int getReview_Reviewer_id() {
        return review_Reviewer_id;
    }

    public void setReview_Reviewer_id(int review_Reviewer_id) {
        this.review_Reviewer_id = review_Reviewer_id;
    }

    public int getReview_Accommodation_Reviewed() {
        return review_Accommodation_Reviewed;
    }

    public void setReview_Accommodation_Reviewed(int review_Accommodation_Reviewed) {
        this.review_Accommodation_Reviewed = review_Accommodation_Reviewed;
    }
}
