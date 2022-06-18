package com.notepad.dbdemo.SQLModels;

public class SqlModels {
    private int id;
    private String name,prize;

    public SqlModels(int id, String name, String prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public SqlModels(String name, String prize) {
        this.name = name;
        this.prize = prize;
    }

    public SqlModels(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
