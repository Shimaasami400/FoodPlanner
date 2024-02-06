package com.example.foodplanner.ingredient.view;

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
import com.example.foodplanner.category.view.CategoryDetailsAdapter;
import com.example.foodplanner.category.view.OnCategoryDetailsClickListener;
import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public class IngredientDetailsAdapter extends RecyclerView.Adapter<IngredientDetailsAdapter.ViewHolder>{
    private Context context;
    private List<ListsDetails> ingredientDetailsList;
    private OnIngredientDetailsClickListener ingredientDetailsClickListener;

    public IngredientDetailsAdapter(Context context,  List<ListsDetails> ingredientDetailsList, OnIngredientDetailsClickListener ingredientDetailsClickListener){
        this.context = context;
        this.ingredientDetailsList = ingredientDetailsList;
        this.ingredientDetailsClickListener = ingredientDetailsClickListener;
    }
    public void setList(List<ListsDetails> ingredientDetailsList) {
        this.ingredientDetailsList = ingredientDetailsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public IngredientDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredient_detail_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientDetailsAdapter.ViewHolder holder, int position) {
        ListsDetails categoriesDetailsItem = ingredientDetailsList.get(position);
        holder.txtIngredientItemName.setText(categoriesDetailsItem.getStrMeal());
        Glide.with(context).load(categoriesDetailsItem.getStrMealThumb()).into(holder.ingredientItemImage);
    }

    @Override
    public int getItemCount() {
        return ingredientDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingredientItemImage;
        private TextView txtIngredientItemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientItemImage = itemView.findViewById(R.id.ingredientDetailsItemImage);
            txtIngredientItemName = itemView.findViewById(R.id.txtingredientDetailsItemName);
        }
    }
}
