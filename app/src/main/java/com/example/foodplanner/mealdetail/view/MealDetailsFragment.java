package com.example.foodplanner.mealdetail.view;

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
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterImp;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterView;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailView ,OnDetailItemClickListener{
    private ImageView itemImage;
    private TextView tvItemName;
    private TextView tvItemCountry;
    private TextView tvItemCategory;
    private ImageView addToFavImage;
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
    private MealRepositoryView mealRepositoryView;
    private OnDetailItemClickListener onDetailItemClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addToFavImage = view.findViewById(R.id.imageViewAddToFavITemDetails);
        youTubePlayerView = view.findViewById(R.id.ytPlayer);


        mealsItem = (MealsItem) getArguments().getSerializable("item");

        // Check if mealsItem is not null
        if (mealsItem != null) {
            // Load item details
            showItemDetailData(mealsItem);

            // Load video from YouTube when the user interacts with it
            youTubePlayerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String videoUrl = mealsItem.getStrYoutube();
                    if (videoUrl != null && !videoUrl.isEmpty()) {
                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                // Load video when player is ready.
                                youTubePlayer.loadVideo(getVideoId(videoUrl), 0);
                            }

                            private String getVideoId(String videoUrl) {
                                String videoId = null;
                                String[] urlParts = videoUrl.split("v=");
                                if (urlParts.length > 1) {
                                    videoId = urlParts[1];
                                }
                                return videoId;
                            }
                        });
                    }
                }
            });
        }
        addToFavImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   // onDetailItemClickListener.onItemClickListener(mealsItem);
                mealDetailPresenterView.addToFav(mealsItem);
                addToFavImage.setImageResource(R.drawable.fullheart);
            }
        });
        // Update UI with item details
        showItemDetailData(mealsItem);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
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
        mealDetailPresenterView = new MealDetailPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
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
    public void addToFav(MealsItem mealsItem) {
        mealRepositoryView.insertMeal(mealsItem);
    }

    @Override
    public void showItemDetailErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickListener(MealsItem mealsItem) {
        this.mealsItem = mealsItem;
        Log.i("TAG", "meal item from onitemclick listener: "+mealsItem);
        mealDetailPresenterView.addToFav(mealsItem);
        Toast.makeText(requireActivity(), "Item added to favorites", Toast.LENGTH_SHORT).show();
        mealRepositoryView.getFavoriteMealsSingle().getClass();
    }
}