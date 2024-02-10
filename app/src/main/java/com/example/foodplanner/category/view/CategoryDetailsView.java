package com.example.foodplanner.category.view;

import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public interface CategoryDetailsView {
    public void showCategoryDetailsData(List<ListsDetails> categoryDetailsList);
    public void showCategoryDetailsErrorMsg(String error);
}
