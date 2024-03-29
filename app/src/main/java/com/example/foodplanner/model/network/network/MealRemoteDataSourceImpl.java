package com.example.foodplanner.model.network.network;

import com.example.foodplanner.model.dto.AreaItemResponse;
import com.example.foodplanner.model.dto.CategoriesItemResponse;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.IngredientsItemResponse;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItemResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    @Override
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback) {
        Call<IngredientsItemResponse> call = mealService.getIngredients();
        call.enqueue(new Callback<IngredientsItemResponse>() {
            @Override
            public void onResponse(Call<IngredientsItemResponse> call, Response<IngredientsItemResponse> response) {
                if (response.isSuccessful()) {
                    //4.calling the methods inside the interface
                    ingredientsCallback.onSuccessResult(response.body().getIngredientList());
                    System.out.println("response.body().getIngredientList()" +response.body().getIngredientList());
                }
            }

            @Override
            public void onFailure(Call<IngredientsItemResponse> call, Throwable t) {
                //4
                ingredientsCallback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }

    @Override
    public void AreasNetworkCall(AreaMealCallback areaMealCallback) {
        Call<AreaItemResponse> call = mealService.getAreas();
        call.enqueue(new Callback<AreaItemResponse>() {
            @Override
            public void onResponse(Call<AreaItemResponse> call, Response<AreaItemResponse> response) {
                if (response.isSuccessful()) {
                    //4.calling the methods inside the interface
                    areaMealCallback.onSuccessResult(response.body().getAreasList());
                    System.out.println("response.body().getIngredientList()" +response.body().getAreasList());
                }
            }

            @Override
            public void onFailure(Call<AreaItemResponse> call, Throwable t) {
                //4
                areaMealCallback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }
    @Override
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category) {
        return mealService.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category) {
        return mealService.getMealsByIngredient(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category) {
        return mealService.getMealsByArea(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsItemResponse> searchByNameNetworkCall(String name) {
        return mealService.searchByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsDetailResponse> getMealDetailNetworkCall(String name) {
        return mealService.getMealById(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

