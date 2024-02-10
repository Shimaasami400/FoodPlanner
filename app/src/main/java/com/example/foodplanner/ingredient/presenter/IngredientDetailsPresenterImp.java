package com.example.foodplanner.ingredient.presenter;

import com.example.foodplanner.ingredient.view.IngredientDetailsView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.network.network.IngredientDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class IngredientDetailsPresenterImp implements IngredientDetailsPresenterView, IngredientDetailsCallback {
    private final IngredientDetailsView ingredientDetailsView;
    private final MealRepositoryView mealRepositoryView;

    public IngredientDetailsPresenterImp(IngredientDetailsView ingredientDetailsView, MealRepositoryView mealRepositoryView) {
        this.ingredientDetailsView = ingredientDetailsView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public Single<ListsDetailsResponse> getIngredientDetail(String category) {
        return mealRepositoryView.IngredientDetailsNetworkCall(category);
    }

    @Override
    public void onSuccessResult(List<ListsDetails> categoryDetailsList) {
        ingredientDetailsView.showIngredientDetailsData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        ingredientDetailsView.showIngredientDetailsErrorMsg(errorMsg);
    }
}
