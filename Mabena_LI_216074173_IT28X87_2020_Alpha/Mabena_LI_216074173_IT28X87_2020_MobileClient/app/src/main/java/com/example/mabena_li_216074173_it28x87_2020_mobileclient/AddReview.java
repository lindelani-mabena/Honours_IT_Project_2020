package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReview extends AppCompatActivity {
    Button btnAddReview, btnCancel;
    RatingBar barReviewsRating;
    TextView txtDescription;
    int user_id,noOfStars;
    int accommodation_id;
    String u_id;
    int a_id;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        btnAddReview = (Button) findViewById(R.id.btnAddReview);
        btnCancel =(Button) findViewById(R.id.btnCancel);
        txtDescription =(TextView) findViewById(R.id.txtReviewDescription);
        barReviewsRating =(RatingBar)findViewById(R.id.reviewRatingBar);
        u_id = getIntent().getExtras().getString("s_id");
        a_id = getIntent().getExtras().getInt("accommodations_id");
        Toast.makeText(getApplicationContext(), "ID is "+ a_id +" seeker id is "+u_id, Toast.LENGTH_LONG).show();
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 noOfStars =barReviewsRating.getNumStars();

                 float rating = barReviewsRating.getRating();
                 int intr = (int) rating;

                 description = txtDescription.getText().toString();


                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.addNewReview(noOfStars,description,u_id,a_id).enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        if(response.code()==201) {
                            Toast.makeText(getApplicationContext(), "Added review", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }

                    }
                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {

                    }
                });

            }
        });
    }
}