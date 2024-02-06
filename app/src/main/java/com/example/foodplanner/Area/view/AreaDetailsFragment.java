package com.example.foodplanner.Area.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.Area.presenter.AreaDetailsPresenterImp;
import com.example.foodplanner.Area.presenter.AreaDetailsPresenterView;
import com.example.foodplanner.R;
import com.example.foodplanner.ingredient.presenter.IngredientDetailsPresenterImp;
import com.example.foodplanner.ingredient.presenter.IngredientDetailsPresenterView;
import com.example.foodplanner.ingredient.view.IngredientDetailsAdapter;
import com.example.foodplanner.ingredient.view.IngredientDetailsView;
import com.example.foodplanner.ingredient.view.OnIngredientDetailsClickListener;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.AreaItem;
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


public class AreaDetailsFragment extends Fragment implements AreaDetailsView, OnAreaDetailsClickListener {
    private Context context;
    private AreaDetailsAdapter areaDetailsAdapter;
    private RecyclerView recyclerView;
    Single<ListsDetailsResponse> areaDetailsList;
    private AreaDetailsPresenterView areaDetailsPresenterView;
    private LinearLayoutManager linearLayoutManager;
    CardView areaCardView;
   AreaItem areaItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.areaDetailsRecyclerView);
        areaDetailsAdapter = new AreaDetailsAdapter(requireActivity(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        areaDetailsPresenterView = new AreaDetailsPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity()))) {
        };

        areaItem = (AreaItem) getArguments().getSerializable("area");
        Toast.makeText(requireActivity(), "strmeal"+areaItem.getStrArea(), Toast.LENGTH_SHORT).show();

        if (areaItem != null) {
            areaDetailsList = areaDetailsPresenterView.getAreaDetail(areaItem.getStrArea());
            areaDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                areaDetailsAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(areaDetailsAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showIngredientDetail: unable to show meal ingredient because: " + throwable.getMessage());
                            });
        }
    }

    @Override
    public void showAreaDetailsData(List<ListsDetails> categoryDetailsList) {

    }

    @Override
    public void showAreaDetailsErrorMsg(String error) {

    }

    @Override
    public void onAreaClick(String category) {

    }
}