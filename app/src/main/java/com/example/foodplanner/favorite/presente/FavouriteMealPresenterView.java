package com.example.foodplanner.favorite.presente;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface FavouriteMealPresenterView {
    public LiveData<List<MealsItem>> getFavMealList();
    public void deleteMeal(MealsItem mealsItem);
}
