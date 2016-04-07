package com.epicodus.restaurants;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.epicodus.restaurants.adapters.RestaurantListAdapter;
import com.epicodus.restaurants.ui.RestaurantsListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class RestaurantsActivityTest {
    private RestaurantsListActivity activity;
    private RecyclerView mRestaurantRecyclerView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(RestaurantsListActivity.class);
        mRestaurantRecyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
    }

    @Test
    public void restaurantRecyclerviewPopulates() {
        assertNotNull(mRestaurantRecyclerView.getRecycledViewPool());
    }

}
