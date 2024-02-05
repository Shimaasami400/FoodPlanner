package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.CategoriesItem;

import java.util.List;

public interface CategoryCallBack {
    public void onSuccessResult(List<CategoriesItem> categoriesItemList);
    //2.
    public void onFailureResult(String errorMsg);

}
