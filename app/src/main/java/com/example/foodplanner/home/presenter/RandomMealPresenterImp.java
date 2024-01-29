package com.example.foodplanner.home.presenter;

import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.home.view.CategoryMealView;
import com.example.foodplanner.home.view.RandomMealView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.network.RandomMealCallback;

import java.util.List;

public class RandomMealPresenterImp implements RandomMealPresenterView, RandomMealCallback {

    private final RandomMealView randomMealView;
    private final MealRepositoryView mealRepositoryView;

    public RandomMealPresenterImp(RandomMealView randomMealView, MealRepositoryView mealRepositoryView){
        this.randomMealView = randomMealView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public void getMeal() {
        mealRepositoryView.RandomMealNetworkCall(this);
    }

    @Override
    //the Random meal
    public void onSuccessResult(List<MealsItem> mealsItemList) {
            randomMealView.showData(mealsItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        randomMealView.showErrorMsg(errorMsg);
    }
}
