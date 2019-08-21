package com.nexdev.enyason.nestedrv.Model;

/**
 * Created by enyason on 10/3/18.
 */

public class Movie {


    public Movie(String name) {
        this.name = name;
    }

    String id;
    String name;
    String poster;
    String overview;

    String runtime;

    int year;

    String genre;

    double rate;
    String backdrop;

    String age;

    String player;
    String trailer;
    int already_episode;

    public Movie(String id, String name, String poster,
                 String overview) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.overview = overview;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public double getRate() {
        return rate;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getAge() {
        return age;
    }

    public String getPlayer() {
        return player;
    }

    public String getTrailer() {
        return trailer;
    }

    public int getAlready_episode() {
        return already_episode;
    }
}




