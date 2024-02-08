package com.example.foodplanner.mealdetail.presenter;

import com.example.foodplanner.model.dto.MealsItem;

public interface MealDetailPresenterView {
    public void SetClickedItemData(MealsItem mealsItem);
    public void addToFav(MealsItem mealsItem);
}
