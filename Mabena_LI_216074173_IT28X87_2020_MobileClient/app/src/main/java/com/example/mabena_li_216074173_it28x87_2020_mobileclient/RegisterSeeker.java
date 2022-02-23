package com.example.mabena_li_216074173_it28x87_2020_mobileclient;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSeeker extends AppCompatActivity {
    Spinner dropdownRace, dropdownEmployment, dropdownReligion, dropdownHomeLanguages;
    Button btnRegister;
    String strRace, strEmployment, strReligion, strLanguage, strDateofBirth,strGender;
    TextView  txtSelectDate;
    DatePickerDialog pickerDOB;
    String user_id;
    RadioButton rbMale, rbFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seeker);
        user_id = getIntent().getExtras().getString("user_id");

        dropdownRace = (Spinner) findViewById(R.id.spnRace);
        dropdownEmployment= (Spinner) findViewById(R.id.spnEmploymentStatus);
        dropdownReligion = (Spinner) findViewById(R.id.spnReligion);
        dropdownHomeLanguages= (Spinner) findViewById(R.id.spnHomeLanguage);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtSelectDate = (TextView) findViewById(R.id.txtDate);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale=(RadioButton) findViewById(R.id.rbFeMale);

        String[] Races = new String[]{"Race", "African", "White", "Coloured", "Asian", "Other"};
        String[] Religions = new String[]{"Religion","Christianity", "African Tradition", "Islam", "Bhuddhism", "Judaism", "Hinduism","Shikism", "Other"};
        String[] EmploymentStatus = new String[]{"Employment Status","Employed", "Self-Employed", "Unemployed", "Student"};
        String[] HomeLanguages = new String[]{"Home Language","English", "Zulu", "Afrikaans", "Southern Sotho", "Northern Sotho", "Tshivenda", "Tswana", "Ndebele", "Xitsonga", "Xhosa","Swati", "Other"};

        ArrayAdapter<String> RacesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Races);
        ArrayAdapter<String> ReligionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Religions);
        ArrayAdapter<String> EmploymentStatusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, EmploymentStatus);
        ArrayAdapter<String> HomeLanguagesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, HomeLanguages);

        dropdownRace.setAdapter(RacesAdapter);
        dropdownReligion.setAdapter(ReligionsAdapter);
        dropdownHomeLanguages.setAdapter(HomeLanguagesAdapter);
        dropdownEmployment.setAdapter(EmploymentStatusAdapter);
        dropdownRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 1:
                        strRace = "African";
                        break;
                    case 2:
                        strRace = "White";
                        break;
                    case 3:
                        strRace = "Coloured";
                        break;
                    case 4:
                        strRace = "Asian";
                        break;
                    case 5:
                        strRace = "Other";
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        dropdownHomeLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 1:
                        strLanguage = "English";
                        break;
                    case 2:
                        strLanguage = "Zulu";
                        break;
                    case 3:
                        strLanguage = "Afrikaans";
                        break;
                    case 4:
                        strLanguage = "Southern Sotho";
                        break;
                    case 5:
                        strLanguage = "Northern Sotho";
                        break;
                    case 6:
                        strLanguage = "Tshivenda";
                        break;
                    case 7:
                        strLanguage = "Tswana";
                        break;
                    case 8:
                        strLanguage = "Ndebele";
                        break;
                    case 9:
                        strLanguage = "Xitsonga";
                        break;
                    case 10:
                        strLanguage = "Xhosa";
                        break;
                    case 11:
                        strLanguage = "Swati";
                        break;
                    case 12:
                        strLanguage = "Other";
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rbMale.isChecked()) {
                    strGender = "Male";

                }else if(rbFemale.isChecked())
                {
                    strGender= "female";
                }

                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.registerResSeeker(user_id,strGender, strDateofBirth,strRace,strReligion,strLanguage,strEmployment).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code()== 201)
                        {

                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                            Intent newIntent = new Intent(getApplicationContext(),Login.class);
                           startActivity(newIntent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
        txtSelectDate.setInputType(InputType.TYPE_NULL);
        txtSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                pickerDOB = new DatePickerDialog(RegisterSeeker.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtSelectDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                strDateofBirth =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                pickerDOB.show();
            }
        });


    }
}