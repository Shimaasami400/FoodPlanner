package com.example.foodplanner.mealdetail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.home.presenter.CategoryMealPresenterImp;
import com.example.foodplanner.home.presenter.RandomMealPresenterImp;
import com.example.foodplanner.home.presenter.RandomMealPresenterView;
import com.example.foodplanner.home.view.MealCategoryAdapter;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterImp;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterView;
import com.example.foodplanner.mealdetail.view.MealDetailView;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.MealRemoteDataSourceImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailView {

    private ImageView itemImage;
    private TextView tvItemName;
    private TextView tvItemCountry;
    private TextView tvItemCategory;
    private MealDetailPresenterView mealDetailPresenterView;
    private MealsItem mealsItem;

    private Context context;
    private IngridentsAdapter ingridentsAdapter;
    private RecyclerView recyclerView;
    private List <MealsItem>mealsItemList;
    private ImageView itemIngridentImage;
    private TextView itemIngridentName;
    private LinearLayoutManager linearLayoutManager;
    private CardView ingridentCardView;
    private YouTubePlayerView youTubePlayerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealDetailPresenterView = new MealDetailPresenterImp(this) ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        tvItemName = view.findViewById(R.id.txtViewMealNameItemDetails);
        tvItemCountry = view.findViewById(R.id.textViewMealCountryItemDetails);
        tvItemCategory = view.findViewById(R.id.textViewMealCateItemDetails);
        itemImage = view.findViewById(R.id.mealImage);
        youTubePlayerView = view.findViewById(R.id.ytPlayer);

        mealsItem = (MealsItem) getArguments().getSerializable("item");

        // Check if mealsItem is not null
        if (mealsItem != null) {
            // Load video from YouTube
            String videoUrl = mealsItem.getStrYoutube();
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    // Load video when player is ready.
                    youTubePlayer.loadVideo(getVideoId(videoUrl), 0);
                }

                private String getVideoId(String videoUrl) {
                    String videoId = null;
                    if (videoUrl != null && videoUrl.trim().length() > 0) {
                        String[] urlParts = videoUrl.split("v=");
                        if (urlParts.length > 1) {
                            videoId = urlParts[1];
                        }
                    }
                    return videoId;
                }
            });
        }

        // Update UI with item details
        showItemDetailData(mealsItem);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewIngredientsItemDetails);
        ingridentCardView = view.findViewById(R.id.cardView);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ingridentsAdapter = new IngridentsAdapter(requireActivity());
        recyclerView.setAdapter(ingridentsAdapter);
        mealDetailPresenterView = new MealDetailPresenterImp(this);
        // Assuming mealsItem has the list of ingredients
        /*List<MealsItem> ingredientsList = mealsItem.getIngredients();

        recyclerView = view.findViewById(R.id.recyclerViewIngredientsItemDetails);
        ingridentCardView = view.findViewById(R.id.cardView);
        linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ingridentsAdapter = new IngridentsAdapter(requireActivity());
        recyclerView.setAdapter(ingridentsAdapter);

        // Set the list of ingredients in the adapter
        ingridentsAdapter.setMealItemDetailList(ingredientsList);*/






    }

    @Override
    public void showItemDetailData(MealsItem mealsItem) {

        tvItemName.setText(mealsItem.getStrMeal());
        tvItemCountry.setText(mealsItem.getStrArea());
        tvItemCategory.setText(mealsItem.getStrCategory());
        Glide.with(requireActivity()).load(mealsItem.getStrMealThumb()).into(itemImage);
        Log.i("TAG", "showItemDetailData: "+mealsItem.getStrArea());


    }

    @Override
    public void showItemDetailErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
    }
}