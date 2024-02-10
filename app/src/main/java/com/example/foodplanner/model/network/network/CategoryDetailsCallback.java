package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.ListsDetails;

import java.util.List;

public interface CategoryDetailsCallback {
    public void onSuccessResult(List<ListsDetails> categoryDetailsList);
    public void onFailureResult(String errorMsg);
}
