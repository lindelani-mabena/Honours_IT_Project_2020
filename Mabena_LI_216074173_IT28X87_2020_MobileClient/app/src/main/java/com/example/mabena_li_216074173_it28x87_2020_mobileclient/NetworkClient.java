package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public static Retrofit retrofitInst = null;

    public static Retrofit getNetworkClient() {
        retrofitInst =new Retrofit.Builder()
                .baseUrl("https://6a273d739682.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitInst;

    }
}
