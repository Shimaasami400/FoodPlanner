package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.view.CategoryMealView;
import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.network.CategoryCallBack;

import java.util.List;

public class CategoryMealPresenterImp implements CategoryMealPresenterView, CategoryCallBack {
    private final CategoryMealView categoryMealView;
    private final MealRepositoryView repositoryView;


    public CategoryMealPresenterImp(CategoryMealView categoryMealView, MealRepositoryView repositoryView){
        this.categoryMealView = categoryMealView;
        this.repositoryView = repositoryView;
    }

    @Override
    public void getCategory() {
        repositoryView.CategoryNetworkCall(this);
    }
     @Override
     public void onSuccessResult(List<CategoriesItem> categoriesItemList) {
         categoryMealView.showCategoryData(categoriesItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryMealView.showErrorMsgCategory(errorMsg);
    }
}
