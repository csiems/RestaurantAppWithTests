package com.epicodus.restaurants;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.epicodus.restaurants.ui.RestaurantsListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;


/**
 * Created by Guest on 4/7/16.
 */
public class RestaurantsListActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<RestaurantsListActivity> activityTestRule =
            new ActivityTestRule<>(RestaurantsListActivity.class);

    @Test
    public void validateSearchFunctionExists() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("97217"))
                .check(matches(withText("97217")));
    }
    @Test
    public void validateSearchFunctionWorks() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("97217")).perform(pressKey(66));
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //how to check text on recyclerView (see also import in gradle file
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("King Burrito Mexican Food"))));
    }

    @Test
    public void recyclerViewClickReturnsRestaurant() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("97217")).perform(pressKey(66));
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Click on the RecyclerView item at position 2
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.addressTextView)).check(matches(withText("5907 N Lombard St, Portsmouth, Portland, OR 97203")));
    }



}

