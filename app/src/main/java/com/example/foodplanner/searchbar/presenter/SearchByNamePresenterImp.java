package com.example.foodplanner.searchbar.presenter;

import com.example.foodplanner.Area.view.AreaDetailsView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.MealsItemResponse;
import com.example.foodplanner.model.network.network.SearchByNameCallback;
import com.example.foodplanner.searchbar.view.SearchByNameView;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SearchByNamePresenterImp implements SearchByNamePresenterView, SearchByNameCallback {
    private final SearchByNameView searchByNameView;
    private final MealRepositoryView mealRepositoryView;

    public SearchByNamePresenterImp(SearchByNameView searchByNameView, MealRepositoryView mealRepositoryView) {
        this.searchByNameView = searchByNameView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public void onSuccessResult(List<MealsItem> searchByNameList) {
        searchByNameView.showSearchByNameData(searchByNameList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        searchByNameView.showSearchByNameErrorMsg(errorMsg);
    }

    @Override
    public Single<MealsItemResponse>getMealName(String name) {
        return mealRepositoryView.SearchByNameNetworkCall(name);
    }
}
