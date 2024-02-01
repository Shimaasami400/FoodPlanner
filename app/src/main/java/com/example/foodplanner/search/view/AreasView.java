package com.example.foodplanner.search.view;

import com.example.foodplanner.model.dto.AreaItem;
import com.example.foodplanner.model.dto.IngredientsItem;

import java.util.List;

public interface AreasView {
    public void showAreasData(List<AreaItem> AreaItemList);
    public void showAreasErrorMsg(String error);
}
