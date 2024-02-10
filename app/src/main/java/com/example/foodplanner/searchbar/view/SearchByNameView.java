package com.example.foodplanner.searchbar.view;

import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public interface SearchByNameView {
    public void showSearchByNameData(List<MealsItem> searchByNameList);
    public void showSearchByNameErrorMsg(String error);
}
