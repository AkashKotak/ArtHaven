package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoDescription extends AppCompatActivity {

    private ImageView productImage;
    private TextView productTitle;
    private TextView productPrice;
    private TextView productDescription;
    private Button addToCartButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photodescription);

        // Get references to UI elements
        productImage = findViewById(R.id.product_image);
        productTitle = findViewById(R.id.product_title);
        productPrice = findViewById(R.id.product_price);
        TextView productDescriptionTitle = findViewById(R.id.product_description_title);
        productDescription = findViewById(R.id.product_description);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        // Get the image position and the artwork images list from the Intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imagePosition = extras.getInt("image_position");
            List<Integer> artworkImages = extras.getIntegerArrayList("artwork_images");

            int imageResId = artworkImages.get(imagePosition);

            // Set the image resource to the ImageView
            Glide.with(this)
                    .load(imageResId)
                    .centerCrop()
                    .into(productImage);

            // Set values for UI elements
            productTitle.setText("Product Title");
            productPrice.setText("$99.99");
            productDescriptionTitle.setText("Product Description");
            productDescription.setText("Product description goes here...");

            // Set click listener for Add to Cart button
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add code to handle button click
                }
            });
        }
    }
}
