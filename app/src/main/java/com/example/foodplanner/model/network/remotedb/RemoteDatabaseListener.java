package com.example.foodplanner.model.network.remotedb;

import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;

import io.reactivex.rxjava3.core.Completable;

public interface RemoteDatabaseListener {
    Completable insertToFavorite(MealsItem mealsItem);
    Completable insertToWeekPlan(WeekPlan weekPlan);
    void deleteFromWeekPlane(WeekPlan weekPlan);
    void deleteFromFavorite(MealsItem mealsItem);
}
