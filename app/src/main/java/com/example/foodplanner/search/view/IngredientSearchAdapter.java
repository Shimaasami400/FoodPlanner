package com.example.foodplanner.search.view;

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
import com.example.foodplanner.model.dto.IngredientsItem;

import java.util.List;

public class IngredientSearchAdapter extends RecyclerView.Adapter<IngredientSearchAdapter.ViewHolder>{
    private Context context;
    List<IngredientsItem> ingredientsItemList;
    private OnIngredientsClickListener oningredientsClickListener;

    public IngredientSearchAdapter(Context context, List<IngredientsItem> ingredientsItemList, OnIngredientsClickListener oningredientsClickListener) {
        this.context = context;
        this.ingredientsItemList =  ingredientsItemList;
        this.oningredientsClickListener = oningredientsClickListener;
    }

    public IngredientSearchAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<IngredientsItem> ingredientsItemList) {
        this.ingredientsItemList = ingredientsItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_ingredients_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientSearchAdapter.ViewHolder holder, int position) {
        IngredientsItem ingredientsItem = ingredientsItemList.get(position);
        holder.txtingredientName.setText(ingredientsItem.getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredientsItem.getStrIngredient() + "-Small.png").into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredientsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingredientImage;
        private TextView txtingredientName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.imageViewIngredientImageItem);
            txtingredientName = itemView.findViewById(R.id.textViewIngredientNameItem);
        }
    }
}
