package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidenceSeekerHomeActivity extends AppCompatActivity {
    CardView cardViewSeekerReviews;
    CardView cardUpdateProfile;
    CardView cardViewListResidences;
    String residence_seeker_id,  religion, EmploymentStatus_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_seeker_home);
        residence_seeker_id = getIntent().getStringExtra("id");
        religion=getIntent().getStringExtra("res_seeker_religion");
        EmploymentStatus_id = getIntent().getStringExtra("res_seeker_employmentstatus_id");


        cardViewSeekerReviews = (CardView)findViewById(R.id.cardViewReviews);
        cardUpdateProfile = (CardView)findViewById(R.id.cardViewProfile);
        cardViewListResidences = (CardView)findViewById(R.id.cardFindResidence);

        final int intID = Integer.parseInt(residence_seeker_id);

        cardViewSeekerReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ResidenceSeekerReviews.class);
                intent.putExtra("intID", intID);
                startActivity(intent);


            }
        });

        cardUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), UpdateProfile.class);

                Toast.makeText(getApplicationContext(), "Id 2 is " +residence_seeker_id, Toast.LENGTH_LONG).show();
                newIntent.putExtra("U_id", residence_seeker_id);
                newIntent.putExtra("res_seeker_religion",religion);
                newIntent.putExtra("res_seeker_employmentstatus_id", EmploymentStatus_id);
                startActivity(newIntent);
            }
        });

        cardViewListResidences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChoosePreferences.class);
                intent.putExtra("U_id", residence_seeker_id);
                startActivity(intent);
            }
        });


    }
}