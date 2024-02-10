package com.example.foodplanner.category.view;

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

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.category.presenter.CategoryDetailsPresenterImp;
import com.example.foodplanner.category.presenter.CategoryDetailsPresenterView;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.CategoriesItem;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.ListsDetailsResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CategoryDetailsFragment extends Fragment implements CategoryDetailsView ,OnCategoryDetailsClickListener{
    private Context context;
    private CategoryDetailsAdapter categoryDetailsAdapter;
    private RecyclerView recyclerView;
    Single<ListsDetailsResponse> categoryDetailsList;
    private CategoryDetailsPresenterView categoryDetailsPresenterView;
    private LinearLayoutManager linearLayoutManager;
    CardView randomCardView;
     CategoriesItem category;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_category_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryDetailsRecyclerView);
        categoryDetailsAdapter = new CategoryDetailsAdapter(requireActivity(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        categoryDetailsPresenterView = new CategoryDetailsPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        category = (CategoriesItem) getArguments().getSerializable("category");
        Toast.makeText(requireActivity(), "strmeal"+category.getStrCategory(), Toast.LENGTH_SHORT).show();
        if (category != null) {
            categoryDetailsList = categoryDetailsPresenterView.getCategoryDetail(category.getStrCategory());
            Log.i("TAG", "s()categoryDetailsList " +categoryDetailsList);
            categoryDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                categoryDetailsAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(categoryDetailsAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showCategoryDetail: unable to show products because: " + throwable.getMessage());
                            });
        }


    }

    @Override
    public void showCategoryDetailsData(List<ListsDetails> categoryDetailsList) {
    }

    @Override
    public void showCategoryDetailsErrorMsg(String error) {

    }

    @Override
    public void onCategoryClick(ListsDetails listsDetails) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("categoryDetail", listsDetails);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryDetailsFragment_to_listDetailFragment, bundle);
    }
}