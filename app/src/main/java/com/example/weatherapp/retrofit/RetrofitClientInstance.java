package com.example.weatherapp.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    // Declare and Init our base url
    public static final String weather_base_url = "http://api.openweathermap.org/";
    public static final String forecast_base_url = "http://api.openweathermap.org/";
    public static final String accuweather_base_url = "http://dataservice.accuweather.com/";

    // Declare Retrofit object
    private static Retrofit openweatherRetrofit;
    private static Retrofit accuweatherRetrofit;

    // Create a private constructor
    private RetrofitClientInstance() {

    }

    // Create public static method to get instance of the retrofit object
    public static Retrofit getWeatherRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                return chain.proceed(request);
            }
        });

        if (openweatherRetrofit == null) {
            openweatherRetrofit = new Retrofit.Builder()
                    .baseUrl(weather_base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return openweatherRetrofit;
    }

    public static Retrofit getForecastRetrofit() {

        if (openweatherRetrofit == null) {
            openweatherRetrofit = new Retrofit.Builder()
                    .baseUrl(forecast_base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return openweatherRetrofit;
    }

    public static Retrofit getAccuweatherRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                return chain.proceed(request);
            }
        });

        if (accuweatherRetrofit == null) {
            accuweatherRetrofit = new Retrofit.Builder()
                    .baseUrl(accuweather_base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return accuweatherRetrofit;
    }
}
