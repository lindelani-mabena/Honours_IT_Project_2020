package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAccommodation extends AppCompatActivity {
    TextView txtResName, txtAddressLine1, txtAddressLine2, txtResCity, txtResTown, txtResProvince, txtResPostalCode, txtPhoneNumber;
    String strResName, strAddressLine1, strAddressline2, strResCity, strResTown, strResProvince, strResPostalCode, strResPhoneNumber;
    Button btnAdd;  String manager_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accommodation);
        txtResName = (TextView)findViewById(R.id.txtAccommodationName);
        txtAddressLine1=(TextView)findViewById(R.id.txtAddressLine1);
        txtAddressLine2=(TextView)findViewById(R.id.txtResAddressline2);
        txtResCity=(TextView)findViewById(R.id.txtResCity);
        txtResTown=(TextView)findViewById(R.id.txtResSuburb);
        txtResProvince=(TextView)findViewById(R.id.txtResProvince);
        txtResPostalCode =(TextView) findViewById(R.id.txtResPostalCode);
        txtPhoneNumber = (TextView) findViewById(R.id.txtResPhoneNumber);
        btnAdd = (Button)findViewById(R.id.btnNext);
        manager_id = getIntent().getStringExtra("mïd");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strResName = txtResName.getText().toString();
                strAddressLine1=txtAddressLine1.getText().toString();
                strAddressline2 =txtAddressLine2.getText().toString();
                strResCity =txtResCity.getText().toString();
                strResTown =txtResTown.getText().toString();
                strResProvince =txtResProvince.getText().toString();
                strResPostalCode =txtResPostalCode.getText().toString();
                strResPhoneNumber =txtPhoneNumber.getText().toString();

                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);
                networkHandler.AddAddress(strAddressLine1,strAddressline2,strResTown,strResCity,strResProvince,strResPostalCode,strResPhoneNumber).enqueue(new Callback<AddressObject>() {
                    @Override
                    public void onResponse(Call<AddressObject> call, Response<AddressObject> response) {
                        if(response.code()==201)
                        {
                            AddressObject addressObject= response.body();
                            int id = addressObject.getAddress_Id();
                            Toast.makeText(getApplicationContext(), "added address on" + id, Toast.LENGTH_LONG).show();

                            Intent newIntent = new Intent(getApplicationContext(), AddAccommodation2.class);
                            newIntent.putExtra("strResName", strResName);
                            newIntent.putExtra("id", id);
                            newIntent.putExtra("man_id",manager_id);
                            startActivity(newIntent);
                        }
                        else
                        {

                        }
                    }
                    @Override
                    public void onFailure(Call<AddressObject> call, Throwable t) {

                    }
                });
            }
        });
    }
}