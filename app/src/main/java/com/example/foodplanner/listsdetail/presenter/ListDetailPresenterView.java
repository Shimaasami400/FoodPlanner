package com.example.foodplanner.listsdetail.presenter;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsDetail;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItem;

import io.reactivex.rxjava3.core.Single;

public interface ListDetailPresenterView {
    public Single<MealsDetailResponse> getMealDetail(String category);
    public void addToFav(MealsDetail mealsDetail);
}
