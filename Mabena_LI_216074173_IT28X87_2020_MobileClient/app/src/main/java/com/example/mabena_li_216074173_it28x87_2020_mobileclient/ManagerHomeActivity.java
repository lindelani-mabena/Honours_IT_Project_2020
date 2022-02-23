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

public class ManagerHomeActivity extends AppCompatActivity {

    String manager_id;
    CardView cardAddResidence;
    CardView cardViewResidences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        cardAddResidence = (CardView)findViewById(R.id.cardAddResidence);
        cardViewResidences =(CardView) findViewById(R.id.cardViewResidences);
        manager_id = getIntent().getStringExtra("ïd");

        cardAddResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), AddAccommodation.class);
                newIntent.putExtra("ïd", manager_id);
                startActivity(newIntent);
            }
        });

        cardViewResidences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.GetManagerAccommodations(manager_id).enqueue(new Callback<List<Accommodation>>() {
                    @Override
                    public void onResponse(Call<List<Accommodation>> call, Response<List<Accommodation>> response) {
                        if (response.code()==200)
                        {
                            List<Accommodation> ListAccommodations = response.body();
                            Toast.makeText(getApplicationContext(), ListAccommodations.get(1).getAccommodation_name(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Accommodation>> call, Throwable t) {

                    }
                });

            }
        });




    }
}