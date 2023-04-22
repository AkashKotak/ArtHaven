package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Artwork> cartItems;
    private Button proceedToCheckoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        proceedToCheckoutButton = findViewById(R.id.proceed_to_checkout_button);

        // Dummy data for cart items
        cartItems = new ArrayList<>();
        cartItems.add(new Artwork(R.id.image_1, R.drawable.portfolio_1, "Artwork 1", "$99.99", "Artwork 1 description"));
        cartItems.add(new Artwork(R.id.image_2, R.drawable.portfolio_2, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_3, R.drawable.portfolio_3, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_4, R.drawable.portfolio_4, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_5, R.drawable.portfolio_5, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_6, R.drawable.portfolio_6, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_7, R.drawable.portfolio_6, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_8, R.drawable.portfolio_4, "Artwork 2", "$79.99", "Artwork 2 description"));
        cartItems.add(new Artwork(R.id.image_9, R.drawable.portfolio_3, "Artwork 2", "$79.99", "Artwork 2 description"));

        cartAdapter = new CartAdapter(cartItems);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        Button proceedToCheckoutButton = findViewById(R.id.proceed_to_checkout_button);
        proceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your desired action here, for example:
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}
