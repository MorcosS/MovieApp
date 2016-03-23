package com.example.android.movieapp.MovieData;

/**
 * Created by MorcosS on 3/22/16.
 */
public class MovieJasonData {
    String description, releaseDate, title, image;
    double rate;

    public MovieJasonData(String description, String releaseDate, String title, String image, double rate) {
        this.description = description;
        this.releaseDate = releaseDate;
        this.title = title;
        this.image = image;
        this.rate = rate;
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
}
