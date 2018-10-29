package com.example.luko9.myapplication.Entity;



public class TvShowActor {
    private String name;
    private String character;
    private String image;
    private String url;

    public TvShowActor(String name, String character, String image, String url) {
        this.name = name;
        this.character = character;
        this.image = image;
        this.url = url;
    }

    public TvShowActor() {
    }

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
