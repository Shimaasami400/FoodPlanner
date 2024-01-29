package com.example.foodplanner.model.network;

import com.example.foodplanner.model.dto.CategoriesItemResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    public Call<MealsItemResponse> getRandomMeal();
    @GET("categories.php")
    public Call<CategoriesItemResponse>getCategory();
}
