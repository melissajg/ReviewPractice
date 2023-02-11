package com.example.reviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewListAdapter extends ArrayAdapter<Review> {

    // constructor for our list view adapter.
    public ReviewListAdapter(@NonNull Context context, ArrayList<Review> ReviewArrayList) {
        super(context, 0, ReviewArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.show_review, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Review Review = getItem(position);

        // initializing our UI components of list view item.
        TextView nameTV = listitemView.findViewById(R.id.rr_rv_avatar);
        ImageView avatarIV = listitemView.findViewById(R.id.rr_rv_avatar);
        TextView reviewTV = listitemView.findViewById(R.id.rr_rv_review);
        RatingBar ratingTV = listitemView.findViewById(R.id.rr_rv_rating);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        nameTV.setText(Review.getUserName());
        reviewTV.setText(Review.getComment());
        ratingTV.setRating(Review.getRating());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.
        Picasso.get().load(Review.getImgUrl()).into(avatarIV);


        return listitemView;
    }
}