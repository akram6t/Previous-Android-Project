package com.salman.moterspart.models;

import com.google.firebase.firestore.ServerTimestamp;

public class ImageModels {
    private String id, title, url,date;

    public ImageModels(){

    }

    public ImageModels(String id, String title, String url, String date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
