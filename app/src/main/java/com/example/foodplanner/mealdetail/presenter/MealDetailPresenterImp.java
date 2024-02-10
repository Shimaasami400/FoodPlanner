package com.example.foodplanner.mealdetail.presenter;

import android.net.ConnectivityManager;

import com.example.foodplanner.mealdetail.view.MealDetailView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;
import com.example.foodplanner.model.network.database.MealDAO;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.RandomMealCallback;

import java.util.List;

public class MealDetailPresenterImp implements MealDetailPresenterView{

    private MealDetailView mealDetailView;
    private MealRepositoryView mealRepositoryView;

    public MealDetailPresenterImp(MealDetailView mealDetailView, MealRepositoryView mealRepositoryView) {
        this.mealDetailView = mealDetailView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public void SetClickedItemData(WeekPlan selectedDate) {
        mealRepositoryView.insertWeekPlanMeal(selectedDate);
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        // Implement your logic to add the meal to favorites
        mealRepositoryView.insertMeal(mealsItem);
    }

    private void addToCalendar(MealsItem mealsItem, String selectedDate) {

        System.out.println("Selected Date: " + selectedDate);
        System.out.println("Meal Details: " + mealsItem);
    }

}
