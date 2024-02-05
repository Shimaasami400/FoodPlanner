package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.dto.CategoryDetails;

import java.util.List;

public interface CategoryDetailsCallback {
    public void onSuccessResult(List<CategoryDetails> categoryDetailsList);
    public void onFailureResult(String errorMsg);
}
