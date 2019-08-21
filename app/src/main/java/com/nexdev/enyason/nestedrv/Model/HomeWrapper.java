package com.nexdev.enyason.nestedrv.Model;

public class HomeWrapper {

    private int viewType;
    private Movie movie;
    private Banner banner;

    public HomeWrapper(int viewType, Movie movie) {
        this.viewType = viewType;
        this.movie = movie;
    }

    public HomeWrapper(int viewType, Banner banner) {
        this.viewType = viewType;
        this.banner = banner;
    }

    public int getViewType() {
        return viewType;
    }

    public Movie getMovie() {
        return movie;
    }

    public Banner getBanner() {
        return banner;
    }
}
