package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumberEditText, expirationDateEditText, cvvEditText, nameOnCardEditText, amountEditText;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        // Find views by their IDs
        cardNumberEditText = findViewById(R.id.card_number);
        expirationDateEditText = findViewById(R.id.expiry_date);
        cvvEditText = findViewById(R.id.cvv);
        payButton = findViewById(R.id.pay_button);

        // Set up pay button click listener
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from the EditText views
                String cardNumber = cardNumberEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String cvv = cvvEditText.getText().toString();
                String nameOnCard = nameOnCardEditText.getText().toString();
                String amount = amountEditText.getText().toString();

                // Validate input (example validation code)
                if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty() || nameOnCard.isEmpty() || amount.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Example payment processing code
                    // Replace this with your own payment processing logic
                    Toast.makeText(PaymentActivity.this, "Processing payment...", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity


                        // Add your desired action here, for example:
                    Intent intent = new Intent(PaymentActivity.this, lastpage.class);
                    startActivity(intent);
                    }
                }


        });
    }
}