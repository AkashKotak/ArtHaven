package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photodescription);

        Button trendingArtButton = findViewById(R.id.add_to_cart_button);
        trendingArtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoDescription.this, CartActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Integer imageId = intent.getIntExtra("imageId", -1);
            String title = intent.getStringExtra("title");
            String price = intent.getStringExtra("price");
            String description = intent.getStringExtra("description");

            if (imageId != -1 && title != null && price != null && description != null) {
                ImageView imageView = findViewById(R.id.product_image);
                imageView.setImageResource(imageId);

                TextView titleView = findViewById(R.id.product_title);
                titleView.setText(title);

                TextView priceView = findViewById(R.id.product_price );
                priceView.setText(price);

                TextView descriptionView = findViewById(R.id.product_description);
                descriptionView.setText(description);


            } else {
                Toast.makeText(this, "Error: Artwork details not available", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        } else {
            Toast.makeText(this, "Error: No artwork data received", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        }

    }

}
