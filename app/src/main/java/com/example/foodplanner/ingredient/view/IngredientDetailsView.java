package com.example.foodplanner.ingredient.view;

import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public interface IngredientDetailsView {
    public void showIngredientDetailsData(List<ListsDetails> categoryDetailsList);
    public void showIngredientDetailsErrorMsg(String error);
}
