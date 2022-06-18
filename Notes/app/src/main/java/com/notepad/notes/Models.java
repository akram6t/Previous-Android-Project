package com.notepad.notes;

public class Models {
    String title,description,image,time,documentID;

    public Models(String title, String description, String image,String time,String documentID) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.time = time;
        this.documentID = documentID;
    }

    public Models(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
}
