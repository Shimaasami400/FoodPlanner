package com.example.foodplanner.home.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RandomMealView ,CategoryMealView,OnCategoryClickListener{
    private Context context;
    private MealCategoryAdapter categoryAdapter;
    private RecyclerView  recyclerView;
    List <MealsItem>mealsItemList;
    private ImageView image;
    private TextView mealName;
    private TextView mealCountry;
    private RandomMealPresenterView presenterView;
    private CategoryMealPresenterView categoryMealPresenterView;
    private  LinearLayoutManager linearLayoutManager;

    CardView randomCardView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_meal, container, false);
        image = view.findViewById(R.id.RandomImage);
        mealName = view.findViewById(R.id.tvRandom);
        mealCountry = view.findViewById(R.id.tvCountry);
        presenterView = new RandomMealPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        presenterView.getMeal();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        randomCardView = view.findViewById(R.id.cardView);

        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        categoryAdapter = new MealCategoryAdapter(requireActivity(),new ArrayList<>(),this);
        recyclerView.setAdapter(categoryAdapter);
        categoryMealPresenterView = new CategoryMealPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(),MealLocalDataSourceImpl.getInstance(requireActivity())));
        categoryAdapter.setCategoryClickListener(this);
        categoryMealPresenterView.getCategory();


    }

    @Override
    public void showData(List<MealsItem> mealsItemList) {
        MealsItem item = mealsItemList.get(0);
        mealName.setText(item.getStrMeal());
        mealCountry.setText(item.getStrArea());
        Glide.with(requireContext()).load(item.getStrMealThumb()).into(image);
        randomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                Navigation.findNavController(v).navigate(R.id.action_randomMealFragment_to_mealDetailsFragment, bundle);
            }
        });
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void showCategoryData(List<CategoriesItem> categoriesItemList) {
        categoryAdapter.setList(categoriesItemList);
        categoryAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(),"Success: "+categoriesItemList.size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgCategory(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCategoryClick(CategoriesItem category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) category);
        Toast.makeText(requireActivity(), "category"+category, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_randomMealFragment_to_categoryDetailsFragment, bundle);
    }
}