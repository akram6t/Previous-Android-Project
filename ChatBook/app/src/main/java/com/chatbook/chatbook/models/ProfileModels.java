package com.chatbook.chatbook.models;

public class ProfileModels {
    String name,profile,uid,number;
    public ProfileModels(String n,String p,String u,String number){
        this.name = n;
        this.profile = p;
        this.uid = u;
        this.number = number;
    }
    public ProfileModels(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
