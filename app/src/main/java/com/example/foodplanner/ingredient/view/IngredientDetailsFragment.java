package com.example.foodplanner.ingredient.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.ingredient.presenter.IngredientDetailsPresenterImp;
import com.example.foodplanner.ingredient.presenter.IngredientDetailsPresenterView;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.IngredientsItem;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class IngredientDetailsFragment extends Fragment implements IngredientDetailsView, OnIngredientDetailsClickListener {
    private Context context;
    private IngredientDetailsAdapter ingredientDetailsAdapter;
    private RecyclerView recyclerView;
    Single<ListsDetailsResponse> ingredientDetailsList;
    private IngredientDetailsPresenterView ingredientDetailsPresenterView;
    private LinearLayoutManager linearLayoutManager;
    CardView randomCardView;
    IngredientsItem ingredientsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredient_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.ingredientDetailsRecyclerView);
        ingredientDetailsAdapter = new IngredientDetailsAdapter(requireActivity(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ingredientDetailsPresenterView = new IngredientDetailsPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity()))) {
        };

        ingredientsItem = (IngredientsItem) getArguments().getSerializable("ingredient");
        Toast.makeText(requireActivity(), "strmeal"+ingredientsItem.getStrIngredient(), Toast.LENGTH_SHORT).show();

        if (ingredientsItem != null) {
            ingredientDetailsList = ingredientDetailsPresenterView.getIngredientDetail(ingredientsItem.getStrIngredient());
            ingredientDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                ingredientDetailsAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(ingredientDetailsAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showIngredientDetail: unable to show meal ingredient because: " + throwable.getMessage());
                            });
        }
    }

    @Override
    public void showIngredientDetailsData(List<ListsDetails> ingredientDetailsList) {

    }

    @Override
    public void showIngredientDetailsErrorMsg(String error) {

    }

    @Override
    public void onIngredientClick(ListsDetails ingredientDetails) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("ingredientDetails", ingredientDetails);
        Navigation.findNavController(requireView()).navigate(R.id.action_ingredientDetailsFragment_to_listDetailFragment, bundle);
    }
}