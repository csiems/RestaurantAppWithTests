package com.epicodus.restaurants.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.restaurants.R;
import com.epicodus.restaurants.adapters.RestaurantPagerAdapter;
import com.epicodus.restaurants.models.Restaurant;
import com.epicodus.restaurants.util.ScaleAndFadePageTransformer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantDetailActivity extends AppCompatActivity {
    final static String TAG = RestaurantDetailActivity.class.getSimpleName();
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private RestaurantPagerAdapter adapterViewPager;
    ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING", TAG + " I've CREATED!" + this.toString());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
        setContentView(R.layout.activity_restaurant_detail);
        ButterKnife.bind(this);
        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new RestaurantPagerAdapter(getSupportFragmentManager(), mRestaurants);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
        mViewPager.setPageTransformer(true, new ScaleAndFadePageTransformer());

    }

    @Override
    protected void onStop() {
        Log.d("TESTING", TAG + " I've STOPPED!" + this.toString());
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("TESTING", TAG + " I've RESUMED!" + this.toString());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("TESTING", TAG + " I've PAUESED!" + this.toString());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("TESTING", TAG + " I've DESTROYED!" + this.toString());
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("TESTING", TAG + " I've RESTARED!" + this.toString());
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("TESTING", TAG + " I've STRATED!" + this.toString());
        super.onStart();
    }
}