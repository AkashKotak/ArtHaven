package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuyerProfileActivity extends AppCompatActivity {

    private ArrayList<Artwork> artworks;
    private int[] imageViews = {R.id.image_1, R.id.image_2, R.id.image_3, R.id.image_4, R.id.image_5, R.id.image_6, R.id.image_7, R.id.image_8, R.id.image_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_profile);

        // Initialize your artworks ArrayList with your Artwork objects
        artworks = new ArrayList<>();
        artworks.add(new Artwork(R.id.image_1, R.drawable.portfolio_1, "Artwork 1", "$99.99", "Artwork 1 description"));
        artworks.add(new Artwork(R.id.image_2, R.drawable.portfolio_2, "Artwork 2", "$79.99", "Artwork 2 description"));
        artworks.add(new Artwork(R.id.image_3, R.drawable.portfolio_3, "Artwork 3", "$79.99", "Artwork 3 description"));
        artworks.add(new Artwork(R.id.image_4, R.drawable.portfolio_4, "Artwork 1", "$99.99", "Artwork 1 description"));
        artworks.add(new Artwork(R.id.image_5, R.drawable.portfolio_5, "Artwork 2", "$79.99", "Artwork 2 description"));
        artworks.add(new Artwork(R.id.image_6, R.drawable.portfolio_6, "Artwork 3", "$79.99", "Artwork 3 description"));
        artworks.add(new Artwork(R.id.image_7, R.drawable.portfolio_6, "Artwork 1", "$99.99", "Artwork 1 description"));
        artworks.add(new Artwork(R.id.image_8, R.drawable.portfolio_4, "Artwork 2", "$79.99", "Artwork 2 description"));
        artworks.add(new Artwork(R.id.image_9, R.drawable.portfolio_3, "Artwork 3", "$79.99", "Artwork 3 description"));

        displayArtworks();

        // Find views by their IDs
        ImageView profileImageView = findViewById(R.id.user_profile_image);

        TextView nameTextView = findViewById(R.id.user_name);

        // Set profile image using Glide
        Glide.with(this)
                .load(R.drawable.artist_profile_copy)
                .override(100, 100) // Adjust these values according to your needs
                .into(profileImageView);

        // Set name
        nameTextView.setText("Amit Samant's Trending Artwork of the Week");

        // Set up artwork grid
        Button trendingArtButton = findViewById(R.id.trending_art_button);
        trendingArtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMostLikedImagesClick(v);
            }
        });
    }

    private void displayArtworks() {
        for (int i = 0; i < artworks.size(); i++) {
            ImageView imageView = findViewById(imageViews[i]);
            imageView.setImageResource(artworks.get(i).getImageId());
            imageView.setId(artworks.get(i).getViewId());
        }
    }

    public void onMostLikedImagesClick(View view) {
        Intent intent = new Intent(BuyerProfileActivity.this, ArtworkGridActivity.class);
        startActivity(intent);
    }


    private void shuffleArtworks() {
        Collections.shuffle(artworks);
    }

    // Add the onImageClick method
    public void onImageClick(View view) {
        int imageId = view.getId();
        Artwork artwork = getArtworkDetails(imageId);

        if (artwork != null) {
            Intent intent = new Intent(BuyerProfileActivity.this, PhotoDescription.class);
            intent.putExtra("imageId", artwork.getImageId());
            intent.putExtra("title", artwork.getTitle());
            intent.putExtra("price", artwork.getPrice());
            intent.putExtra("description", artwork.getDescription());
            startActivity(intent);
        }
    }

    private Artwork getArtworkDetails(int imageId) {
        // Find the Artwork object based on the clicked ImageView ID
        for (Artwork artwork : artworks) {
            if (artwork.getViewId() == imageId)
                return artwork;
        }
        return null;
    }
}
