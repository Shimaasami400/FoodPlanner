package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface RandomMealCallback {
    //1.
    public void onSuccessResult(List<MealsItem>mealsItem);
    //2.
    public void onFailureResult(String errorMsg);

}
