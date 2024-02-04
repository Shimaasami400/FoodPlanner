package com.example.foodplanner.model.network.database;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    void  insertMealToFavorite(MealsItem mealsItem);
    void deleteMealFromFavorite(MealsItem mealsItem);
    Single<List<MealsItem>> getAllFavoriteStoredMeals();
}
