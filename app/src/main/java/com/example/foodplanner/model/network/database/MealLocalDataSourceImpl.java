package com.example.foodplanner.model.network.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource{
    private MealDAO mealDAO;
    private static MealLocalDataSourceImpl mealLocalDataSource = null;
    private LiveData<List<MealsItem>>storedFavoriteMeals;
    private MealLocalDataSourceImpl(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealsDAO();
        storedFavoriteMeals = mealDAO.getAllFavoriteMeals();
    }
    public static MealLocalDataSourceImpl getInstance(Context context){
        if(mealLocalDataSource == null)
            mealLocalDataSource = new MealLocalDataSourceImpl(context);

        return mealLocalDataSource;
    }

    @Override
    public void insertMealToFavorite(MealsItem mealsItem) {
        new Thread(()->{
            mealDAO.insertMealToFavorite(mealsItem);
            Log.i("TAG", "inserting: Adding Item ");
        }).start();
    }

    @Override
    public void deleteMealFromFavorite(MealsItem mealsItem) {
        new Thread(()->{
            mealDAO.deleteMealFromFavorite(mealsItem);
        }).start();
    }

    @Override
    public LiveData<List<MealsItem>> getAllFavoriteStoredMeals() {
        return storedFavoriteMeals;
    }
}