package com.example.foodplanner.category.presenter;

import com.example.foodplanner.category.view.CategoryDetailsView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.CategoryDetails;
import com.example.foodplanner.model.network.network.CategoryDetailsCallback;

import java.util.List;

public class CategoryDetailsPresenterImp implements CategoryDetailsPresenterView, CategoryDetailsCallback {
    private final CategoryDetailsView categoryDetailsView;
    private final MealRepositoryView mealRepositoryView;

    public CategoryDetailsPresenterImp(CategoryDetailsView categoryDetailsView, MealRepositoryView mealRepositoryView) {
        this.categoryDetailsView = categoryDetailsView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public void getCategoryDetail(String category) {
        mealRepositoryView.CategoryDetailsNetworkCall(category,this);
    }

    @Override
    public void onSuccessResult(List<CategoryDetails> categoryDetailsList) {
        categoryDetailsView.showCategoryDetailsData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryDetailsView.showCategoryDetailsErrorMsg(errorMsg);
    }
}
