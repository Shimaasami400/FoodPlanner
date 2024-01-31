package com.example.foodplanner.mealdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.home.view.MealCategoryAdapter;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterView;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public class IngridentsAdapter extends RecyclerView.Adapter<IngridentsAdapter.ViewHolder> {

    private MealDetailPresenterView mealDetailPresenterView;
    private Context context;
    List<MealsItem> mealItemDetailList;

   public IngridentsAdapter(Context context){
        this.context = context;
   }
   public void setMealItemDetailList(List<MealsItem>mealItemDetailList){
       this.mealItemDetailList = mealItemDetailList;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngridentsAdapter.ViewHolder holder, int position) {
        MealsItem mealsItem = mealItemDetailList.get(position);
        holder.tvIngridentName.setText(mealsItem.getStrMeal());
        Glide.with(context).load(mealsItem.getStrMealThumb()).into(holder.ingridentImage);
    }

    @Override
    public int getItemCount() {
       if(mealItemDetailList!=null) {
           return mealItemDetailList.size();
       }
       else
           return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingridentImage;
        private TextView tvIngridentName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingridentImage = itemView.findViewById(R.id.imageViewIngredientImageItem);
            tvIngridentName = itemView.findViewById(R.id.textViewIngredientNameItem);
        }
    }
}
