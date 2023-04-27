package com.example.arthaven;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Artwork> artworks;

    private Cart() {
        artworks = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void addArtwork(Artwork artwork) {
        artworks.add(artwork);
    }
}
