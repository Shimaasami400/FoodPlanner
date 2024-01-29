package com.example.foodplanner.model;

import com.example.foodplanner.model.network.CategoryCallBack;
import com.example.foodplanner.model.network.RandomMealCallback;

public interface MealRepositoryView {
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
    public void CategoryNetworkCall(CategoryCallBack categoryCallBack);

}
