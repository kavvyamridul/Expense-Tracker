package com.example.expensemanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    private EditText email_register;
    private EditText password_register;
    private Button button_register;
    private ProgressBar progressBar;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email_register= findViewById(R.id.email_register);
        password_register=findViewById(R.id.password_register);
        button_register=findViewById(R.id.button_register);
        progressBar=findViewById(R.id.progressbar);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
                                 }
        );

        // Assuming that you have already defined email_register, password_register, and button_register

// Inside your onCreate or some initialization method

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = email_register.getText().toString().trim();
                String password = password_register.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    email_register.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    password_register.setError("Password Required");
                    return;
                }

                // Use Firebase Authentication to register the user
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    // Registration success
                                    Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                    // You can navigate to the main activity or any other desired screen here
                                    Intent intent = new Intent(register.this, login.class);
                                    startActivity(intent);
                                    finish(); // Finish the RegisterActivity to prevent going back

                                } else {
                                    // Registration failed
                                    Toast.makeText(register.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });




    }
}