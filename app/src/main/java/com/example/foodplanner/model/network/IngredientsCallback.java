package com.example.foodplanner.model.network;

import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.dto.IngredientsItem;

import java.util.List;

public interface IngredientsCallback {
    public void onSuccessResult(List<IngredientsItem> IngredientsItemList);
    //2.
    public void onFailureResult(String errorMsg);
}
