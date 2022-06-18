package com.example.arbazkhan;

public class ArbazModels {

    String pic ,rid;

    public ArbazModels(){

    }

    public ArbazModels(String pic, String rid) {
        this.pic = pic;
        this.rid = rid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}