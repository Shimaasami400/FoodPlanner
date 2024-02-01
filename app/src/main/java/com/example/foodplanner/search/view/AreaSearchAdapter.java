package com.example.foodplanner.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.dto.AreaItem;
import com.example.foodplanner.model.dto.IngredientsItem;

import java.util.List;

public class AreaSearchAdapter extends RecyclerView.Adapter<AreaSearchAdapter.ViewHolder>{
    private Context context;
    List<AreaItem> areaItemList;
    private OnAreaClickListener onAreaClickListener;

    public AreaSearchAdapter(Context context, List<AreaItem> areaItemList, OnAreaClickListener onAreaClickListener) {
        this.context = context;
        this.areaItemList =  areaItemList;
        this.onAreaClickListener = onAreaClickListener;
    }

    public AreaSearchAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<AreaItem> areaItemList) {
        this.areaItemList = areaItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AreaSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_country_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaSearchAdapter.ViewHolder holder, int position) {
        AreaItem areaItem = areaItemList.get(position);
        holder.txtAreaName.setText(areaItem.getStrArea());
    }

    @Override
    public int getItemCount() {
        return areaItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAreaName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAreaName = itemView.findViewById(R.id.txtArea);
        }
    }
}
