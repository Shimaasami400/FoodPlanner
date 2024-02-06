package com.example.foodplanner.Area.view;

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

public class AreaDetailsAdapter extends RecyclerView.Adapter<AreaDetailsAdapter.ViewHolder>{
    private Context context;
    private List<ListsDetails> areaDetailsList;
    private OnAreaDetailsClickListener areaDetailsClickListener;
    public AreaDetailsAdapter(Context context, List<ListsDetails> areaDetailsList, OnAreaDetailsClickListener areaDetailsClickListener){
        this.context = context;
        this.areaDetailsList = areaDetailsList;
        this.areaDetailsClickListener = areaDetailsClickListener;
    }
    public void setList(List<ListsDetails> areaDetailsList) {
        this.areaDetailsList = areaDetailsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AreaDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.area_detail_list_tem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaDetailsAdapter.ViewHolder holder, int position) {
        ListsDetails areaDetailsItem = areaDetailsList.get(position);
        holder.txtAreaItemName.setText(areaDetailsItem.getStrMeal());
        Glide.with(context).load(areaDetailsItem.getStrMealThumb()).into(holder.areaItemImage);

    }

    @Override
    public int getItemCount() {
        return areaDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView areaItemImage;
        private TextView txtAreaItemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            areaItemImage = itemView.findViewById(R.id.areaDetailsItemImage);
            txtAreaItemName = itemView.findViewById(R.id.txtareaDetailsItemName);
        }
    }
}
