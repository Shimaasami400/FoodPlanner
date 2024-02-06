package com.example.foodplanner.category.view;

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
import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.ViewHolder>{
    private Context context;
    private List<ListsDetails> categoryDetailsList;
    private OnCategoryDetailsClickListener categoryDetailsClickListener;

    public CategoryDetailsAdapter(Context context, List<ListsDetails> categoryDetailsList, OnCategoryDetailsClickListener categoryDetailsClickListener){
        this.context = context;
        this.categoryDetailsList = categoryDetailsList;
        this.categoryDetailsClickListener = categoryDetailsClickListener;
    }

    public void setList(List<ListsDetails> categoryDetailsList) {
        this.categoryDetailsList = categoryDetailsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_details_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsAdapter.ViewHolder holder, int position) {
        ListsDetails categoriesDetailsItem = categoryDetailsList.get(position);
        holder.txtCategoryItemName.setText(categoriesDetailsItem.getStrMeal());
        Glide.with(context).load(categoriesDetailsItem.getStrMealThumb()).into(holder.categoryItemImage);

    }

    @Override
    public int getItemCount() {
        return categoryDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryItemImage;
        private TextView txtCategoryItemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryItemImage = itemView.findViewById(R.id.categoryDetailsItemImage);
            txtCategoryItemName = itemView.findViewById(R.id.txtCategoryDetailsItemName);
        }
    }
}
