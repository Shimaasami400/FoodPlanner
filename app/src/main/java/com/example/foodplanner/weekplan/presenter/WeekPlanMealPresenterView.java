package com.example.foodplanner.weekplan.presenter;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface WeekPlanMealPresenterView {
    public Single<List<WeekPlan>> getWeekPlanMealList();
    public void deleteMeal(WeekPlan weekPlan);
    Single<List<WeekPlan>> getMealsForDate(String date);
}
