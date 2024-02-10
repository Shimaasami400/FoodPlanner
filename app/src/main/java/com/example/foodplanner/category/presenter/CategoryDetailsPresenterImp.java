package com.example.foodplanner.category.presenter;

import com.example.foodplanner.category.view.CategoryDetailsView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.network.network.CategoryDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryDetailsPresenterImp implements CategoryDetailsPresenterView, CategoryDetailsCallback {
    private final CategoryDetailsView categoryDetailsView;
    private final MealRepositoryView mealRepositoryView;

    public CategoryDetailsPresenterImp(CategoryDetailsView categoryDetailsView, MealRepositoryView mealRepositoryView) {
        this.categoryDetailsView = categoryDetailsView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public Single<ListsDetailsResponse> getCategoryDetail(String category) {
        return mealRepositoryView.CategoryDetailsNetworkCall(category);
    }

    @Override
    public void onSuccessResult(List<ListsDetails> categoryDetailsList) {
        categoryDetailsView.showCategoryDetailsData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryDetailsView.showCategoryDetailsErrorMsg(errorMsg);
    }
}
