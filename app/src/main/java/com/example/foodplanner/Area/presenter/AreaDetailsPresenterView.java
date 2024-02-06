package com.example.foodplanner.Area.presenter;

import com.example.foodplanner.model.dto.ListsDetailsResponse;

import io.reactivex.rxjava3.core.Single;

public interface AreaDetailsPresenterView {
    public Single<ListsDetailsResponse> getAreaDetail(String category);
}
