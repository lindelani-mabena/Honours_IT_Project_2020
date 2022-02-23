package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredAccommodations extends AppCompatActivity {
    List<Accommodation> AccommodationsList;
    ListView listViewAccommodations;
    Intent newActivity;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_accommodations);
        id = getIntent().getExtras().getString("U_id");
        listViewAccommodations = (ListView)findViewById(R.id.AccommodationsListView);
        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
        networkHandler.GetAllAccommodations().enqueue(new Callback<List<Accommodation>>() {
            @Override
            public void onResponse(Call<List<Accommodation>> call, Response<List<Accommodation>> response) {

                NetworkHandler networkHandler =  NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.GetAllAccommodations().enqueue(new Callback<List<Accommodation>>() {
                    @Override
                    public void onResponse(Call<List<Accommodation>> call, Response<List<Accommodation>> response) {
                        if(response.code()==200)
                        {
                            AccommodationsList = response.body();
                            AccommodationAdapter adapter = new AccommodationAdapter(getApplicationContext(),AccommodationsList, R.layout.accommodation_layout,id);

                            listViewAccommodations.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Accommodation>> call, Throwable t) {
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Accommodation>> call, Throwable t) {

            }
        });
    }
}