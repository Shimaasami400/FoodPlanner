package com.example.foodplanner.home.view;

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
import com.example.foodplanner.model.dto.CategoriesItem;

import java.util.List;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.ViewHolder>{
    private Context context;
    List<CategoriesItem> categoriesItemList;
    private OnCategoryClickListener categoryClickListener;
    public MealCategoryAdapter(Context context, List<CategoriesItem> categoriesItemList, OnCategoryClickListener categoryClickListener) {
        this.context = context;
        this.categoriesItemList =  categoriesItemList;
        this.categoryClickListener = categoryClickListener;
    }

    public MealCategoryAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CategoriesItem> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
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
    public void onBindViewHolder(@NonNull MealCategoryAdapter.ViewHolder holder, int position) {
        CategoriesItem categoriesItem = categoriesItemList.get(position);

        holder.txtCategoryName.setText(categoriesItem.getStrCategory());
        Glide.with(context).load(categoriesItem.getStrCategoryThumb()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return categoriesItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView txtCategoryName;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            txtCategoryName = itemView.findViewById(R.id.txtItemName);
        }
    }
}
