package com.example.arthaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyerProfileActivity extends AppCompatActivity {

    private ImageView userProfileImage;
    private TextView userName;
    private TextView userBio;
    private RecyclerView userPostsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_profile);

        userProfileImage = findViewById(R.id.user_profile_image);
        userName = findViewById(R.id.user_name);
        userBio = findViewById(R.id.user_bio);
        userPostsGrid = findViewById(R.id.user_posts_grid);

        // Initialize the RecyclerView with a GridLayoutManager
        userPostsGrid.setLayoutManager(new GridLayoutManager(this, 2));

        // Set up the data for the RecyclerView
        // TODO: Implement the logic to fetch and display data in the RecyclerView

        Button trendingArtButton = findViewById(R.id.trending_art_button);
        trendingArtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerProfileActivity.this, PhotoDescription.class);
                startActivity(intent);
            }
        });
    }
}
