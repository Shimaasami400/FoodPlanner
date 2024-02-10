package com.example.foodplanner.listsdetail.presenter;

import com.example.foodplanner.listsdetail.view.ListDetailView;
import com.example.foodplanner.mealdetail.view.MealDetailView;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsDetail;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.network.MealDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ListDetailPresenterImp implements ListDetailPresenterView, MealDetailsCallback {
    private ListDetailView listDetailView;
    private MealsDetail mealsDetail;
    private MealRepositoryView mealRepositoryView;

    public ListDetailPresenterImp(ListDetailView listDetailView,MealRepositoryView mealRepositoryView){
        this.listDetailView = listDetailView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<MealsDetailResponse> getMealDetail(String category) {
        return mealRepositoryView.getMealByIdNetworkCall(category);
    }

    @Override
    public void addToFav(MealsDetail mealsDetail) {
        this.mealsDetail = mealsDetail;
       // mealRepositoryView.insertMeal(mealsDetail);
    }

    @Override
    public void onSuccessResult(List<MealsDetail> mealsDetailList) {
        listDetailView.showMealDetailData(mealsDetailList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        listDetailView.showMealDetailErrorMsg(errorMsg);
    }
}
