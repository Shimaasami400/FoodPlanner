package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.AreaItemResponse;
import com.example.foodplanner.model.dto.CategoriesItemResponse;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.IngredientsItemResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;

import io.reactivex.rxjava3.core.Single;
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

    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByIngredient(@Query("i") String category);
    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByArea(@Query("a") String category);

    @GET("search.php")
    public Single<MealsItemResponse>searchByName(@Query("s") String mealName);
}
