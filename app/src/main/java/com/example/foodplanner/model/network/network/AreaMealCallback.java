package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.AreaItem;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface AreaMealCallback {
    public void onSuccessResult(List<AreaItem> areaItemsItem);
    //2.
    public void onFailureResult(String errorMsg);
}
