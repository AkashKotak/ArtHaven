package com.example.arthaven;

public class Artwork {
    private int viewId;
    private int imageId;
    private String title;
    private String price;
    private String description;

    public Artwork(int viewId, int imageId, String title, String price, String description) {
        this.viewId = viewId;
        this.imageId = imageId;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public int getViewId() {
        return viewId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
