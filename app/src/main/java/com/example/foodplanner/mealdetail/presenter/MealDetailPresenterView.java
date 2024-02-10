package com.example.foodplanner.mealdetail.presenter;

import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;

public interface MealDetailPresenterView {
    public void SetClickedItemData(WeekPlan selectedDate);
    public void addToFav(MealsItem mealsItem);
}
