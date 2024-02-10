package com.example.foodplanner.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodplanner.R;
import com.example.foodplanner.Register;
import com.example.foodplanner.onboard.OnBoardingActivity;

public class Splash extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSharedPreferences = getSharedPreferences("mySp", MODE_PRIVATE);
                boolean isFirstTime = mSharedPreferences.getBoolean("firstTimeToOpen", true);

                boolean isAuthenticated = false;
                if (isFirstTime) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();
                    Intent intent = new Intent(Splash.this, OnBoardingActivity.class);
                    //Intent intent = new Intent(Splash.this,Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } /*else if (AuthenticationFireBaseRepo.getInstance().isAuthenticated()) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } */ else {
                    Intent intent = new Intent(Splash.this, Register.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

        }, 4000);

        /*MealsRepo mealsRepo = MealsRepo.getInstance();
        mealsRepo.getRandomMealObservable();
        mealsRepo.getRootIngredientObservable();
        mealsRepo.getYouMightLikeMealsObservable();*/
    }
    }
