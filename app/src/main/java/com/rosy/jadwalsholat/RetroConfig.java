package com.rosy.jadwalsholat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConfig {


    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.aladhan.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    return retrofit;
    }

}
