package com.example.foodplanner.model;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsDetail;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.MealsItemResponse;
import com.example.foodplanner.model.dto.WeekPlan;
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
    public Single<MealsItemResponse> SearchByNameNetworkCall(String name);
    public Single<MealsDetailResponse> getMealByIdNetworkCall(String name);


    //Local
    public Single<List<MealsItem>> getFavoriteMeals();
    public Single<List<MealsDetail>>getListMealDetails();
    public void deleteMeal(MealsItem mealsItem);
    public void insertMeal(MealsItem mealsItem);
    public void deleteMeal(MealsDetail mealsItem);
    public void insertMeal(MealsDetail mealsItem);

    public Single<List<WeekPlan>> getWeekPlanMeals();
    Single<List<WeekPlan>> getMealsForDate(String date);
    public void deleteWeekPlanMeal( WeekPlan weekPlan);
    public void insertWeekPlanMeal(WeekPlan weekPlan);




    Single<List<MealsItem>> getFavoriteMealsSingle();


}
