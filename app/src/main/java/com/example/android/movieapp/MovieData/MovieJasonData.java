package com.example.android.movieapp.MovieData;

import java.util.ArrayList;

/**
 * Created by MorcosS on 3/22/16.
 */
public class MovieJasonData {
    String description, releaseDate, title, image;
    int id;
    double rate;
    ArrayList<String> key;
    ArrayList<String> reviewAuthors;
    ArrayList<String> reviews;

    public MovieJasonData(String description, String releaseDate, String title, String image, double rate, int id, ArrayList<String> trailerList, ArrayList<String> authorReviews,ArrayList<String> reviews) {
        this.description = description;
        this.releaseDate = releaseDate;
        this.title = title;
        this.image = image;
        this.rate = rate;
        this.id = id;
        this.key = trailerList;
        this.reviewAuthors=authorReviews;
        this.reviews=reviews;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getRate() {
        return rate;
    }

    public int getID(){
        return id;
    }

    public ArrayList<String>getKey() {
        return key;
    }

    public ArrayList<String> getReviewAuthors() {
        return reviewAuthors;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }
}
