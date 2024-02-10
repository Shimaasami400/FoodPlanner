package com.example.foodplanner.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.home.view.Home;
import com.example.foodplanner.R;
import com.example.foodplanner.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            Intent intent = new Intent(getApplicationContext(), Home.class);
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

                Intent intent = new Intent(Login.this, Home.class);
                intent.putExtra("guestMode", true);
                startActivity(intent);
                finish();
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
                                getUserEmail(email, email -> Log.i("TAG", "onEmailReceived: " + email));
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    private String encodeEmailForFirebase(String email) {
        return email.replace(".",",");
    }

    public void getUserEmail(String email, OnLoginListener onLoginListener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String encodedEmail = encodeEmailForFirebase(email);
        databaseReference.child(encodedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue(String.class);
                onLoginListener.onReceivingMail(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}