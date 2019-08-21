package com.nexdev.enyason.nestedrv.Model;


import java.util.List;

/**
 * Created by enyason on 10/7/18.
 */

public class Data {

    List<Movie> list;
    List<Resurantlistdatahorizontal> listh;
    String genre;
    String type;

//    public Data(List<Movie> list) {
//        this.list = list;
//    }

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

    public void setListh(List<Resurantlistdatahorizontal> listh) {
        this.listh = listh;
    }

    public List<Resurantlistdatahorizontal> getListh() {
        return listh;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }
}
