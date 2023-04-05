package com.example.arthaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailEditText.getText().toString().trim();
                final String password = passwordEditText.getText().toString().trim();

                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            boolean authenticated = false;
                            String userType = null;

                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                String storedPassword = userSnapshot.child("password").getValue(String.class);
                                if (storedPassword != null && storedPassword.equals(password)) {
                                    authenticated = true;
                                    userType = userSnapshot.child("userType").getValue(String.class);
                                    break;
                                }
                            }

                            if (authenticated) {
                                if (userType != null) {
                                    if (userType.equals("Artist")) {
                                        Intent intent = new Intent(LoginActivity.this, ArtistProfileActivity.class);
                                        startActivity(intent);
                                    } else if (userType.equals("Buyer")) {
                                        Intent intent = new Intent(LoginActivity.this, BuyerProfileActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Invalid user type", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "User type not found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "User not found in the database.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "getUserType:onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }

        interface UserTypeCallback {
        void onUserTypeReceived(String userType);
    }

    private void getUserType(String email, UserTypeCallback callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userType = null;
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        userType = userSnapshot.child("userType").getValue(String.class);
                    }
                    callback.onUserTypeReceived(userType);
                } else {
                    Toast.makeText(LoginActivity.this, "User not found in the database.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getUserType:onCancelled", databaseError.toException());
            }
        });
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                String email = user.getEmail();

                                getUserType(email, new UserTypeCallback() {
                                    @Override
                                    public void onUserTypeReceived(String userType) {
                                        if (userType.equals("Artist")) {
                                            Intent intent = new Intent(LoginActivity.this, ArtistProfileActivity.class);
                                            startActivity(intent);
                                        } else if (userType.equals("Buyer")) {
                                            Intent intent = new Intent(LoginActivity.this, BuyerProfileActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Invalid user type", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(LoginActivity.this, "User not found.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

