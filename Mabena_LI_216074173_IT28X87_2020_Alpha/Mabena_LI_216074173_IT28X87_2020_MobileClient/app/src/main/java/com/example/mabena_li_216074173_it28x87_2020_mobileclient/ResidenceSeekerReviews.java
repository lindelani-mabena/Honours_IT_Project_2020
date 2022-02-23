package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidenceSeekerReviews extends AppCompatActivity {

int intID;
ListView ResidenceReviewsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_seeker_reviews);
        intID = getIntent().getExtras().getInt("intID");
        ResidenceReviewsListView = (ListView)findViewById(R.id.ResidenceReviewsListView);
        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
        networkHandler.GetSeekerReviews(intID).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.code()==200)
                {
                    List<Review> listReviews = response.body();

                    ResSeekerReviewsAdapter adapter = new ResSeekerReviewsAdapter(getApplicationContext(),listReviews, R.layout.residence_seeker_reviews_layout);
                    ResidenceReviewsListView.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });

    }
}