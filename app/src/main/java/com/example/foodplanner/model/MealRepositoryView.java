package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.network.AreaMealCallback;
import com.example.foodplanner.model.network.network.CategoryCallBack;
import com.example.foodplanner.model.network.network.IngredientsCallback;
import com.example.foodplanner.model.network.network.RandomMealCallback;

import java.util.List;

public interface MealRepositoryView {
    //Remote
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
    public void CategoryNetworkCall(CategoryCallBack categoryCallBack);
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback);
    public void AreasNetworkCall(AreaMealCallback areaMealCallback);


    //Local
    public LiveData<List<MealsItem>> getFavoriteMeals();
    public void deleteMeal(MealsItem mealsItem);
    public void insertMeal(MealsItem mealsItem);


    LiveData<List<MealsItem>> getFavoriteMealsLiveData(); // Add this method

}
