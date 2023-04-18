package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArtistProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_profile);

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
        List<Integer> artworkImages = new ArrayList<>();
        artworkImages.add(R.drawable.portfolio_1);
        artworkImages.add(R.drawable.portfolio_2);
        artworkImages.add(R.drawable.portfolio_3);
        artworkImages.add(R.drawable.portfolio_4);
        artworkImages.add(R.drawable.portfolio_6);
        // Add more images to the list

        ArtworkAdapter artworkAdapter = new ArtworkAdapter(artworkImages);
        Button trendingArtButton = findViewById(R.id.trending_art_button);
        trendingArtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistProfileActivity.this, PhotoDescription.class);
                startActivity(intent);
            }
        });
    }

    // Add the onImageClick method
    public void onImageClick(View view) {
        // Add your desired click action here, for example:
        Intent intent = new Intent(ArtistProfileActivity.this, PhotoDescription.class);
        startActivity(intent);

    }
}
