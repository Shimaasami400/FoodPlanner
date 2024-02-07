package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealRemoteDataSource {
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback);
    public void AreasNetworkCall(AreaMealCallback areaMealCallback);

    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category);
    public Single<MealsItemResponse> searchByNameNetworkCall(String name);

}
