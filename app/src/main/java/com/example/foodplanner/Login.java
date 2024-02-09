package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  EditText txtMail;
    private EditText txtPassword;
    private Button btnlogIn;
    private TextView tvregister;
    private TextView tvSkip;

    private String email,password;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtMail = findViewById(R.id.editTextTextEmailAddress);
        txtPassword = findViewById(R.id.editTextTextPassword);
        btnlogIn = findViewById(R.id.button);
        tvregister = findViewById(R.id.tvLogin);
        mAuth = FirebaseAuth.getInstance();
        tvSkip = findViewById(R.id.tvLoginSkip);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home activity directly without authentication
                Intent intent = new Intent(Login.this, Home.class);
                // Set the guest mode flag in the intent
                intent.putExtra("guestMode", true);
                startActivity(intent);
                finish(); // Finish the current activity to prevent the user from going back to it
            }
        });



        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtMail.getText().toString();
                password = txtPassword.getText().toString();

                // Checking if email and password are empty or not.
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please Enter Your Email and Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                    finish(); // Finish the current activity to prevent the user from going back to it
                                } else {
                                    Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}