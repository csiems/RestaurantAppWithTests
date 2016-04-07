package com.epicodus.restaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.restaurants.R;
import com.epicodus.restaurants.models.Restaurant;
import com.epicodus.restaurants.ui.RestaurantDetailActivity;
import com.epicodus.restaurants.ui.RestaurantDetailFragment;
import com.epicodus.restaurants.util.ItemTouchHelperViewHolder;
import com.epicodus.restaurants.util.OnRestaurantSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/29/16.
 */
public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    @Bind(R.id.restaurantImageView) ImageView mRestaurantImageView;
    @Bind(R.id.restaurantDetailNameTextView) TextView mNameTextView;
    @Bind(R.id.categoryTextView) TextView mCategoryTextView;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    private Context mContext;
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    private Integer mPosition;
    private int mOrientation;
    private OnRestaurantSelectedListener mRestaurantSelectedListener;

    public RestaurantViewHolder(View itemView, ArrayList<Restaurant> restaurants, OnRestaurantSelectedListener restaurantSelectedListener) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mRestaurants = restaurants;
        mRestaurantSelectedListener = restaurantSelectedListener;
    }

    @Override
    public void onClick(View v) {
        mPosition = getLayoutPosition();

        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRestaurantSelectedListener.onRestaurantSelected(mPosition, mRestaurants);
            RestaurantDetailFragment detailFragment = RestaurantDetailFragment
                    .newInstance(mRestaurants.get(mPosition));
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.restaurantDetailContainer, detailFragment);
            ft.commit();
        } else {
            Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
            intent.putExtra("position", mPosition.toString());
            intent.putExtra("restaurants", Parcels.wrap(mRestaurants));
            mContext.startActivity(intent);
        }
    }

    public void bindRestaurant(Restaurant restaurant) {
        Picasso.with(mContext)
                .load(restaurant.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRestaurantImageView);
        mNameTextView.setText(restaurant.getName());
        mCategoryTextView.setText(restaurant.getCategories().get(0));
        mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        mOrientation = itemView.getResources().getConfiguration().orientation;
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}