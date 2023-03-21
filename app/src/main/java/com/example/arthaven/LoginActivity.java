package com.example.arthaven;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform authentication logic here
               // if (email.equals("example@email.com") && password.equals("password123")) {
                    // If user is authenticated, launch the artist profile page
                   //Intent intent = new Intent(LoginActivity.this, ArtistProfileActivity.class);
                    //startActivity(intent);
                //} //else {
                    // Otherwise, show an error message or dialog box
                   // Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
               // }
            }

        });
    }
}
