package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccommodationReviews extends AppCompatActivity {
    ListView listAccommodationReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_reviews);

        listAccommodationReviews = (ListView) findViewById(R.id.ResidenceReviewsListView);

        int intAccommodationID = getIntent().getExtras().getInt("accommodations_id");

        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

        networkHandler.getAllAccommodationReviews(intAccommodationID).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.code()==200)
                {
                    List<Review> AccommodationReviews = response.body();
                    AccommodationsReviewsAdapter adapter = new AccommodationsReviewsAdapter(getApplicationContext(),AccommodationReviews, R.layout.accommodation_reviews_layout);
                    listAccommodationReviews.setAdapter(adapter);
            }
            }
            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });



    }
}