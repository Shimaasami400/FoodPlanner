package com.example.foodplanner.model;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.network.AreaMealCallback;
import com.example.foodplanner.model.network.network.CategoryCallBack;
import com.example.foodplanner.model.network.network.IngredientsCallback;
import com.example.foodplanner.model.network.network.RandomMealCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealRepositoryView {
    //Remote
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
    public void CategoryNetworkCall(CategoryCallBack categoryCallBack);
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback);
    public void AreasNetworkCall(AreaMealCallback areaMealCallback);
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category);


    //Local
    public Single<List<MealsItem>> getFavoriteMeals();
    public void deleteMeal(MealsItem mealsItem);
    public void insertMeal(MealsItem mealsItem);


    Single<List<MealsItem>> getFavoriteMealsSingle();


}
