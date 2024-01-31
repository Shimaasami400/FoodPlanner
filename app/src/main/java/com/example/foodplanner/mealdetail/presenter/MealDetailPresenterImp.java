package com.example.foodplanner.mealdetail.presenter;

import com.example.foodplanner.mealdetail.view.MealDetailView;
import com.example.foodplanner.model.dto.MealsItem;

public class MealDetailPresenterImp implements MealDetailPresenterView {

    private MealDetailView mealDetailView;
    private MealsItem mealsItem;

   public MealDetailPresenterImp(MealDetailView mealDetailView){
        this.mealDetailView = mealDetailView;
    }
    @Override
    public void SetClickedItemData(MealsItem mealsItem){
       this.mealsItem = mealsItem;
        mealDetailView.showItemDetailData(mealsItem);
    }
}
