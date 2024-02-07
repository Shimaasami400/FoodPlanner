package com.example.foodplanner.searchbar.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.example.foodplanner.searchbar.presenter.SearchByNamePresenterImp;
import com.example.foodplanner.searchbar.presenter.SearchByNamePresenterView;
import com.example.foodplanner.searchbar.view.OnSearchByNameClickListener;
import com.example.foodplanner.searchbar.view.SearchByNameAdapter;
import com.example.foodplanner.searchbar.view.SearchByNameView;
import com.example.foodplanner.model.MealRepositoryImpl;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByNameFragment extends Fragment implements SearchByNameView, OnSearchByNameClickListener {
    private Context context;
    private SearchByNameAdapter searchByNameAdapter;
    private RecyclerView recyclerView;
    private Single<List<MealsItem>> searchByNameList;
    private SearchByNamePresenterView searchByNamePresenterView;
    private CardView searchByNameCardView;
    private EditText editText;
    private List<MealsItem> originalList;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_by_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.searchByNameResultsRecyclerView);
        editText = view.findViewById(R.id.txtSearchEdit);

        searchByNameAdapter = new SearchByNameAdapter(requireActivity(), new ArrayList<>(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchByNameAdapter);

        searchByNamePresenterView = new SearchByNamePresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        // Fetch the original list of meals
        searchByNamePresenterView.getMealName("")
                .subscribe(
                        response -> {
                            List<MealsItem> items = response.getRandomMealList(); // Extract the list of meals
                            originalList = items;
                            searchByNameAdapter.setList(originalList);
                            searchByNameAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Log.e("SearchByNameFragment", "Error fetching meal list: " + throwable.getMessage());
                        }
                );


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim(); // Get the search text

                if (searchText.isEmpty()) {
                    // If the search text is empty, show the original list
                    searchByNameAdapter.setList(originalList);
                } else {
                    // Filter the original list based on the search text
                    List<MealsItem> filteredList = new ArrayList<>();
                    for (MealsItem item : originalList) {
                        if (item.getStrMeal().toLowerCase().contains(searchText.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    // Update the adapter with the filtered list
                    searchByNameAdapter.setList(filteredList);
                }
                // Notify the adapter of the data change
                searchByNameAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
    }

    @Override
    public void showSearchByNameData(List<MealsItem> searchByNameList) {
        // Update UI with search results
    }

    @Override
    public void showSearchByNameErrorMsg(String error) {
        // Show error message
    }

    public void onSearchByNameClick(MealsItem item) {
        // Handle click on search item
    }
}
