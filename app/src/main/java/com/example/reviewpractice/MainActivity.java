package com.example.reviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button writeRevBT, writeRevBT2;
    String potty1, potty2, uid;
    private DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbr = FirebaseDatabase.getInstance().getReference("Potty/Rating");
        uid = dbr.push().getKey();
        dbr = FirebaseDatabase.getInstance().getReference("Potty");
        potty1 = dbr.push().getKey();
        potty2 = dbr.push().getKey();

        writeRevBT = (Button) findViewById(R.id.rrBT1);
        writeRevBT2 = (Button) findViewById(R.id.rrBT2);

        writeRevBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWriteReviewPage(1);
            }
        });

        writeRevBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWriteReviewPage(2);
            }
        });


    }
    public void toWriteReviewPage(Integer info) {
        Intent intent = new Intent(MainActivity.this, WriteReview.class);
        if (info == 1){
            intent.putExtra("pottyId", potty1);
            intent.putExtra("userId", uid);
        }
        else {
            intent.putExtra("pottyId", potty2);
            intent.putExtra("userId",uid);
        }

        startActivity(intent);
    }

}