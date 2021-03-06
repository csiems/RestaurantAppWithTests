package com.epicodus.restaurants.ui;



import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.epicodus.restaurants.R;
import com.epicodus.restaurants.models.Restaurant;
import com.epicodus.restaurants.util.OnRestaurantSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;


public class RestaurantsListActivity extends AppCompatActivity implements OnRestaurantSelectedListener {
    public static final String TAG = RestaurantsListActivity.class.getSimpleName();
    private Integer mPosition;
    ArrayList<Restaurant> mRestaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int size;

        try {
            ArrayList<Restaurant> restaurants = Parcels.unwrap(savedInstanceState.getParcelable("restaurants"));
            size = restaurants.size();
        } catch (NullPointerException npe) {
            size = -1;
        }

        if (savedInstanceState != null && size > -1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt("position");
                mRestaurants = Parcels.unwrap(savedInstanceState.getParcelable("restaurants"));
                Intent intent = new Intent(this, RestaurantDetailActivity.class);
                intent.putExtra("position", mPosition.toString());
                intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
                startActivity(intent);
            }
        }
        setContentView(R.layout.activity_restaurants);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mPosition != null && mRestaurants != null) {
            outState.putInt("position", Integer.valueOf(mPosition));
            outState.putParcelable("restaurants", Parcels.wrap(mRestaurants));
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants) {
        mPosition = position;
        mRestaurants = restaurants;
    }

}