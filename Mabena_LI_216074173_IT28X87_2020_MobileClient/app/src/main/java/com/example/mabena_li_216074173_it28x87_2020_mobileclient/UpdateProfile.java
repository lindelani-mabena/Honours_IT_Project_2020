package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity {
    String religion, EmploymentStatus_id;
    Spinner  dropdownEmployment, dropdownReligion;
    Button btnUpdate;
    String strReligion, strEmployment;
    String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

         User=getIntent().getExtras().getString("U_id");
        religion=getIntent().getExtras().getString("res_seeker_religion");
        EmploymentStatus_id = getIntent().getExtras().getString("res_seeker_employmentstatus_id");


        dropdownEmployment= (Spinner) findViewById(R.id.spnEmploymentStatus);
        dropdownReligion = (Spinner) findViewById(R.id.spnReligion);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        String[] Religions = new String[]{"Religion","Christianity", "African Tradition", "Islam", "Bhuddhism", "Judaism", "Hinduism","Shikism", "Other"};
        String[] EmploymentStatus = new String[]{"Employment Status","Employed", "Self-Employed", "Unemployed", "Student"};

        ArrayAdapter<String> ReligionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Religions);
        ArrayAdapter<String> EmploymentStatusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, EmploymentStatus);

        dropdownReligion.setAdapter(ReligionsAdapter);
        dropdownEmployment.setAdapter(EmploymentStatusAdapter);
        int selectionPosition= ReligionsAdapter.getPosition(religion);
        dropdownReligion.setSelection(selectionPosition);
        int selectedPosition;

        if(EmploymentStatus_id.equals("1"))
        {
             selectedPosition= EmploymentStatusAdapter.getPosition("Employed");
            dropdownEmployment.setSelection(selectedPosition);

        }else if(EmploymentStatus_id.equals("2"))
        {
            selectedPosition= EmploymentStatusAdapter.getPosition("Self-Employed");
            dropdownEmployment.setSelection(selectedPosition);
        }else if(EmploymentStatus_id.equals("3"))
        {
            selectedPosition= EmploymentStatusAdapter.getPosition("Unemployed");
            dropdownEmployment.setSelection(selectedPosition);
        }
        else if(EmploymentStatus_id.equals("4"))
        {
            selectedPosition= EmploymentStatusAdapter.getPosition("Student");
           dropdownEmployment.setSelection(selectedPosition);
        }

        dropdownReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        strReligion = "Christianity";
                        break;
                    case 2:
                        strReligion = "African Tradition";
                        break;
                    case 3:
                        strReligion = "Islam";
                        break;
                    case 4:
                        strReligion = "Bhuddhism";
                        break;
                    case 5:
                        strReligion = "Judaism";
                        break;
                    case 6:
                        strReligion = "Hinduism";
                        break;
                    case 7:
                        strReligion = "Shikism";
                        break;
                    case 8:
                        strReligion = "Other";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dropdownEmployment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 1:
                        strEmployment = "1";
                        break;
                    case 2:
                        strEmployment = "2";
                        break;
                    case 3:
                        strEmployment = "3";
                        break;
                    case 4:
                        strEmployment = "4";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                networkHandler.UpdateProfile(User,strReligion,strEmployment).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()== 200)
                        {
                            Toast.makeText(getApplicationContext(), "Profile successfully updated", Toast.LENGTH_LONG).show();
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