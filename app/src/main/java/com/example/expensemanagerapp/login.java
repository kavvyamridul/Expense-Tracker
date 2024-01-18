package com.example.expensemanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText email_login;
    private EditText password_login;
    private Button button_login;
    private ProgressBar progressBar;
    private TextView register;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        button_login = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressbar);
        register = findViewById(R.id.register);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginUser() {
        String email = email_login.getText().toString().trim();
        String password = password_login.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            email_login.setError("Email Required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            password_login.setError("Password Required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Use Firebase Authentication to log in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Login success
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            // You can navigate to the main activity or any other desired screen here
                            Intent intent = new Intent(login.this, DashboardActivity.class);
                            startActivity(intent);
                            finish(); // Finish the LoginActivity to prevent going back

                        } else {
                            // Login failed
                            Toast.makeText(login.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
