package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {
    String strName, strTitle, strEmail, strPassword, strConfirmPassword;
    TextView txtName, txtTitle, txtEmail, txtPassword, txtCPassword;
    Button btnRegister, btnCancel;
    RadioButton  rbResidenceSeeker, rbManager;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        txtName = (TextView)findViewById(R.id.txtName);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtEmail =(TextView)findViewById(R.id.txtEmail);
        txtPassword= (TextView) findViewById(R.id.txtPassword);
        txtCPassword = (TextView) findViewById(R.id.txtConfirmPassword);
        btnRegister =(Button) findViewById(R.id.btnRegister);
        btnCancel =(Button) findViewById(R.id.btnCancel);

        rbResidenceSeeker=(RadioButton) findViewById(R.id.rbResSeeker);
        rbManager = (RadioButton) findViewById(R.id.rbManager);


        rbResidenceSeeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbManager.setChecked(false);
            }
        });
        rbManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbResidenceSeeker.setChecked(false);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strName = txtName.getText().toString();
                strTitle = txtTitle.getText().toString();
                strEmail = txtEmail.getText().toString();
                strPassword = txtPassword.getText().toString();
                strConfirmPassword = txtCPassword.getText().toString();

                final NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                if(strPassword.equals(strConfirmPassword))
                {
                     networkHandler.Register(strName, strTitle, strEmail,strPassword,strConfirmPassword).enqueue(new Callback<RegisterModel>() {
                         @Override
                         public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                             if (response.code() == 201) {
                                 RegisterModel registerModel = response.body();
                                 id = registerModel.getUser_id();
                                 Toast.makeText(getApplicationContext(), "Registered User", Toast.LENGTH_LONG).show();
                                 if (rbResidenceSeeker.isChecked()) {

                                     Intent newIntent = new Intent(getApplicationContext(), RegisterSeeker.class);
                                     newIntent.putExtra("user_id", registerModel.getUser_id());

                                     startActivity(newIntent);

                                 } else if (rbManager.isChecked()) {
                                     networkHandler.registerManager(registerModel.getUser_id()).enqueue(new Callback<RegisterManagerModel>() {
                                         @Override
                                         public void onResponse(Call<RegisterManagerModel> call, Response<RegisterManagerModel> response2) {
                                             if (response2.code() == 201) {
                                                 Toast.makeText(getApplicationContext(), "Registered Manager", Toast.LENGTH_LONG).show();

                                                 Intent newIntent = new Intent(getApplicationContext(), ManagerHomeActivity.class);
                                                 newIntent.putExtra("id", id);
                                                 startActivity(newIntent);
                                             }
                                             else
                                             {
                                                 Toast.makeText(getApplicationContext(), response2.message(), Toast.LENGTH_LONG).show();
                                             }
                                         }
                                         @Override
                                         public void onFailure(Call<RegisterManagerModel> call, Throwable t) {
                                         }
                                     });
                                 }

                             } else {
                                 String error = response.message();

                                 Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<RegisterModel> call, Throwable t) {
                         }
                     });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Your password and Confirm password do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}