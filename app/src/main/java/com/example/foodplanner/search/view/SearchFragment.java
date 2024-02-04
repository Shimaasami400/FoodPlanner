package com.example.foodplanner.search.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.AreaItem;
import com.example.foodplanner.model.dto.IngredientsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.search.presenter.AreasPresenterImp;
import com.example.foodplanner.search.presenter.AreasPresenterView;
import com.example.foodplanner.search.presenter.IngredientsPresenterImp;
import com.example.foodplanner.search.presenter.IngredientsPresenterView;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements IngredientsView,AreasView,OnIngredientsClickListener,OnAreaClickListener{
    private Context context;
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView areasRecyclerView;
    List <IngredientsItem>ingredientsItemList;
    List <AreaItem>areaItemList;
    private ImageView image;
    private TextView mealName;
    private IngredientsPresenterView ingredientsPresenterView;
    private AreasPresenterView areasPresenterView;
    private LinearLayoutManager ingredientsLinearLayoutManager;
    private LinearLayoutManager areasLinearLayoutManager;

    private IngredientSearchAdapter ingredientSearchAdapter;
    private AreaSearchAdapter areaSearchAdapter;
    CardView randomCardView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ingredientsPresenterView = new IngredientsPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
        ingredientsPresenterView.getIngredient();

        areasPresenterView = new AreasPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(),MealLocalDataSourceImpl.getInstance(requireActivity())));
        areasPresenterView.getArea();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        ingredientsLinearLayoutManager = new LinearLayoutManager(requireActivity());
        ingredientsLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ingredientsRecyclerView.setLayoutManager(ingredientsLinearLayoutManager);

        areasRecyclerView = view.findViewById(R.id.countryRecyclerView);
        areasLinearLayoutManager = new LinearLayoutManager(requireActivity());
        areasLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areasRecyclerView.setLayoutManager(areasLinearLayoutManager);

        ingredientSearchAdapter = new IngredientSearchAdapter(requireActivity(),new ArrayList<>(),this);
        ingredientsRecyclerView.setAdapter(ingredientSearchAdapter);
        ingredientsPresenterView = new IngredientsPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(),MealLocalDataSourceImpl.getInstance(requireActivity())));
        ingredientsPresenterView.getIngredient();

        /*areasRecyclerView = view.findViewById(R.id.countryRecyclerView);
        areasLinearLayoutManager = new LinearLayoutManager(requireActivity());
        areasLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areasRecyclerView.setLayoutManager(areasLinearLayoutManager);*/

        areaSearchAdapter = new AreaSearchAdapter(requireActivity(),new ArrayList<>(),this);
        areasRecyclerView.setAdapter(areaSearchAdapter);
        areasPresenterView = new AreasPresenterImp(this,MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(),MealLocalDataSourceImpl.getInstance(requireActivity())));
        areasPresenterView.getArea();


    }

    @Override
    public void showIngredientsData(List<IngredientsItem> IngredientsItemList) {
            if (IngredientsItemList != null) {
                ingredientSearchAdapter.setList(IngredientsItemList);
                ingredientSearchAdapter.notifyDataSetChanged();
                Toast.makeText(requireActivity(), "Success: " + IngredientsItemList.size(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireActivity(), "Ingredients list is null", Toast.LENGTH_SHORT).show();
            }


       /* ingredientSearchAdapter.setList(IngredientsItemList);
        ingredientSearchAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(),"Success: "+IngredientsItemList.size(),Toast.LENGTH_SHORT).show();*/

    }

    @Override
    public void showIngredientsErrorMsg(String error) {

    }

    @Override
    public void showAreasData(List<AreaItem> AreaItemList) {
        if (AreaItemList != null) {
            areaSearchAdapter.setList(AreaItemList);
            areaSearchAdapter.notifyDataSetChanged();
            Toast.makeText(requireActivity(), "Success: " + AreaItemList.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireActivity(), "Ingredients list is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showAreasErrorMsg(String error) {

    }
}