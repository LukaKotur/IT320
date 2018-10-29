package com.example.luko9.myapplication.Entity;

import java.io.Serializable;

public class TvShows implements Serializable {
    private int id;
    private String name;
    private String summary;
    private String image;
    private String bigImage;
    private String days;
    private String status;
    private String genres;
    private String runtime;
    private String weight;
    private String isFavourite;
    private String rating;

    public TvShows(int id, String name, String summary, String image, String bigImage, String days, String status, String genres, String runtime, String weight, String isFavourite, String rating) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.image = image;
        this.bigImage = bigImage;
        this.days = days;
        this.status = status;
        this.genres = genres;
        this.runtime = runtime;
        this.weight = weight;
        this.isFavourite = isFavourite;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }

    public String getBigImage() {
        return bigImage;
    }

    public String getDays() {
        return days;
    }

    public String getStatus() {
        return status;
    }

    public String getGenres() {
        return genres;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(String isFavourite) {
        this.isFavourite = isFavourite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setWeight(String rating) {
        this.weight = rating;
    }

    public TvShows() {

    }
}
