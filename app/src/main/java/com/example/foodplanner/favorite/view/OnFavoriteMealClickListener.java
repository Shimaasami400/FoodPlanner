package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.dto.MealsItem;

public interface OnFavoriteMealClickListener {
    void onClickItem(String id);
    public void onClick(MealsItem mealsItem);

    void onClickDeleteItem(MealsItem mealsItem);

}
