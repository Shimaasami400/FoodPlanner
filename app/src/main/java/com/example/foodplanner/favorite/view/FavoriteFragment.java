package com.example.foodplanner.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.AppDataBase;
import com.example.foodplanner.model.network.database.MealDAO;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.favorite.presente.FavouriteMealPresenterImp;
import com.example.foodplanner.favorite.presente.FavouriteMealPresenterView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteFragment extends Fragment implements FavouriteMealView, OnFavoriteMealClickListener {

    private FavouriteMealPresenterView favouriteMealPresenterView;
    private RecyclerView recyclerView;
    private FavMealsAdapter favMealsAdapter;
    private Single<List<MealsItem>> favMealsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.favRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        favMealsAdapter = new FavMealsAdapter(requireActivity(), new ArrayList<>(), this, favouriteMealPresenterView);
        recyclerView.setAdapter(favMealsAdapter);

        favouriteMealPresenterView = new FavouriteMealPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        showList();
            }
    @Override
    public void onDeleteItemClick(MealsItem mealsItem) {
        favouriteMealPresenterView.deleteMeal(mealsItem);
        Toast.makeText(requireActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList() {
        favMealsList = favouriteMealPresenterView.getFavMealList();
        favMealsList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsItemList -> {
                    //setFavMealsList(mealsItemList);
                    favMealsAdapter.setMealFavList(mealsItemList);
                    favMealsAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.i("TAG", "Unable to show Meal because: "+throwable.getMessage());
                });
    }
}
