package com.example.foodplanner.model.dto;

import com.google.gson.annotations.SerializedName;

public class AreaItem {

	@SerializedName("strArea")
	private String strArea;

	public String getStrArea(){
		return strArea;
	}
}