package com.example.naveenkanumoori.inclass07;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Naveenkanumoori on 10/3/16.
 */
public class Feed implements Serializable {
    String title;
    String smallImage;
    String largeImage;
    String desctription;
    Date date;
    Boolean isSearch;

    public Feed(String title, String smallImage, String largeImage, String desctription, Date date) {
        this.title = title;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.desctription = desctription;
        this.date = date;
        this.isSearch = false;
    }

    public String getTitle() {
        return title;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public String getDesctription() {
        return desctription;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getSearch() {
        return isSearch;
    }

    public void setSearch(Boolean search) {
        isSearch = search;
    }
}
