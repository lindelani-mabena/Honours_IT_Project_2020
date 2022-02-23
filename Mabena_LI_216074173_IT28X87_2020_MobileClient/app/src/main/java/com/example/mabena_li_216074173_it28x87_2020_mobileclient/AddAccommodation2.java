package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAccommodation2 extends AppCompatActivity {
    String Res_Name;
    Spinner dropdownResType;
    int Res_Type, Res_Address_ID, Res_Gym, Res_Security,Res_Parking, Res_WashingMachines,Res_Wifi, Res_ManagerID;
    RadioButton rbGym, rbWifi,rbWashingMachines,rbParkingLots,rbSecurity;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accommodation2);

        rbGym = (RadioButton)findViewById(R.id.rbGym);
        rbWifi=(RadioButton) findViewById(R.id.rbWifi);
        rbWashingMachines= (RadioButton) findViewById(R.id.rbWashingMachines);
        rbParkingLots =(RadioButton)findViewById(R.id.rbParkingLots);
        rbSecurity= (RadioButton)findViewById(R.id.rbSecurity);
        btnAdd= (Button)findViewById(R.id.btnAdd);

        dropdownResType = (Spinner) findViewById(R.id.spnResType);
        Res_Address_ID = getIntent().getExtras().getInt("id");
        Res_Name = getIntent().getExtras().getString("strResName");
        Res_ManagerID =getIntent().getExtras().getInt("man_id");


        String[] Res_Types = new String[]{"Accommodation Type","Hotel", "Apartment", "Flat", "Bread and Breakfast", "Communal", "Student Accommodation","Hostel","Camping"};

        ArrayAdapter<String> ResTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Res_Types);

        dropdownResType.setAdapter(ResTypeAdapter);

        dropdownResType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        break;
                    case 1:
                        Res_Type =1;
                        break;
                    case 2:
                        Res_Type =2;
                        break;
                    case 3:
                        Res_Type =3;
                        break;
                    case 4:
                        Res_Type =4;
                        break;
                    case 5:
                        Res_Type =5;
                        break;
                    case 6:
                        Res_Type =6;
                        break;
                    case 7:
                        Res_Type =7;
                        break;
                    case 8:
                        Res_Type =8;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
          btnAdd.setOnClickListener(new View.OnClickListener() {
              @Override
        public void onClick(View v) {

                  if(rbGym.isChecked())
                  {
                      Res_Gym = 1;
                  }
                  else
                  {
                      Res_Gym = 0;
                  }
                  if(rbWifi.isChecked())
                  {
                      Res_Wifi = 1;
                  }
                  else
                  {
                      Res_Wifi = 0;
                  }
                  if(rbParkingLots.isChecked())
                  {
                      Res_Parking=1;
                  }
                  else
                  {
                      Res_Parking=0;
                  }
                  if(rbSecurity.isChecked())
                  {
                      Res_Security =1;
                  }
                  else
                  {
                      Res_Security =0;
                  }
                  if(rbWashingMachines.isChecked())
                  {
                      Res_WashingMachines=1;
                  }
                  else
                  {
                      Res_WashingMachines=0;
                  }
                  NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                  networkHandler.AddAccommodation(Res_Name,Res_Address_ID,Res_Gym,Res_Security,Res_WashingMachines,Res_Wifi,Res_ManagerID,Res_Type).enqueue(new Callback<Void>() {
                      @Override
                      public void onResponse(Call<Void> call, Response<Void> response) {
                          if (response.code() == 201) {
                              Toast.makeText(getApplicationContext(), "added accommodation", Toast.LENGTH_LONG).show();
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