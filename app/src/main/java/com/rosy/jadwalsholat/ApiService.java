package com.rosy.jadwalsholat;

import com.rosy.jadwalsholat.Model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/timingsByCity")
    Call<ResponseData> getDataTimePray(
            @Query("city") String city,
            @Query("country") String country
    );
}
