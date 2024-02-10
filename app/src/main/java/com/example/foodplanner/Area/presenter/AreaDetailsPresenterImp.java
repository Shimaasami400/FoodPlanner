package com.example.foodplanner.Area.presenter;

import com.example.foodplanner.Area.view.AreaDetailsView;
import com.example.foodplanner.ingredient.presenter.IngredientDetailsPresenterView;
import com.example.foodplanner.ingredient.view.IngredientDetailsView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.network.network.AreaDetailsCallback;
import com.example.foodplanner.model.network.network.IngredientDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class AreaDetailsPresenterImp implements AreaDetailsPresenterView, AreaDetailsCallback {
    private final AreaDetailsView areaDetailsView;
    private final MealRepositoryView mealRepositoryView;

    public AreaDetailsPresenterImp(AreaDetailsView areaDetailsView, MealRepositoryView mealRepositoryView) {
        this.areaDetailsView = areaDetailsView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<ListsDetailsResponse> getAreaDetail(String category) {
        return mealRepositoryView.AreaDetailsNetworkCall(category);
    }

    @Override
    public void onSuccessResult(List<ListsDetails> categoryDetailsList) {
        areaDetailsView.showAreaDetailsData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        areaDetailsView.showAreaDetailsErrorMsg(errorMsg);
    }
}
