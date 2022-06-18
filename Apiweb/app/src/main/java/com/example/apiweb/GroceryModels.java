package com.example.apiweb;

public class GroceryModels {
    int id;
    String name , image ;
    double price;

    public GroceryModels(int id, String name, String image, double price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }
    public GroceryModels(){

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
