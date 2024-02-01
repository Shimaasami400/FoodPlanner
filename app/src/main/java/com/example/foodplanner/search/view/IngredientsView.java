package com.example.foodplanner.search.view;

import com.example.foodplanner.model.dto.IngredientsItem;


import java.util.List;

public interface IngredientsView {
    public void showIngredientsData(List<IngredientsItem> IngredientsItemList);
    public void showIngredientsErrorMsg(String error);
}
