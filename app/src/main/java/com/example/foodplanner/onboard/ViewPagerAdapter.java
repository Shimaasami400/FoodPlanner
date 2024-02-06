package com.example.foodplanner.onboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int[] animations = {R.raw.plate , R.raw.barbecue};

    int[] headerText = {R.string.heading_one,R.string.heading_two};

    int[] descriptionText = {R.string.desc_one, R.string.desc_two};

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return animations.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.on_boarding_slider,container,false);

        LottieAnimationView slideLotti = view.findViewById(R.id.lottiAnimation);
        TextView slideHeader = view.findViewById(R.id.tvHeader);
        TextView slideDescription = view.findViewById(R.id.tvDescription);

        slideLotti.setAnimation(animations[position]);
        slideHeader.setText(headerText[position]);
        slideDescription.setText(descriptionText[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
