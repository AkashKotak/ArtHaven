package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private Button proceedToCheckoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        proceedToCheckoutButton = findViewById(R.id.proceed_to_checkout_button);
        proceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PaymentActivity
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        List<Artwork> cartItems = Cart.getInstance().getArtworks();

        RecyclerView recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter(cartItems));
    }
}
