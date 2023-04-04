package com.example.arthaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    TextView emailInputLayout, passwordInputLayout, confirmPasswordInputLayout;
    TextView emailEditText, passwordEditText, confirmPasswordEditText;
    Button registerButton;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    RadioGroup userTypeRadioGroup;
    RadioButton normalUserRadioButton, artistRadioButton;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //userTypeRadioGroup = findViewById(R.id.userTypeRadioGroup);
        //normalUserRadioButton = findViewById(R.id.normalUserRadioButton);
        //artistRadioButton = findViewById(R.id.artistRadioButton);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        emailInputLayout = findViewById(R.id.emailEditText);
        passwordInputLayout = findViewById(R.id.passwordEditText);
        confirmPasswordInputLayout = findViewById(R.id.confirmPasswordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        progressDialog = new ProgressDialog(RegisterActivity.this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String inputEmail=emailInputLayout.getText().toString();
        String inputPassword=passwordInputLayout.getText().toString();
        String confirmInputPassword=confirmPasswordEditText.getText().toString();
        int selectedId = userTypeRadioGroup.getCheckedRadioButtonId();
        String userType = "";
        if (selectedId == normalUserRadioButton.getId()) {
            userType = "buyer";
        } else if (selectedId == artistRadioButton.getId()) {
            userType = "artist";
        } else {
            Toast.makeText(RegisterActivity.this, "Please select a user type", Toast.LENGTH_SHORT).show();
            return;
        }
            mAuth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }



    private void sendUserToNextActivity() {
        Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
