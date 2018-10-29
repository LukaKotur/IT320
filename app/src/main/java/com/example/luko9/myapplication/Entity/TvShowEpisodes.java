package com.example.luko9.myapplication.Entity;

import java.io.Serializable;

public class TvShowEpisodes implements Serializable {
    private String episodeName;
    private String episodeNumber;
    private String seasonNumber;
    private String summary;
    private String img;
    private String url;

    public TvShowEpisodes(String episodeName, String episodeNumber, String seasonNumber, String summary, String img, String url) {
        this.episodeName = episodeName;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.summary = summary;
        this.img = img;
        this.url = url;
    }


    public String getEpisodeName() {
        return episodeName;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }

    public String getSummary() {
        return summary;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }
}
