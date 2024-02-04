package com.example.foodplanner.model.network.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.dto.MealsItem;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals")
    LiveData<List<MealsItem>> getAllFavoriteMeals();//get the favList.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToFavorite(MealsItem mealsItem);
    @Delete
    void deleteMealFromFavorite(MealsItem mealsItem);
}
