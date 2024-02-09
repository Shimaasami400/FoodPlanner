package com.example.foodplanner.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.Register;

public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout linearLayout;
    TextView btnSkip;
    TextView nextImage,backImage;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_KEY_ONBOARDING_SHOWN = "onboardingShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Check if onboarding screens have been shown before
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean onboardingShown = prefs.getBoolean(PREF_KEY_ONBOARDING_SHOWN, false);
        if (onboardingShown) {
            // Onboarding already shown, navigate to Register activity directly
            goToRegisterActivity();
        }

        btnSkip = findViewById(R.id.tvSkip);
        nextImage = findViewById(R.id.tvNext);
        backImage = findViewById(R.id.tvBack);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0) {
                    viewPager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) < 1)
                    viewPager.setCurrentItem(getitem(1), true);
                else {
                    // On last page, navigate to Register activity
                    goToRegisterActivity();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Skip button clicked, navigate to Register activity
                goToRegisterActivity();
            }
        });

        viewPager = findViewById(R.id.onBoardingSlider);
        linearLayout = findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    private void goToRegisterActivity() {
        // Navigate to Register activity
        Intent intent = new Intent(OnBoardingActivity.this, Register.class);
        startActivity(intent);
        finish();

        // Set onboarding shown flag in SharedPreferences
        setOnboardingShown();
    }

    private void setOnboardingShown() {
        // Update SharedPreferences to indicate onboarding screens have been shown
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_KEY_ONBOARDING_SHOWN, true);
        editor.apply();
    }

    public void setUpindicator(int position) {
        dots = new TextView[2];
        linearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.mainOrange, getApplicationContext().getTheme()));
            linearLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.mainBrown, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position > 0) {
                backImage.setVisibility(View.VISIBLE);
            } else {
                backImage.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private int getitem(int i) {
        return viewPager.getCurrentItem() + i;
    }
}