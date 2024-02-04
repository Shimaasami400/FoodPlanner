package com.example.foodplanner.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        favMealsAdapter = new FavMealsAdapter(requireActivity(), new ArrayList<>(), this);
        recyclerView.setAdapter(favMealsAdapter);

        favouriteMealPresenterView = new FavouriteMealPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        AppDataBase db = AppDataBase.getInstance(requireActivity());
        MealDAO dao = db.getMealsDAO();
        favMealsList = dao.getAllFavoriteMeals();

        favMealsList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsItemList -> {
                    setFavMealsList(mealsItemList);
                }, throwable -> {
                    // Handle error
                });
    }

    private void setFavMealsList(List<MealsItem> mealsItemList) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                favMealsAdapter.setMealFavList(mealsItemList);
            }
        });
    }

    @Override
    public void showFavListData(List<MealsItem> mealsItemList) {
        // Not needed as we are directly setting the list in setFavMealsList method
    }

    @Override
    public void showFavListErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickItem(String id) {
        // Handle item click if needed
    }

    @Override
    public void onClick(MealsItem mealsItem) {
        // Handle item click if needed
    }

    @Override
    public void onClickDeleteItem(MealsItem mealsItem) {
        // Handle delete item click if needed
    }
}
