package com.example.reviewpractice;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListMainActivity extends AppCompatActivity {

    // creating a variable for our list view, 
    // arraylist and firebase Firestore.
    ListView ReviewList;
    ArrayList<Review> reviewArrayList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_reviews);

        // below line is use to initialize our variables
        ReviewList = findViewById(R.id.reviewLV);
        reviewArrayList = new ArrayList<>();

        // initializing our variable for firebase 
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("Data").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding
                            // our progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                Review Review = d.toObject(Review.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
                                reviewArrayList.add(Review);
                            }
                            // after that we are passing our array list to our adapter class.
                            ReviewListAdapter adapter = new ReviewListAdapter(ListMainActivity.this, reviewArrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            ReviewList.setAdapter(adapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(ListMainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message
                        // when we get any error from Firebase.
                        Toast.makeText(ListMainActivity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}