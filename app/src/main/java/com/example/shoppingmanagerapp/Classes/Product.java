package com.example.shoppingmanagerapp.Classes;

public class Product {
    private String name;
    private int image;
    private int quantity;

    public Product(String name, int image, int quantity) {
        this.name = name;
        this.image = image;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public int getImage() { return image; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
