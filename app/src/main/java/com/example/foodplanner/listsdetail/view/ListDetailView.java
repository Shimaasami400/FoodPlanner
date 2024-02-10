package com.example.foodplanner.listsdetail.view;

import com.example.foodplanner.model.dto.MealsDetail;

import java.util.List;

public interface ListDetailView {
    public void showMealDetailData(List<MealsDetail> mealsItem);
    public void addMealToFav(MealsDetail mealsItem);
    public void showMealDetailErrorMsg(String error);
}
