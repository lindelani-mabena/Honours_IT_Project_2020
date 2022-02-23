package com.example.mabena_li_216074173_it28x87_2020_mobileclient;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosenAccommodation extends AppCompatActivity {
    Button btnAddReview, btnViewAllReviews;
    String seeker_id, accommodation_name;
    int accommodation_id;
    int address_id;
    Address Address;
    TextView txtAccommodationName, txtAccommodationAddress, txtAccommodationPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_accommodation);
        btnAddReview = (Button) findViewById(R.id.btnAddReview);
        btnViewAllReviews = (Button) findViewById(R.id.btnViewAllReviews);
        accommodation_id = getIntent().getExtras().getInt("accommodation_id");
        seeker_id=getIntent().getExtras().getString("seeker_id");
        address_id=getIntent().getExtras().getInt("address_id");
        accommodation_name = getIntent().getExtras().getString("accommodation_name");
        txtAccommodationName = (TextView)findViewById(R.id.txtAccommodationName);
        txtAccommodationAddress= (TextView)findViewById(R.id.txtAccommodationAddress);
        txtAccommodationPhone = (TextView)findViewById(R.id.txtPhoneNumber);
        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

       networkHandler.GetAddress(address_id).enqueue(new Callback<List<Address>>() {
           @Override
           public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
               if(response.code()==200)
               {
                    List<Address> AddressList = response.body();
                    Address = AddressList.get(0);
                    txtAccommodationName.setText("Accommodation Name: "+ accommodation_name);
                    txtAccommodationAddress.setText("Address:" + Address.getAddress_Line1() +", "+ Address.getAddressLine2()+", "+ Address.getAddress_Town()+ ", "+Address.getAddress_City()+ ","+ Address.getAddress_Province()+", "+Address.getAddress_PostalCode());txtAccommodationPhone.setText("Telephone Number:" +Address.getAddress_PhoneNumber());
               }
           }
           @Override
           public void onFailure(Call<List<Address>> call, Throwable t) {
           }
       });
       Toast.makeText(getApplicationContext(), "ID is "+ accommodation_id +" seeker id is" +seeker_id, Toast.LENGTH_LONG).show();
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                Intent newIntent = new Intent(getApplicationContext(), AddReview.class);
                newIntent.putExtra("s_id",seeker_id);
                newIntent.putExtra("accommodations_id", accommodation_id);
                startActivity(newIntent);
            }
        });
        btnViewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), AccommodationReviews.class);
                newIntent.putExtra("accommodations_id", accommodation_id);
                startActivity(newIntent);

            }
        });
    }
}