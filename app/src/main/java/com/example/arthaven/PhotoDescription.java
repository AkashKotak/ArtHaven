package com.example.arthaven;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        // Set values for UI elements
        productImage.setImageResource(R.drawable.portfolio_2);
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
