package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EditResidence extends AppCompatActivity {

    RadioButton rbGym, rbWifi,rbWashingMachines,rbSecurity;
    int accommodation_id, accommodation_gym,accommodation_security , accommodation_washingmachines, accommodation_wifi,accommodation_type_id, Res_Type,Res_Gym,Res_Wifi,Res_Security,Res_WashingMachines;
    Spinner dropdownResType;
    Button btnConfirmEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_residence);
        accommodation_id = getIntent().getExtras().getInt("accommodation_id");
        accommodation_gym =getIntent().getExtras().getInt("accommodation_gym");
        accommodation_security= getIntent().getExtras().getInt("accommodation_security");
        accommodation_washingmachines= getIntent().getExtras().getInt("accommodation_washingmachines");
        accommodation_wifi =getIntent().getExtras().getInt("accommodation_wifi");
        accommodation_type_id = getIntent().getExtras().getInt("accommodation_type_id");
        rbGym = (RadioButton)findViewById(R.id.rbGym);
        rbWifi=(RadioButton) findViewById(R.id.rbWifi);
        rbWashingMachines= (RadioButton) findViewById(R.id.rbWashingMachines);
        rbSecurity= (RadioButton)findViewById(R.id.rbSecurity);
        dropdownResType = (Spinner) findViewById(R.id.spnResTyper);

        btnConfirmEdit =(Button) findViewById(R.id.btnConfirmEdit);
        if(accommodation_gym==1)
        {
            rbGym.setChecked(true);
        }
        if(accommodation_security==1)
        {
            rbSecurity.setChecked(true);
        }
        if(accommodation_washingmachines==1)
        {
            rbGym.setChecked(true);
        }
        if(accommodation_wifi==1)
        {
            rbWifi.setChecked(true);
        }

        String[] Res_Types = new String[]{"Accommodation Type","Hotel", "Apartment", "Flat", "Bread and Breakfast", "Communal", "Student Accommodation","Hostel","Camping"};

        ArrayAdapter<String> ResTypeAdapters = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Res_Types);

        dropdownResType.setAdapter(ResTypeAdapters);

        int selectedPosition;

        if(accommodation_type_id==1)
        {
            selectedPosition= ResTypeAdapters.getPosition("Hotel");
            dropdownResType.setSelection(selectedPosition);

        }else if(accommodation_type_id==2)
        {
            selectedPosition= ResTypeAdapters.getPosition("Apartment");
            dropdownResType.setSelection(selectedPosition);
        }else if(accommodation_type_id==3)
        {
            selectedPosition= ResTypeAdapters.getPosition("Flat");
            dropdownResType.setSelection(selectedPosition);
        }
        else if(accommodation_type_id==4)
        {
            selectedPosition= ResTypeAdapters.getPosition("Bread and Breakfast");
            dropdownResType.setSelection(selectedPosition);
        }
        else if(accommodation_type_id==5)
        {
            selectedPosition= ResTypeAdapters.getPosition("Communal");
            dropdownResType.setSelection(selectedPosition);
        }
        else if(accommodation_type_id==6)
        {
            selectedPosition= ResTypeAdapters.getPosition("Student Accommodation");
            dropdownResType.setSelection(selectedPosition);
        }
        else if(accommodation_type_id==7)
        {
            selectedPosition= ResTypeAdapters.getPosition("Hostel");
            dropdownResType.setSelection(selectedPosition);
        }
        else if(accommodation_type_id==8)
        {
            selectedPosition= ResTypeAdapters.getPosition("Camping");
            dropdownResType.setSelection(selectedPosition);
        }


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
        btnConfirmEdit.setOnClickListener(new View.OnClickListener() {
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
                networkHandler.UpdateAccommodation(accommodation_id,Res_Gym, Res_Security,Res_WashingMachines,Res_Wifi,Res_Type).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code()==200)
                        {

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