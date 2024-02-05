package com.example.foodplanner.favorite.presente;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.favorite.view.FavouriteMealView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavouriteMealPresenterImp implements FavouriteMealPresenterView{
    private FavouriteMealView favouriteMealView;
    private MealRepositoryView mealRepositoryView;

    public FavouriteMealPresenterImp(FavouriteMealView favouriteMealView,MealRepositoryView mealRepositoryView){
        this.favouriteMealView = favouriteMealView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<List<MealsItem>> getFavMealList() {
        Log.i("TAG", "getFavMealList: Fav Presenter Live Data ");
        return mealRepositoryView.getFavoriteMeals();
    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {
        mealRepositoryView.deleteMeal(mealsItem);
        Log.i("TAG", "deleteMeal: Presenter");

    }
}
