package com.example.reviewpractice;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;

public class WriteReview extends AppCompatActivity {

    private Button backBT, submitBT, imgBT;
    private ImageView imgIV;
    private RatingBar ratingRB;
    private EditText reviewET;
    private String pottyId, userId, userName;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);

        //get potty id from intent
        pottyId = getIntent().getStringExtra("pottyId");
        userId = getIntent().getStringExtra("userId");
        userName = "melissa";

        // get ui information
        backBT = (Button) findViewById(R.id.back);
        submitBT = (Button) findViewById(R.id.submit);
        imgBT = (Button) findViewById(R.id.addPhotos);
        imgIV = (ImageView) findViewById(R.id.previewImage);
        ratingRB = (RatingBar) findViewById(R.id.rating);
        reviewET = (EditText) findViewById(R.id.comment);

        imgBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadPhotos();
            }
        });

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review rev = new Review(ratingRB.getRating(),
                        reviewET.getText().toString().trim(),
                        String.valueOf(System.currentTimeMillis()), 0,imgIV.toString(),userName);
                dbr = FirebaseDatabase
                        .getInstance("https://reviewpractice-36ce6-default-rtdb.firebaseio.com")
                        .getReference("Potty/Ratings");
                dbr.child(userId).setValue(rev);
                Toast.makeText(WriteReview.this, "Review Submitted", Toast.LENGTH_SHORT).show();
            }
        });
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
/*
    private void saveReview(Float rating, String comment, String timestamp, Integer helpfulnes,String imgUrl, String userName) {
        Review rev = new Review(rating, comment, timestamp, helpfulnes, imgUrl,userName);
        dbr = FirebaseDatabase
                .getInstance("https://reviewpractice-36ce6-default-rtdb.firebaseio.com")
                .getReference("Potty/Ratings");
        dbr.child(userId).setValue(rev);
    }*/
    public void goBack() {
        Intent intent = new Intent(WriteReview.this, MainActivity.class);
        startActivity(intent);
    }

}
    /*
    private void inputData(){
        String rating = String.valueOf(ratingRB.getRating());
        String review = reviewET.getText().toString().trim();
        String ts = String.valueOf(System.currentTimeMillis());
        // data into hashmap
        HashMap<String,Object> rev = new HashMap<>();
        rev.put("userid",userId);
        rev.put("rating", rating);
        rev.put("review",review);
        //hashmap containing data into database
        dbr = FirebaseDatabase
                .getInstance("https://reviewpractice-36ce6-default-rtdb.firebaseio.com")
                .getReference("Potty/Ratings");
        userId = dbr.push().getKey();
        Toast.makeText(WriteReview.this, "userId: "+userId, Toast.LENGTH_LONG).show();
        dbr.child(userId).updateChildren(rev);

    }
     */




    


     
