package com.example.foodplanner.favorite.presente;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FavouriteMealPresenterView {
    public Single<List<MealsItem>> getFavMealList();
    public void deleteMeal(MealsItem mealsItem);
}