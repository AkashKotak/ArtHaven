package com.example.arthaven;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_profile);

        // Find views by their IDs
        ImageView profileImageView = findViewById(R.id.profileImageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        GridView artworkGrid = findViewById(R.id.artworkGrid);

        // Set profile image
        profileImageView.setImageResource(R.drawable.artist_profile_copy);

        // Set name
        nameTextView.setText("Amit Samant");

        // Set up artwork grid
        int[] artworkImages = {R.drawable.portfolio_1, R.drawable.portfolio_2, R.drawable.portfolio_3,
                R.drawable.portfolio_4, R.drawable.portfolio_5, R.drawable.portfolio_6};
        ArtworkAdapter artworkAdapter = new ArtworkAdapter(this, artworkImages);
        artworkGrid.setAdapter(artworkAdapter);
    }
}
