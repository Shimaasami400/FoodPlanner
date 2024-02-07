package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface SearchByNameCallback {
    public void onSuccessResult(List<MealsItem> searchByNameList);
    public void onFailureResult(String errorMsg);
}
