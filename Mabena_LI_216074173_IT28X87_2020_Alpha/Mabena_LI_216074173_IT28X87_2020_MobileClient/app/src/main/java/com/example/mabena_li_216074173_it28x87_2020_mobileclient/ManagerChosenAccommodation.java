package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerChosenAccommodation extends AppCompatActivity {
    Button btnEdit, btnDelete;
    int accommodation_id, address_id,accommodation_gym, accommodation_security, accommodation_washingmachines, accommodation_wifi, accommodation_type_id;
    String accommodation_name;
    TextView txtAccommodationName, txtAccommodationAddress, txtAccommodationPhone, txtGym, txtSecurity, txtWashingMachines,txtWifi, txtAccommodationType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_chosen_accommodation);


        btnEdit  = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        accommodation_id = getIntent().getExtras().getInt("accommodation_id");
        address_id=getIntent().getExtras().getInt("address_id");
        accommodation_name = getIntent().getExtras().getString("accommodation_name");
        txtAccommodationName = (TextView)findViewById(R.id.txtAccommodationName);
        txtAccommodationAddress= (TextView)findViewById(R.id.txtAccommodationAddress);
        txtAccommodationPhone = (TextView)findViewById(R.id.txtPhoneNumber);
        txtGym= (TextView)findViewById(R.id.txtGym);
        txtSecurity= (TextView)findViewById(R.id.txtSecurity);
        txtWashingMachines= (TextView)findViewById(R.id.txtWashingMachines);
        txtWifi= (TextView)findViewById(R.id.txtWifi);
        txtAccommodationType =(TextView)findViewById(R.id.txtAccommodationType);

        accommodation_gym =getIntent().getExtras().getInt("accommodation_gym");
        accommodation_security= getIntent().getExtras().getInt("accommodation_security");
        accommodation_washingmachines= getIntent().getExtras().getInt("accommodation_washingmachines");
        accommodation_wifi =getIntent().getExtras().getInt("accommodation_wifi");
        accommodation_type_id = getIntent().getExtras().getInt("accommodation_type_id");

        Toast.makeText(getApplicationContext(), "gym " + accommodation_gym, Toast.LENGTH_LONG).show();
        NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
        networkHandler.GetAddress(address_id).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if(response.code()==200)
                {
                    List<Address> AddressList = response.body();
                    Address address = AddressList.get(0);
                    txtAccommodationName.setText("Accommodation Name: "+ accommodation_name);
                    txtAccommodationAddress.setText("Address:" + address.getAddress_Line1() +", "+ address.getAddressLine2()+", "+ address.getAddress_Town()+ ", "+address.getAddress_City()+ ","+ address.getAddress_Province()+", "+address.getAddress_PostalCode());txtAccommodationPhone.setText("Telephone Number:" +address.getAddress_PhoneNumber());
                }
            }
            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
            }
        });

        if((accommodation_gym==0))
        {
            txtGym.setText("Gym: Not Available");
        }
        else
        {
            txtGym.setText("Gym: Available");
        }
        if(accommodation_security==0)
        {
            txtSecurity.setText("Security: Not Available");
        }
        else
        {
            txtSecurity.setText("Security: Available");
        }
        if(accommodation_washingmachines==0)
        {
            txtWashingMachines.setText("Washing Machines: Not Available");
        }
        else
        {
            txtWashingMachines.setText("Washing Machines: Available");
        }
        if(accommodation_wifi==0)
        {
            txtWifi.setText("Wifi: Not Available");
        }
        else
        {
            txtWifi.setText("Wi-Fi: Available");
        }

        if(accommodation_type_id==1)
        {
            txtAccommodationType.setText("Type of Residence: Hotel");
        }else if(accommodation_type_id==2)
        {
            txtAccommodationType.setText("Type of Residence: Apartment");
        }
        else if(accommodation_type_id==3)
        {
            txtAccommodationType.setText("Type of Residence: Flat");
        }
        else if(accommodation_type_id==4)
        {
            txtAccommodationType.setText("Type of Residence: BnB");
        }
        else if(accommodation_type_id==5)
        {
            txtAccommodationType.setText("Type of Residence: Communes");
        }
        else if(accommodation_type_id==6)

        {
            txtAccommodationType.setText("Type of Residence: Student Accommodation");
        }
        else if(accommodation_type_id==7)
        {
            txtAccommodationType.setText("Type of Residence: Hostel");
        }
        else if(accommodation_type_id==8)
        {
            txtAccommodationType.setText("Type of Residence: Camping");
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                Intent newIntent = new Intent(getApplicationContext(), EditResidence.class);

                newIntent.putExtra("accommodation_id", accommodation_id);
                newIntent.putExtra("accommodation_gym", accommodation_gym);
                newIntent.putExtra("accommodation_security",accommodation_security);
                newIntent.putExtra("accommodation_washingmachines", accommodation_washingmachines);
                newIntent.putExtra("accommodation_wifi", accommodation_wifi);
                newIntent.putExtra("accommodation_type_id", accommodation_type_id);
                startActivity(newIntent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.DeleteAccommodation(accommodation_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(getApplicationContext(), "Accommodation successfully deleted", Toast.LENGTH_LONG).show();

                        }
                        if(response.code()==404)
                        {
                            Toast.makeText(getApplicationContext(), "Accommodation not found", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        });
    }
}