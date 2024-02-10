package com.example.foodplanner.searchbar.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Area.view.AreaDetailsAdapter;
import com.example.foodplanner.Area.view.OnAreaDetailsClickListener;
import com.example.foodplanner.R;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public class SearchByNameAdapter extends RecyclerView.Adapter<SearchByNameAdapter.ViewHolder>{
    private Context context;
    private List<MealsItem> searchByNameList;
    private OnSearchByNameClickListener searchByNameClickListener;

    public SearchByNameAdapter(Context context, List<MealsItem> searchByNameList,OnSearchByNameClickListener searchByNameClickListener){
        this.context = context;
        this.searchByNameList = searchByNameList;
        this.searchByNameClickListener = searchByNameClickListener;
    }
    public void setList(List<MealsItem> searchByNameList) {
        this.searchByNameList = searchByNameList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchByNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_by_name_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByNameAdapter.ViewHolder holder, int position) {
        MealsItem mealsItem  = searchByNameList.get(position);
        holder.txtsearchByName.setText(mealsItem.getStrMeal());
        Glide.with(context).load(mealsItem.getStrMealThumb()).into(holder.searchByNameItemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchByNameClickListener != null) {
                    searchByNameClickListener.onSearchByNameClick(mealsItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchByNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView searchByNameItemImage;
        private TextView txtsearchByName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchByNameItemImage = itemView.findViewById(R.id.searchByNameItemImage);
            txtsearchByName = itemView.findViewById(R.id.txtSearchByNameItem);
        }
    }
}
