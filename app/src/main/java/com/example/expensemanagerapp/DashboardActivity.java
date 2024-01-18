package com.example.expensemanagerapp;

// DashboardActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.core.Transaction;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    public static List<Transaction> transactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        // Set an onClickListener for the logout button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout logic
                // For example, you can add FirebaseAuth.getInstance().signOut();

                // Navigate back to the login screen
                Intent loginIntent = new Intent(DashboardActivity.this, login.class);
                startActivity(loginIntent);

                // Finish the current activity (dashboard) to prevent navigating back to it
                finish();
            }
        });

        // Set an onClickListener for the "Add Transaction" Button
        Button addTransactionButton = findViewById(R.id.addTransactionButton);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the AddTransaction page
                Intent addTransactionIntent = new Intent(DashboardActivity.this, addtransaction.class);
                startActivity(addTransactionIntent);
            }
        });
    }
}