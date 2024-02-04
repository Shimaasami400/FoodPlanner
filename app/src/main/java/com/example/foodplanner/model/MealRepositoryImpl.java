package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.AreaMealCallback;
import com.example.foodplanner.model.network.network.CategoryCallBack;
import com.example.foodplanner.model.network.network.IngredientsCallback;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.network.RandomMealCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealRepositoryImpl implements MealRepositoryView{
    MealRemoteDataSourceImpl mealRemoteDataSource;
    MealLocalDataSourceImpl mealLocalDataSource;
    static MealRepositoryImpl mealRepository;

    public MealRepositoryImpl(MealRemoteDataSourceImpl mealRemoteDataSource,MealLocalDataSourceImpl mealLocalDataSource){
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
    }

    public static MealRepositoryImpl getInstance(MealRemoteDataSourceImpl mealRemoteDataSource,MealLocalDataSourceImpl mealLocalDataSource){
        if(mealRepository == null)
            mealRepository = new MealRepositoryImpl(mealRemoteDataSource,mealLocalDataSource);

        return  mealRepository;
    }

    @Override
    public void RandomMealNetworkCall(RandomMealCallback networkCallback) {
        mealRemoteDataSource.RandomMealNetworkCall(networkCallback);
    }

    @Override
    public void CategoryNetworkCall(CategoryCallBack categoryCallBack) {
        mealRemoteDataSource.CategoryNetworkCall(categoryCallBack);
    }

    @Override
    public void IngredientsNetworkCall(IngredientsCallback ingredientsCallback) {
        mealRemoteDataSource.IngredientsNetworkCall(ingredientsCallback);
    }

    @Override
    public void AreasNetworkCall(AreaMealCallback areaMealCallback) {
        mealRemoteDataSource.AreasNetworkCall(areaMealCallback);
    }

    @Override
    public Single<List<MealsItem>> getFavoriteMeals() {
        return mealLocalDataSource.getAllFavoriteStoredMeals();
    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {
        mealLocalDataSource.deleteMealFromFavorite(mealsItem);
    }

    @Override
    public void insertMeal(MealsItem mealsItem) {
        mealLocalDataSource.insertMealToFavorite(mealsItem);
    }

    @Override
    public Single<List<MealsItem>> getFavoriteMealsSingle() {
        return mealLocalDataSource.getAllFavoriteStoredMeals();
    }
}
