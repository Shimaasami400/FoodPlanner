package com.example.foodplanner.model.network.database;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface MealLocalDataSource {
    void  insertMealToFavorite(MealsItem mealsItem);
    void deleteMealFromFavorite(MealsItem mealsItem);
    LiveData<List<MealsItem>> getAllFavoriteStoredMeals();
}
