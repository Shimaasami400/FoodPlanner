package com.example.foodplanner.search.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements IngredientsView,AreasView,OnIngredientsClickListener,OnAreaClickListener{
    private Context context;
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView areasRecyclerView;
    private EditText searchByName;
    private List <IngredientsItem>ingredientsItemList;
    private List <AreaItem>areaItemList;
    private ImageView image;
    private TextView mealName;
    private IngredientsPresenterView ingredientsPresenterView;
    private AreasPresenterView areasPresenterView;
    private LinearLayoutManager ingredientsLinearLayoutManager;
    private LinearLayoutManager areasLinearLayoutManager;
    private IngredientSearchAdapter ingredientSearchAdapter;
    private AreaSearchAdapter areaSearchAdapter;
    private CardView  ingredientCardView;
    private LottieAnimationView lottieAnimationView;
    private NestedScrollView nestedScrollView;


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
        nestedScrollView = view.findViewById(R.id.searchnested);
        lottieAnimationView = view.findViewById(R.id.searchAnmi);

        areasPresenterView = new AreasPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(),MealLocalDataSourceImpl.getInstance(requireActivity())));
        areasPresenterView.getArea();

        if (!isNetworkAvailable()) {
            nestedScrollView.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }else {
            nestedScrollView.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchByName = view.findViewById(R.id.txtSearch);
        searchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_searchFragment2_to_searchByNameFragment);
            }
        });
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
            } else {
                Toast.makeText(requireActivity(), "Ingredients list is null", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void showIngredientsErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAreasData(List<AreaItem> AreaItemList) {
        if (AreaItemList != null) {
            areaSearchAdapter.setList(AreaItemList);
            areaSearchAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(requireActivity(), "Ingredients list is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showAreasErrorMsg(String error) {

    }

    @Override
    public void onIngredientClick(IngredientsItem ingredientsItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("ingredient", (Serializable) ingredientsItem);
        Toast.makeText(requireActivity(), "ingredient"+ingredientsItem, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment2_to_ingredientDetailsFragment, bundle);
    }

    @Override
    public void onAreaClick(AreaItem areaItem) {
        Bundle areaBundle = new Bundle();
        areaBundle.putSerializable("area", (Serializable) areaItem);
        Toast.makeText(requireActivity(), "ingredient"+areaItem, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment2_to_areaDetailsFragment, areaBundle);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }
}