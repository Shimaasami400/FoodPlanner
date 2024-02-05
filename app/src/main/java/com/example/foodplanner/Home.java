package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.model.dto.MealsItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    BottomNavigationView navigationView;

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.navigation_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.searchFragment2, R.id.favoriteFragment, R.id.calenderFragment2, R.id.randomMealFragment).build();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

         navController= Navigation.findNavController(this,R.id.nav_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);


    }
/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            if(drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }*/
@Override
public boolean onSupportNavigateUp() {
    return navController.navigateUp() || super.onSupportNavigateUp();
}
    public void onItemClickListener(MealsItem mealsItem) {
        // Handle click event from MealDetailsFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", mealsItem);
        navController.navigate(R.id.favoriteFragment, bundle);
    }

 }
