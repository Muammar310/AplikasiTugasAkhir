package com.rosy.jadwalsholat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.rosy.jadwalsholat.Model.Data;
import com.rosy.jadwalsholat.Model.ResponseData;
import com.rosy.jadwalsholat.Model.Timings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvSubuh,tvImsak,tvDhuhur,tvAshar,tvMagrhib,tvIsya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchVIew();
    }

    private void initGetData(String city) {
        ApiService apiService = RetroConfig.getRetrofit().create(ApiService.class);
        Call<ResponseData> call = apiService.getDataTimePray(city,"indonesia");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, final Response<ResponseData> response) {
                if (response.body() != null){
                    tvSubuh = findViewById(R.id.tv_subuh);
                    tvImsak = findViewById(R.id.tv_imsak);
                    tvDhuhur = findViewById(R.id.tv_dzuhur);
                    tvAshar = findViewById(R.id.tv_ashar);
                    tvMagrhib = findViewById(R.id.tv_magrib);
                    tvIsya = findViewById(R.id.tv_isya);
                    tvSubuh.setText(response.body().getData().getTimings().getFajr());
                    tvDhuhur.setText(response.body().getData().getTimings().getDhuhr());
                    tvAshar.setText(response.body().getData().getTimings().getAsr());
                    tvMagrhib.setText(response.body().getData().getTimings().getMaghrib());
                    tvIsya.setText(response.body().getData().getTimings().getIsha());
                    tvImsak.setText(response.body().getData().getTimings().getImsak());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }

    private void initSearchVIew(){
        SearchView sv = findViewById(R.id.sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView lokasi = findViewById(R.id.lokasi);
                lokasi.setText(query);
                initGetData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
