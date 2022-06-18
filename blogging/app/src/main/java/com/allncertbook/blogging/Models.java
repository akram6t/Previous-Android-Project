package com.allncertbook.blogging;

public class Models {
    String profile ,name , image;
    int like , comment;

    public Models(String profile, String name, String image, int like, int comment) {
        this.profile = profile;
        this.name = name;
        this.image = image;
        this.like = like;
        this.comment = comment;
    }
    public Models () {

    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
