package com.example.foodplanner.mealdetail.view;

import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface MealDetailView {
    public void showItemDetailData(MealsItem mealsItem);
    public void showItemDetailErrorMsg(String error);
}
