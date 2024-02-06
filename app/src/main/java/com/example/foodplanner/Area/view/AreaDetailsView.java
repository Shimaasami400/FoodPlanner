package com.example.foodplanner.Area.view;

import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public interface AreaDetailsView {
    public void showAreaDetailsData(List<ListsDetails> categoryDetailsList);
    public void showAreaDetailsErrorMsg(String error);
}
