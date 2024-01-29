package com.example.foodplanner.model.dto;

import java.util.List;

import com.example.foodplanner.model.dto.MealsItem;
import com.google.gson.annotations.SerializedName;

public class MealsItemResponse {

	@SerializedName("meals")
	private List<MealsItem> meals;

	public List<MealsItem> getRandomMealList(){
		return meals;
	}
}