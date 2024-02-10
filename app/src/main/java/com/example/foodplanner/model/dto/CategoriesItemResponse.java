package com.example.foodplanner.model.dto;

import java.util.List;

import com.example.foodplanner.model.dto.CategoriesItem;
import com.google.gson.annotations.SerializedName;

public class CategoriesItemResponse {

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}