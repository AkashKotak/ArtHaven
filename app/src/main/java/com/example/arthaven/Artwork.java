package com.example.arthaven;

import java.io.Serializable;

public class Artwork implements Serializable {
    private int imageId;
    private int viewId;
    private String title;
    private String price;
    private String description;

    public Artwork(int imageId, String title, String price, String description) {
        this.imageId = imageId;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Artwork(int viewId, int imageId, String title, String price, String description) {
        this.viewId = viewId;
        this.imageId = imageId;
        this.title = title;
        this.price = price;
        this.description = description;
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

    public int getViewId() {
        return viewId;
    }
}
