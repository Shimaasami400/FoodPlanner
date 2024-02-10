package com.example.foodplanner.weekplan.view;

import com.example.foodplanner.model.dto.ListsDetails;
import com.example.foodplanner.model.dto.MealsItem;
import com.example.foodplanner.model.dto.WeekPlan;

public interface OnWeekPlanMealClickListener {
    void onDeleteItemClick(WeekPlan weekPlan);
    void onWeekPlanMealClick(WeekPlan weekPlan);

}
