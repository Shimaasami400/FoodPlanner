package com.example.foodplanner.weekplan.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.favorite.view.FavouriteMealView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;
import com.example.foodplanner.weekplan.view.WeekPlanMealView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekPlanMealPresenterImp implements WeekPlanMealPresenterView{

    private WeekPlanMealView weekPlanMealView;
    private MealRepositoryView mealRepositoryView;

    public WeekPlanMealPresenterImp(WeekPlanMealView weekPlanMealView,MealRepositoryView mealRepositoryView){
        this.weekPlanMealView = weekPlanMealView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<List<WeekPlan>> getWeekPlanMealList() {
        return mealRepositoryView.getWeekPlanMeals();
    }

    @Override
    public void deleteMeal(WeekPlan weekPlan) {
        mealRepositoryView.deleteWeekPlanMeal(weekPlan);
        Log.i("TAG", "deleteMeal: Presenter");
    }

    @Override
    public Single<List<WeekPlan>> getMealsForDate(String date) {
        return mealRepositoryView.getMealsForDate(date);
    }


}
