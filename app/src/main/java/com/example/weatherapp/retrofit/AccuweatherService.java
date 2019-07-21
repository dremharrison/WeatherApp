package com.example.weatherapp.retrofit;

import com.example.weatherapp.accuweatherpojo.AccuweatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccuweatherService {
        @GET("forecasts/v1/daily/5day/30309?")
        Call<AccuweatherResponse> loadAccuweather(@Query("apikey") String apikey);
    }
