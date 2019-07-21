package com.example.weatherapp.retrofit;

import com.example.weatherapp.forecastpojo.ForecastResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastService {
    @GET("data/2.5/forecast?")
    Call<ForecastResponse> loadForecast(@Query("APPID") String apikey, @Query("zip") String zipcode, @Query("units") String units);
}
