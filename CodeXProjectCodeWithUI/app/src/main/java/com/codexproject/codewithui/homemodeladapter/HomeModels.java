package com.codexproject.codewithui.homemodeladapter;

public class HomeModels {
    String title , description , zipfile ,tags , image1 , image2 , image3 , documentID;

    public HomeModels(String title, String description, String zipfile, String tags, String image1, String image2, String image3, String documentID) {
        this.title = title;
        this.description = description;
        this.zipfile = zipfile;
        this.tags = tags;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.documentID = documentID;
    }

    public HomeModels(String title, String image1) {
        this.title = title;
        this.image1 = image1;
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

    public String getZipfile() {
        return zipfile;
    }

    public void setZipfile(String zipfile) {
        this.zipfile = zipfile;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
}
