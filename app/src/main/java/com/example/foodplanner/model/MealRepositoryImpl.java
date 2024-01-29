package com.example.foodplanner.model;

import com.example.foodplanner.model.network.CategoryCallBack;
import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.model.network.RandomMealCallback;

public class MealRepositoryImpl implements MealRepositoryView{
    MealRemoteDataSourceImpl mealRemoteDataSource;
    static MealRepositoryImpl mealRepository;

    public MealRepositoryImpl(MealRemoteDataSourceImpl mealRemoteDataSource){
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public static MealRepositoryImpl getInstance(MealRemoteDataSourceImpl mealRemoteDataSource){
        if(mealRepository == null)
            mealRepository = new MealRepositoryImpl(mealRemoteDataSource);

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


}
