package com.example.foodplanner.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.favorite.presente.FavouriteMealPresenterImp;
import com.example.foodplanner.favorite.presente.FavouriteMealPresenterView;
import com.example.foodplanner.home.presenter.CategoryMealPresenterImp;
import com.example.foodplanner.home.view.MealCategoryAdapter;
import com.example.foodplanner.mealdetail.view.OnDetailItemClickListener;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements FavouriteMealView, OnFavoriteMealClickListener {

    private FavouriteMealPresenterView favouriteMealPresenterView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FavMealsAdapter favMealsAdapter;
    private LiveData<List<MealsItem>> favMealsList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        //linearLayoutManager = new LinearLayoutManager(requireActivity());
       // linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        favMealsAdapter = new FavMealsAdapter(requireActivity(),new ArrayList<>(),this);
        favouriteMealPresenterView = new FavouriteMealPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        recyclerView.setAdapter(favMealsAdapter);
        favMealsList = favouriteMealPresenterView.getFavMealList();
        favMealsList.observe(requireActivity(), new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> mealsItemList) {
                favMealsAdapter.setMealFavList(mealsItemList);
                favMealsAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void showFavListData(List<MealsItem> mealsItemList) {
        favMealsAdapter.setMealFavList(mealsItemList);
        favMealsAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(),"Success: "+mealsItemList.size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFavListErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickItem(String id) {

    }

    @Override
    public void onClick(MealsItem mealsItem) {

    }

    @Override
    public void onClickDeleteItem(MealsItem mealsItem) {

    }
}