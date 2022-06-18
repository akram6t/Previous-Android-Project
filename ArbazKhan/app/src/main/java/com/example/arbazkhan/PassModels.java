package com.example.arbazkhan;

public class PassModels {

    String pname, ppass;

    public PassModels(String pname, String ppass) {
        this.pname = pname;
        this.ppass = ppass;
    }

    public PassModels() {

    }


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPpass() {
        return ppass;
    }

    public void setPpass(String ppass) {
        this.ppass = ppass;
    }
}
