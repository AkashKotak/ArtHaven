package com.example.arthaven;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class lastpage extends AppCompatActivity {

    private Button payButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastpage);
        payButton = findViewById(R.id.button1);

        // Set up pay button click listener
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(lastpage.this, ArtistProfileActivity.class);
                startActivity(intent);
            }


        });

    }
}