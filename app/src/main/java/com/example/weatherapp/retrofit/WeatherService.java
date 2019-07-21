package com.example.weatherapp.retrofit;

import com.example.weatherapp.weatherpojo.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> loadWeather(@Query("APPID") String apikey, @Query("zip") String zipcode, @Query("units") String units);
}
