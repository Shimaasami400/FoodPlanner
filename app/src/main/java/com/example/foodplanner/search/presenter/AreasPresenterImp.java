package com.example.foodplanner.search.presenter;

import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.AreaItem;
import com.example.foodplanner.model.network.AreaMealCallback;
import com.example.foodplanner.model.network.IngredientsCallback;
import com.example.foodplanner.search.view.AreasView;
import com.example.foodplanner.search.view.IngredientsView;

import java.util.List;

public class AreasPresenterImp implements AreasPresenterView, AreaMealCallback {

    private AreasView areasView;
    private MealRepositoryView mealRepositoryView;
    public AreasPresenterImp(AreasView areasView,MealRepositoryView mealRepositoryView){
        this.areasView = areasView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public void onSuccessResult(List<AreaItem> areaItemsItem) {
        areasView.showAreasData(areaItemsItem);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        areasView.showAreasErrorMsg(errorMsg);
    }

    @Override
    public void getArea() {
        mealRepositoryView.AreasNetworkCall(this);
    }
}
