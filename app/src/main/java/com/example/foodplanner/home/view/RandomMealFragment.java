package com.example.foodplanner.home.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.home.presenter.CategoryMealPresenterImp;
import com.example.foodplanner.home.presenter.CategoryMealPresenterView;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.R;
import com.example.foodplanner.home.presenter.RandomMealPresenterImp;
import com.example.foodplanner.home.presenter.RandomMealPresenterView;
import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;


public class RandomMealFragment extends Fragment implements RandomMealView ,CategoryMealView,OnCategoryClickListener{
    RandomMealPresenterView randomMealPresenterView;
    private Context context;
    private MealCategoryAdapter categoryAdapter;
    private RecyclerView  recyclerView;
    List <MealsItem>mealsItemList;
    private ImageView image;
    private TextView title;
    private RandomMealPresenterView presenterView;
    private CategoryMealPresenterView categoryMealPresenterView;
    private  LinearLayoutManager linearLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_meal, container, false);
        image = view.findViewById(R.id.RandomImage);
        title = view.findViewById(R.id.tvRandom);
        presenterView = new RandomMealPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance()));
        presenterView.getMeal();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);

        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        categoryAdapter = new MealCategoryAdapter(requireActivity(),new ArrayList<>(),this);
        recyclerView.setAdapter(categoryAdapter);
        categoryMealPresenterView = new CategoryMealPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance()));
        categoryMealPresenterView.getCategory();
    }

    @Override
    public void showData(List<MealsItem> mealsItemList) {
        MealsItem item = mealsItemList.get(0);
        title.setText(item.getStrMeal());
        Glide.with(requireContext()).load(item.getStrMealThumb()).into(image);
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void showCategoryData(List<CategoriesItem> categoriesItemList) {
        categoryAdapter.setList(categoriesItemList);
        categoryAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(),"Success"+categoriesItemList.size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgCategory(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }
}