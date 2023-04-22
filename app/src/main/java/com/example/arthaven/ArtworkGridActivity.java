package com.example.arthaven;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ArtworkGridActivity extends AppCompatActivity {

    private ArrayList<Artwork> artworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artwork_grid);

        // Initialize your artworks ArrayList with your Artwork objects
        artworks = new ArrayList<>();
        artworks.add(new Artwork(R.id.image_1, R.drawable.portfolio_1, "Artwork 1", "$99.99", "Artwork 1 description"));
        artworks.add(new Artwork(R.id.image_2, R.drawable.portfolio_2, "Artwork 2", "$79.99", "Artwork 2 description"));
        artworks.add(new Artwork(R.id.image_3, R.drawable.portfolio_3, "Artwork 3", "$79.99", "Artwork 3 description"));

        displayArtworks();
    }

    private void displayArtworks() {
        GridLayout gridLayout = findViewById(R.id.artwork_grid);

        for (Artwork artwork : artworks) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(artwork.getImageId());
            imageView.setId(artwork.getViewId());
            imageView.setPadding(8, 8, 8, 8);

            gridLayout.addView(imageView);
        }
    }
}
