package com.example.foodplanner.category.presenter;

import com.example.foodplanner.model.dto.ListsDetailsResponse;

import io.reactivex.rxjava3.core.Single;

public interface CategoryDetailsPresenterView {
    public Single<ListsDetailsResponse> getCategoryDetail(String category);
}
