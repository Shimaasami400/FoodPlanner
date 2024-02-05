package com.example.foodplanner.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryDetailsResponse {

	@SerializedName("meals")
	private List<CategoryDetails> meals;

	public List<CategoryDetails> getCategoryDetails(){
		return meals;
	}
}