package com.example.foodplanner.category.view;

import com.example.foodplanner.model.dto.CategoryDetails;

import java.util.List;

public interface CategoryDetailsView {
    public void showCategoryDetailsData(List<CategoryDetails> categoryDetailsList);
    public void showCategoryDetailsErrorMsg(String error);
}
