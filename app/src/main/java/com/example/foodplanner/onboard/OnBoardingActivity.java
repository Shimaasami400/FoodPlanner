package com.example.foodplanner.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnSkip = findViewById(R.id.tvSkip);
        nextImage = findViewById(R.id.tvNext);
        backImage = findViewById(R.id.tvBack);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0){

                    viewPager.setCurrentItem(getitem(-1),true);

                }

            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 1)
                    viewPager.setCurrentItem(getitem(1),true);
                else {

                    Intent intent = new Intent(OnBoardingActivity.this, Register.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(OnBoardingActivity.this, Register.class);
                startActivity(intent);
                finish();

            }
        });

        viewPager = (ViewPager) findViewById(R.id.onBoardingSlider);
         linearLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position){

        dots = new TextView[2];
        linearLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.mainOrange,getApplicationContext().getTheme()));
            linearLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.mainBrown,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){
                backImage.setVisibility(View.VISIBLE);
            }else {
                backImage.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    private int getitem(int i){
        return viewPager.getCurrentItem() + i;
    }
}