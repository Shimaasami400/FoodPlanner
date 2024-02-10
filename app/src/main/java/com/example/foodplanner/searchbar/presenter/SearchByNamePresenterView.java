package com.example.foodplanner.searchbar.presenter;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;

import io.reactivex.rxjava3.core.Single;

public interface SearchByNamePresenterView {
    public Single<MealsItemResponse> getMealName(String name);
}
