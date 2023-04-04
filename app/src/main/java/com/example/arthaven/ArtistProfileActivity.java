package com.example.arthaven;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArtistProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_profile);

        // Find views by their IDs
        ImageView profileImageView = findViewById(R.id.profileImageView);

        TextView nameTextView = findViewById(R.id.nameTextView);

        // Set profile image using Glide
        Glide.with(this)
                .load(R.drawable.artist_profile_copy)
                .override(100, 100) // Adjust these values according to your needs
                .into(profileImageView);


        // Set name
        nameTextView.setText("Amit Samant's Trending Artwork of the Week");

        // Set up artwork grid
        RecyclerView artworkRecyclerView = findViewById(R.id.artworkRecyclerView);
        List<Integer> artworkImages = new ArrayList<>();
        artworkImages.add(R.drawable.portfolio_1);
        artworkImages.add(R.drawable.portfolio_2);
        artworkImages.add(R.drawable.portfolio_3);
        artworkImages.add(R.drawable.portfolio_4);
        artworkImages.add(R.drawable.portfolio_6);
// Add more images to the list

        ArtworkAdapter artworkAdapter = new ArtworkAdapter(artworkImages);
        artworkRecyclerView.setAdapter(artworkAdapter);

    }
}
