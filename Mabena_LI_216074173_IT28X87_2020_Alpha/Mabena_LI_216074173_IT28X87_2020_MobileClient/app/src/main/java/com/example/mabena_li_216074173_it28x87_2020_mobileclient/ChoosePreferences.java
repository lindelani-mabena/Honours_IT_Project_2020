package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ChoosePreferences extends AppCompatActivity {
    RadioButton  radioButtonGym, radioButtonWifi, radioButtonWashingMachine, radioButtonSecurity;
    int Gym=0, Wifi=0, WashingMachine=0,  Security=0;
            String U_id;



    Button btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_preferences);

        radioButtonGym= (RadioButton) findViewById(R.id.radiobGym);
        radioButtonWifi= (RadioButton) findViewById(R.id.radiobWifi);
        radioButtonWashingMachine= (RadioButton) findViewById(R.id.radiobWashingMachines);
        radioButtonSecurity= (RadioButton) findViewById(R.id.radiobSecurity);
        btnSelect =(Button) findViewById(R.id.btnSelect);

        U_id = getIntent().getExtras().getString("U_id");

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButtonGym.isChecked())
                {
                    Gym =1;
                }
                if(radioButtonWifi.isChecked())
                {
                    Wifi =1;
                }
                if(radioButtonWashingMachine.isChecked())
                {
                    WashingMachine =1;
                }
                if(radioButtonSecurity.isChecked())
                {
                    Security =1;
                }

                Intent newIntent = new Intent(v.getContext(), FilteredAccommodations.class);
                newIntent.putExtra("Gym", Gym);
                newIntent.putExtra("Wifi", Wifi);
                newIntent.putExtra("WashingMachine",WashingMachine );
                newIntent.putExtra("Security",Security );
                newIntent.putExtra("U_id", U_id);
                startActivity(newIntent);
            }
        });


    }
}