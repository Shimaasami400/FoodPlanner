package com.example.foodplanner.model.network.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.dto.MealsItem;


@Database(entities = {MealsItem.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MealDAO getMealsDAO();
    private static AppDataBase instance = null;
    private MealDAO mealDAO;

    public static synchronized AppDataBase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"meals.db").build();

        return instance;
    }
}
