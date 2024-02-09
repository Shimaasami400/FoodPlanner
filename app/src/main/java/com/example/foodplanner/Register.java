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

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText txtName;
    EditText txtMail;
    EditText txtPassword;
    EditText txtDoubleCheckPassword;
    Button btnsignUp;
    TextView tvlogin;

    String email,password;

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
        setContentView(R.layout.activity_register);
        txtName = findViewById(R.id.editTextText2);
        txtMail = findViewById(R.id.editTextTextEmailAddress);
        txtPassword = findViewById(R.id.editTextTextPassword);
        btnsignUp = findViewById(R.id.button);
        tvlogin = findViewById(R.id.tvRegist);

        mAuth = FirebaseAuth.getInstance();

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtMail.getText().toString();
                password = txtPassword.getText().toString();
                //Checking if email and password are empty or not.
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account is created successfully",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, Home.class);
                                    startActivity(intent);
                                    finish(); // Finish the current activity to prevent the user from going back to it
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
