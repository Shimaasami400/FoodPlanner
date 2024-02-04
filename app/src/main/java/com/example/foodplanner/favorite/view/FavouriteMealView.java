package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface FavouriteMealView {
    public void showFavListData(List<MealsItem> mealsItemList);
    public void showFavListErrorMsg(String error);
}
