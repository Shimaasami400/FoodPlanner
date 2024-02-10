package com.example.foodplanner.ingredient.presenter;

import com.example.foodplanner.model.dto.ListsDetailsResponse;

import io.reactivex.rxjava3.core.Single;

public interface IngredientDetailsPresenterView {
    public Single<ListsDetailsResponse> getIngredientDetail(String category);
}
