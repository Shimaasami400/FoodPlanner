package com.example.foodplanner.model.network;

import com.example.foodplanner.model.dto.CategoriesItemResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource{

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealService mealService;
    private static Retrofit retrofit = null;

    private static MealRemoteDataSourceImpl mealRemoteDataSource = null;

    public MealRemoteDataSourceImpl (){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()  // Use the class-level retrofit variable
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataSourceImpl getInstance() {
        if (mealRemoteDataSource == null)
            mealRemoteDataSource = new MealRemoteDataSourceImpl();

        return mealRemoteDataSource;
    }

    @Override
    public void RandomMealNetworkCall(RandomMealCallback networkCallback) {
        Call<MealsItemResponse> call = mealService.getRandomMeal();
        call.enqueue(new Callback<MealsItemResponse>() {
            @Override
            public void onResponse(Call<MealsItemResponse> call, Response<MealsItemResponse> response) {
                if (response.isSuccessful()) {
                    //4.calling the methods inside the interface
                    networkCallback.onSuccessResult(response.body().getRandomMealList());
                }
            }

            @Override
            public void onFailure(Call<MealsItemResponse> call, Throwable t) {
                //4
                networkCallback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }

    public void CategoryNetworkCall(CategoryCallBack categoryCallBack){
        Call<CategoriesItemResponse> call = mealService.getCategory();
        call.enqueue(new Callback<CategoriesItemResponse>() {
            @Override
            public void onResponse(Call<CategoriesItemResponse> call, Response<CategoriesItemResponse> response) {
                if (response.isSuccessful()) {
                    //4.calling the methods inside the interface
                    categoryCallBack.onSuccessResult(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoriesItemResponse> call, Throwable t) {
                //4
                categoryCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }
}