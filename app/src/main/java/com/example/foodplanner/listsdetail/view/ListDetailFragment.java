package com.example.foodplanner.listsdetail.view;

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
import com.example.foodplanner.listsdetail.presenter.ListDetailPresenterImp;
import com.example.foodplanner.listsdetail.presenter.ListDetailPresenterView;
import com.example.foodplanner.mealdetail.presenter.MealDetailPresenterView;
import com.example.foodplanner.mealdetail.view.IngridentsAdapter;
import com.example.foodplanner.mealdetail.view.MealDetailView;
import com.example.foodplanner.mealdetail.view.OnDetailItemClickListener;
import com.example.foodplanner.model.MealRepositoryImpl;
import com.example.foodplanner.model.MealRepositoryView;
import com.example.foodplanner.model.dto.GeneratingIngridentsArrayLists;
import com.example.foodplanner.model.dto.GeneratingListIngridentsArrayLists;
import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.MealsDetail;
import com.example.foodplanner.model.dto.MealsDetailResponse;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.network.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.network.network.MealRemoteDataSourceImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListDetailFragment extends Fragment implements ListDetailView, OnMealDetailClickListener {

    private ImageView itemImage;
    private TextView tvItemName;
    private TextView tvItemCountry;
    private TextView tvItemCategory;
    private TextView tvProcedures;
    private ImageView addToFavImage;
    private ListDetailPresenterView listDetailPresenterView ;
    private ListsDetails listsDetails;
    private ListsDetails listAreaDetails;
    private ListsDetails listingredientDetails;
    private MealsItem searchByName;
    private MealsItem favMeal;
    private Context context;
    private ListDetailAdapter listDetailAdapter;
    private RecyclerView recyclerView;
    private Single <MealsDetailResponse> mealsDetailList;
    private ImageView itemingredientImage;
    private TextView itemingredientName;
    private LinearLayoutManager linearLayoutManager;
    private CardView ingridentCardView;
    private YouTubePlayerView youTubePlayerView;
    private MealRepositoryView mealRepositoryView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvItemName = view.findViewById(R.id.tviewMealNameItem);
        tvItemCountry = view.findViewById(R.id.tvMealCountryDetails);
        tvItemCategory = view.findViewById(R.id.tvMealCategoryDetails);
        tvProcedures = view.findViewById(R.id.tvProcedure);
        itemImage = view.findViewById(R.id.mealDetailImage);
        addToFavImage = view.findViewById(R.id.FavItemImage);
        youTubePlayerView = view.findViewById(R.id.ytPlayerVideo);
        recyclerView = view.findViewById(R.id.rviewIngredients);

        linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listDetailAdapter = new ListDetailAdapter(requireContext());
        recyclerView.setAdapter(listDetailAdapter);



        listDetailPresenterView = new ListDetailPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        listsDetails = (ListsDetails) getArguments().getSerializable("categoryDetail");
        listAreaDetails = (ListsDetails) getArguments().getSerializable("areaDetails");
        listingredientDetails =(ListsDetails) getArguments().getSerializable("ingredientDetails");
        searchByName = (MealsItem) getArguments().getSerializable("SearchByName");
        favMeal = (MealsItem) getArguments().getSerializable("Favorite");


        Single<MealsDetailResponse> mealsDetailSingle = listDetailPresenterView.getMealDetail(listsDetails != null ? listsDetails.getIdMeal() :
                listAreaDetails != null ? listAreaDetails.getIdMeal() :
                        listingredientDetails != null ? listingredientDetails.getIdMeal() : searchByName != null ? searchByName.getIdMeal() :favMeal != null ? favMeal.getIdMeal() :"");
        Log.i("TAG", "mealsDetailSingle = " + mealsDetailSingle);

        mealsDetailSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsDetailResponse -> {
                    MealsDetail mealsDetail = mealsDetailResponse.getMeals().get(0);
                    Log.i("TAG", "mealsDetailResponse.getMeals().get(0)=" + mealsDetail);
                    tvItemName.setText(mealsDetail.getStrMeal());
                    tvItemCountry.setText(mealsDetail.getStrArea());
                    tvItemCategory.setText(mealsDetail.getStrCategory());
                    tvProcedures.setText(mealsDetail.getStrInstructions());
                    Glide.with(requireActivity()).load(mealsDetail.getStrMealThumb()).into(itemImage);
                    listDetailAdapter.setMealItemDetailList(GeneratingListIngridentsArrayLists.getIngridentsListArray(mealsDetail));

                    addToFavImage.setOnClickListener(v -> {
                        listDetailPresenterView.addToFav(mealsDetail);
                    });

                    String youtubeVideoId = mealsDetail.getStrYoutube();
                    if (youtubeVideoId != null && !youtubeVideoId.isEmpty()) {
                        youTubePlayerView.setVisibility(View.VISIBLE);
                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                super.onReady(youTubePlayer);
                                loadVideo(youTubePlayer, youtubeVideoId);
                            }
                        });
                    } else {
                        // Hide YouTube player if there's no video URL
                        youTubePlayerView.setVisibility(View.GONE);
                    }
                }, throwable -> {
                    Log.e("TAG", "Error fetching meal details: " + throwable.getMessage());
                    Toast.makeText(requireContext(), "Error fetching meal details", Toast.LENGTH_SHORT).show();
                });


    }

    @Override
    public void showMealDetailData(List<MealsDetail> mealsDetailList) {
      /*  MealsDetail mealsDetail = mealsDetailList.get(0);
        tvItemName.setText(mealsDetail.getStrMeal());
        tvItemCountry.setText(mealsDetail.getStrArea());
        tvItemCategory.setText(mealsDetail.getStrCategory());
        Glide.with(requireActivity()).load(mealsDetail.getStrMealThumb()).into(itemImage);
        Log.i("TAG", "showMealDetailData: "+mealsDetail.getStrArea());*/
    }

    @Override
    public void addMealToFav(MealsDetail mealsDetail) {
        mealRepositoryView.insertMeal(mealsDetail);
    }

    @Override
    public void showMealDetailErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealDetailClickListener(MealsItem mealsItem) {

    }

    private void loadVideo(@NonNull YouTubePlayer youTubePlayer, String youtubeVideoId) {
        youTubePlayer.loadVideo(getVideoId(youtubeVideoId), 0);
    }

    private String getVideoId(String videoUrl) {
        String videoId = null;
        String[] urlParts = videoUrl.split("v=");
        if (urlParts.length > 1) {
            videoId = urlParts[1];
        }
        return videoId;
    }
}