package com.epicodus.restaurants.ui;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.restaurants.R;
import com.epicodus.restaurants.models.Restaurant;
import com.epicodus.restaurants.util.OnRestaurantSelectedListener;

import java.util.ArrayList;


public class RestaurantsListActivity extends AppCompatActivity implements OnRestaurantSelectedListener{
    private Integer mPosition;
    ArrayList<Restaurant> mRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
    }

    @Override
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants) {
        mPosition = position;
        mRestaurant = restaurants;
    }
}