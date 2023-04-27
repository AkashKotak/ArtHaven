package com.example.arthaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText firstName, lastName;
    TextView emailEditText, passwordEditText, confirmPasswordEditText;
    Button registerButton;
    ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    Spinner userTypeSpinner;
    ImageView profileImageView;
    Uri profileImageUri;

    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize DatabaseReference
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        // Initialize views
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        userTypeSpinner = findViewById(R.id.userType);
        profileImageView = findViewById(R.id.profileImageView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        userTypeSpinner.setAdapter(adapter);

        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            profileImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), profileImageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void PerformAuth() {
        String inputEmail = emailEditText.getText().toString();
        String inputPassword = passwordEditText.getText().toString();
        String confirmInputPassword = confirmPasswordEditText.getText().toString();

        if (userType.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please select a user type", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!inputPassword.equals(confirmInputPassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user data in the Realtime Database
        saveUserDataToDatabase(inputEmail, inputPassword);
    }

    private void saveUserDataToDatabase(String email, String password) {
        // Create a unique identifier for the new user
        String uid = mDatabase.push().getKey();

        // Create a HashMap to store user data
        HashMap<String, String> userDataMap = new HashMap<>();
        userDataMap.put("userType", userType);
        userDataMap.put("email", email);
        userDataMap.put("password", password);
        userDataMap.put("firstName", firstName.getText().toString());
        userDataMap.put("lastName", lastName.getText().toString());

        // Upload the profile image
        uploadProfileImage(uid, userDataMap);
    }

    private void uploadProfileImage(String uid, HashMap<String, String> userDataMap) {
        if (profileImageUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("profileImages/" + uid);
            UploadTask uploadTask = storageReference.putFile(profileImageUri);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        userDataMap.put("profileImageUrl", downloadUri.toString());
                        saveUserDataToDatabase(uid, userDataMap);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error uploading profile image: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            saveUserDataToDatabase(uid, userDataMap);
        }
    }

    private void saveUserDataToDatabase(String uid, HashMap<String, String> userDataMap) {
        // Store the user data in the Realtime Database
        mDatabase.child(uid).setValue(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registration Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
