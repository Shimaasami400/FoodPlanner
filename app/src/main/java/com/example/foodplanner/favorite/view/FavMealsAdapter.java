package com.example.foodplanner.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealdetail.IngridentsAdapter;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder>{
    private Context context;
    private List<MealsItem> favMealList;
    private OnFavoriteMealClickListener onFavoriteMealClickListener;

    public FavMealsAdapter(Context context){
        this.context = context;
    }
    public FavMealsAdapter(Context context,List<MealsItem> favmealList,OnFavoriteMealClickListener onFavoriteMealClickListener){
        this.context = context;
        this.favMealList = favMealList;
        this.onFavoriteMealClickListener = onFavoriteMealClickListener;
    }
    public void setMealFavList(List<MealsItem>favMealList){
        this.favMealList = favMealList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fav_list_item,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealsAdapter.ViewHolder holder, int position) {
        MealsItem mealsItem = favMealList.get(position);
        holder.mealName.setText(mealsItem.getStrMeal());
        holder.mealCountry.setText(mealsItem.getStrArea());
        Glide.with(context).load(mealsItem.getStrMealThumb()).into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
         return favMealList != null ? favMealList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage;
        private TextView mealName;
        private TextView mealCountry;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.MealImage);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealCountry = itemView.findViewById(R.id.tvMealCountry);
            cardView = itemView.findViewById(R.id.favCardView);
        }
    }
}