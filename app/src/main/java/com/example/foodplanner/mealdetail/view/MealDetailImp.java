package com.example.foodplanner.mealdetail.view;

import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterImp;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterView;
import com.example.foodplanner.model.dto.MealsItem;

public class MealDetailImp implements MealDetailView{
    MealDetailPresenterView mealDetailPresenterView;
    void MealDetailImp(MealDetailPresenterImp mealDetailPresenterView){
        this.mealDetailPresenterView = mealDetailPresenterView;
    }
    @Override
    public void showItemDetailData(MealsItem mealsItem) {
        mealDetailPresenterView.SetClickedItemData(mealsItem);
    }

    @Override
    public void showItemDetailErrorMsg(String error) {

    }
}
