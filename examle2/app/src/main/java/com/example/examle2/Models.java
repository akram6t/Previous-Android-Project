package com.example.examle2;

public class Models {
    String title , link ;

    public Models(String title, String link) {
        this.title = title;
        this.link = link;
    }
    public Models() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
