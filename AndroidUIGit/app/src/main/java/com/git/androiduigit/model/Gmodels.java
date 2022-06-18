package com.git.androiduigit.model;

public class Gmodels {

    String t,st,l,img,type;

    public Gmodels(String t, String st, String l, String img, String type) {
        this.t = t;
        this.st = st;
        this.l = l;
        this.img = img;
        this.type= type;
    }
    public Gmodels(){}


    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
