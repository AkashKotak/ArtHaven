package com.example.arthaven;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumberEditText, expirationDateEditText, cvvEditText, nameOnCardEditText;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        // Find views by their IDs
        cardNumberEditText = findViewById(R.id.card_number);
        expirationDateEditText = findViewById(R.id.expiry_date);
        cvvEditText = findViewById(R.id.cvv);
        nameOnCardEditText = findViewById(R.id.name_on_card);
        payButton = findViewById(R.id.pay_button);

        // Set input types for EditText views
        cardNumberEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        expirationDateEditText.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        cvvEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Set up pay button click listener
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from the EditText views
                String cardNumber = cardNumberEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String cvv = cvvEditText.getText().toString();
                String nameOnCard = nameOnCardEditText.getText().toString();

                // Validate input
                if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty() || nameOnCard.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidCardType(cardNumber)) {
                    Toast.makeText(PaymentActivity.this, "Invalid card type", Toast.LENGTH_SHORT).show();
                } else if (!isValidExpiryDate(expirationDate)) {
                    Toast.makeText(PaymentActivity.this, "Invalid expiry date", Toast.LENGTH_SHORT).show();
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

    private boolean isValidCardType(String cardNumber) {
        String visaRegex = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCardRegex = "^(5[1-5][0-9]{14}|2[2-7][0-9]{14})$";

        return cardNumber.matches(visaRegex) || cardNumber.matches(masterCardRegex);
    }

    private boolean isValidExpiryDate(String expiryDate) {
        String expiryDateRegex = "^(0[1-9]|1[0-2])\\/([0-9]{2})$";
        return expiryDate.matches(expiryDateRegex);
    }
}
