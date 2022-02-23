package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerAccommodations extends AppCompatActivity {

    String manager_id;
    ListView ManagerResidencesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_accommodations);
        ManagerResidencesListView = (ListView)findViewById(R.id.ManagerResidencesListView);
        manager_id = getIntent().getStringExtra("mïd");

        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

        networkHandler.GetManagerAccommodations(manager_id).enqueue(new Callback<List<Accommodation>>() {


            @Override
            public void onResponse(Call<List<Accommodation>> call, Response<List<Accommodation>> response) {
                if (response.code()==200)
                {
                    List<Accommodation> ListAccommodations = response.body();
                    ManagerAccommodationAdapter adapter = new ManagerAccommodationAdapter(getApplicationContext(),ListAccommodations, R.layout.accommodation_layout);
                    ManagerResidencesListView.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),"name"+ ListAccommodations.get(0).getAccommodation_name(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Accommodation>> call, Throwable t) {

            }
        });
    }
}