package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.MealsDetail;

import java.util.List;

public interface MealDetailsCallback {
    public void onSuccessResult(List<MealsDetail> mealsDetailList);
    public void onFailureResult(String errorMsg);
}
