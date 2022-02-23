package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class EditReview extends AppCompatActivity {
    int ID;
    String Description;
    int Rating;
    Button btnEditReview, btnCancel;
    RatingBar barReviewsRating;
    TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        btnEditReview = (Button) findViewById(R.id.btnEditReview);
        btnCancel =(Button) findViewById(R.id.btnCancel);
        txtDescription =(TextView) findViewById(R.id.txtEditReviewDescription);
        barReviewsRating =(RatingBar)findViewById(R.id.EditreviewRatingBar);

        ID = getIntent().getExtras().getInt("review_id");

        btnEditReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                String strDescription = txtDescription.getText().toString();
                float rating = barReviewsRating.getRating();
                int intrating = (int) rating;

                networkHandler.EditReview(ID,strDescription, intrating).enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(getApplicationContext(), "Edited Review", LENGTH_LONG).show();
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