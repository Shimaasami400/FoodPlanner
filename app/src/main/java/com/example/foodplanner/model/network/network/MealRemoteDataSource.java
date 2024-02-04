package com.example.foodplanner.model.network.network;

public interface MealRemoteDataSource {
    public void RandomMealNetworkCall(RandomMealCallback networkCallback);
   //public void CategoryNetworkCall(CategoryCallBack categoryCallBack);
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback);
    public void AreasNetworkCall(AreaMealCallback areaMealCallback);
}
