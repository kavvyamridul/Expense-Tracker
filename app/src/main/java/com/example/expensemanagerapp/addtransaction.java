package com.example.expensemanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class addtransaction extends AppCompatActivity {
    public static List<ExpenseTransaction> transactionList = new ArrayList<>();


    private EditText amountEditText;
    private EditText detailsEditText;
    private Button addButton;
    private List<ExpenseTransaction> transactionList;
    private ArrayAdapter<ExpenseTransaction> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtransaction);

        amountEditText = findViewById(R.id.amountEditText);
        detailsEditText = findViewById(R.id.detailsEditText);
        addButton = findViewById(R.id.addbutton);

        // Initialize the transactionList and adapter
        transactionList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransactionAndReturnToDashboard();
            }
        });
    }

    private void addTransactionAndReturnToDashboard() {
        // Get the entered amount and details
        String amount = amountEditText.getText().toString();
        String details = detailsEditText.getText().toString();

        // Validate input
        if (amount.isEmpty() || details.isEmpty()) {
            // Handle validation, show an error, etc.
            return;
        }

        // Create a new ExpenseTransaction object
        ExpenseTransaction transaction1 = new ExpenseTransaction(amount, details);

        // Add the transaction to the global list in DashboardActivity
        DashboardActivity.transactionList.add(transaction1);

        // Add the transaction to the local list
        transactionList.add(transaction1);

        // Set up the ListView
        ListView transactionListView = findViewById(R.id.transactionListView);
        transactionListView.setAdapter(adapter);

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();

        // Optionally, clear the input fields
        amountEditText.setText("");
        detailsEditText.setText("");
    }
}
