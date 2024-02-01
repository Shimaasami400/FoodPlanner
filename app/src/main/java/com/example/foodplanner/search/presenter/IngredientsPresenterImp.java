package com.example.foodplanner.search.presenter;

import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.IngredientsItem;
import com.example.foodplanner.model.network.IngredientsCallback;
import com.example.foodplanner.search.view.IngredientsView;

import java.util.List;

public class IngredientsPresenterImp implements IngredientsPresenterView, IngredientsCallback {

    private IngredientsView ingredientsView;
    private MealRepositoryView mealRepositoryView;
    public IngredientsPresenterImp(IngredientsView ingredientsView,MealRepositoryView mealRepositoryView){
        this.ingredientsView = ingredientsView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public void onSuccessResult(List<IngredientsItem> IngredientsItemList) {
        ingredientsView.showIngredientsData(IngredientsItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        ingredientsView.showIngredientsErrorMsg(errorMsg);
    }

    @Override
    public void getIngredient() {
        mealRepositoryView.IngredientsNetworkCall(this);
    }
}
