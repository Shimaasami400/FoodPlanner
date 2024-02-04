package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.AreaItemResponse;
import com.example.foodplanner.model.dto.CategoriesItemResponse;
import com.example.foodplanner.model.dto.IngredientsItemResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    public Call<MealsItemResponse> getRandomMeal();
    @GET("categories.php")
    public Call<CategoriesItemResponse>getCategory();
    @GET("list.php?i=list")
    public Call<IngredientsItemResponse>getIngredients();
    @GET("list.php?a=list")
    public Call<AreaItemResponse>getAreas();

   /* @GET("filter.php")
    public Single<RootMealPreview> getMealsByCategory(@Query("c") String category);*/
}
