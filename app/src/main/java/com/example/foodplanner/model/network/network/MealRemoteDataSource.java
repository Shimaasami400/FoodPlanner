package com.example.foodplanner.model.network.network;

public interface MealRemoteDataSource {
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback);
    public void AreasNetworkCall(AreaMealCallback areaMealCallback);

    public void CategoryDetailsNetworkCall(String category, CategoryDetailsCallback categoryDetailsCallback);
}
