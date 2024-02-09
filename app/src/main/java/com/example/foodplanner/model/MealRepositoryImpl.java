package com.example.foodplanner.model;

import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsDetail;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.MealsItemResponse;
import com.example.foodplanner.model.dto.WeekPlan;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.AreaMealCallback;
import com.example.foodplanner.model.network.network.CategoryCallBack;
import com.example.foodplanner.model.network.network.IngredientsCallback;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.network.RandomMealCallback;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

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



    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category) {
       return mealRemoteDataSource.CategoryDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category) {
        return mealRemoteDataSource.IngredientDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category) {
        return mealRemoteDataSource.AreaDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsItemResponse> SearchByNameNetworkCall(String name) {
        return mealRemoteDataSource.searchByNameNetworkCall(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsDetailResponse> getMealByIdNetworkCall(String name) {
        return mealRemoteDataSource.getMealDetailNetworkCall(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<MealsItem>> getFavoriteMeals() {
        return mealLocalDataSource.getAllFavoriteStoredMeals();
    }

    @Override
    public Single<List<MealsDetail>> getListMealDetails() {
        return mealLocalDataSource.getAllFavoriteStoredMealsDetail();
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
    public void deleteMeal(MealsDetail mealsItem) {
        mealLocalDataSource.deleteMealDetailFromFavorite(mealsItem);
    }

    @Override
    public void insertMeal(MealsDetail mealsItem) {
        mealLocalDataSource.insertMealDetailToFavorite(mealsItem);
    }

    @Override
    public Single<List<WeekPlan>> getWeekPlanMeals() {
        return mealLocalDataSource.getWeekPlanMeals();
    }

    @Override
    public Single<List<WeekPlan>> getMealsForDate(String date) {
        return mealLocalDataSource.getMealsForDate(date);
    }

    @Override
    public void deleteWeekPlanMeal(WeekPlan weekPlan) {
        mealLocalDataSource.deleteWeekPlanMealFromCalender(weekPlan);
    }

    @Override
    public void insertWeekPlanMeal(WeekPlan weekPlan) {
        mealLocalDataSource.insertWeekPlanMealToCalender(weekPlan);
    }

    @Override
    public Single<List<MealsItem>> getFavoriteMealsSingle() {
        return mealLocalDataSource.getAllFavoriteStoredMeals();
    }

}
