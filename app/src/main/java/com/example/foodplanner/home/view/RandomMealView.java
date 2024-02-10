package com.example.foodplanner.home.view;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface RandomMealView {
    public void showData(List<MealsItem> mealsItemList);
    public void showErrorMsg(String error);
}
