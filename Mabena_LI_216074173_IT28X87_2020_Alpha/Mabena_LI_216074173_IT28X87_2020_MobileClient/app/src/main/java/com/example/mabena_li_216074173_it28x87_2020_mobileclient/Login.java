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

public class Login extends AppCompatActivity {
    String strUsername, strPassword;
    TextView txtUsername, txtPassword, txtNoAccount, txtResetPassword;
    LoginObject user;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (TextView) findViewById(R.id.txtEmail);
        txtPassword =(TextView) findViewById(R.id.txtPassword);
        txtNoAccount= (TextView) findViewById(R.id.txtNoAccount);

        txtNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(newIntent);
            }
        });
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUsername = txtUsername.getText().toString();
                strPassword = txtPassword.getText().toString();


                final NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.Login(strUsername, strPassword).enqueue(new Callback<LoginObject>() {
                    @Override
                    public void onResponse(Call<LoginObject> call, Response<LoginObject> response) {
                        if(response.code()==200)
                        {
                            user = new LoginObject();
                            user = response.body();

                         final String id = user.getUser_id();
                            networkHandler.getUserType(id).enqueue(new Callback<com.example.mabena_li_216074173_it28x87_2020_mobileclient.User>() {
                                @Override
                                public void onResponse(Call<com.example.mabena_li_216074173_it28x87_2020_mobileclient.User> call, Response<com.example.mabena_li_216074173_it28x87_2020_mobileclient.User> response) {
                                   if(response.code()==200) {
                                       User usertype = response.body();
                                       String user_type = usertype.getUserType();
                                       String religion=usertype.getRes_seeker_religion();
                                       String EmploymentStatus_id = usertype.getRes_seeker_employmentstatus_id();

                                       Toast.makeText(getApplicationContext(), "user type is " +user_type , Toast.LENGTH_LONG).show();
                                       if(user_type.equals("Manager"))
                                       {
                                           Intent newIntent = new Intent(getApplicationContext(), ManagerHomeActivity.class);
                                           newIntent.putExtra("ïd", id);
                                           startActivity(newIntent);
                                       }else if(user_type.equals("Residence_Seeker"))
                                       {
                                           Intent newIntent = new Intent(getApplicationContext(), ResidenceSeekerHomeActivity.class);
                                           newIntent.putExtra("id", id);

                                           newIntent.putExtra("res_seeker_religion",religion);
                                           newIntent.putExtra("res_seeker_employmentstatus_id", EmploymentStatus_id);
                                           startActivity(newIntent);

                                       }else if(user_type.equals("Admin"))
                                       {
                                           Intent newIntent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                                           startActivity(newIntent);
                                       }
                                   }
                                }
                                @Override
                                public void onFailure(Call<com.example.mabena_li_216074173_it28x87_2020_mobileclient.User> call, Throwable t) {
                                }
                            });
                        }
                        else
                        {
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginObject> call, Throwable t) {

                    }
                });
            }
        });

    }
}