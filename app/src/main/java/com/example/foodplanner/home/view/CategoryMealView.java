package com.example.foodplanner.home.view;

import com.example.foodplanner.model.dto.CategoriesItem;

import java.util.List;

public interface CategoryMealView {
    public void showCategoryData(List<CategoriesItem> categoriesItemList);
    public void showErrorMsgCategory(String error);
}
