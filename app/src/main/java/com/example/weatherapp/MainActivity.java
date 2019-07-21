package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.accuweatherpojo.AccuweatherResponse;
import com.example.weatherapp.forecastpojo.ForecastResponse;
import com.example.weatherapp.retrofit.AccuweatherService;
import com.example.weatherapp.retrofit.ForecastService;
import com.example.weatherapp.retrofit.RetrofitClientInstance;
import com.example.weatherapp.retrofit.WeatherService;
import com.example.weatherapp.weatherpojo.WeatherResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivitys";
    private final String OPEN_WEATHER_APP_ID = "84869316442320747ea4c9e3a0608c2d";
    private final String ACCUWEATHER_APP_ID = "WUBtmi1nL8EqkSgVhsrr5Z0dGVLSQbEW";
    private final String zipcode = "30309,us";
    private final String units = "imperial";

    @BindView(R.id.tv_city_name) TextView tvCityName;
    @BindView(R.id.tv_temperature) TextView tvTemp;
    @BindView(R.id.tv_wind) TextView tvWind;
    @BindView(R.id.tv_weather_main) TextView tvWeatherMain;
    @BindView(R.id.tv_weather_description) TextView tvWeatherDescription;

    @BindView(R.id.tv_dayone_temp) TextView tvDayOneTemp;
    @BindView(R.id.tv_daytwo_temp) TextView tvDayTwoTemp;
    @BindView(R.id.tv_daythree_temp) TextView tvDayThreeTemp;
    @BindView(R.id.tv_dayfour_temp) TextView tvDayFourTemp;
    @BindView(R.id.tv_dayfive_temp) TextView tvDayFiveTemp;

    @BindView(R.id.tv_dayone_date) TextView tvDayOneDate;
    @BindView(R.id.tv_daytwo_date) TextView tvDayTwoDate;
    @BindView(R.id.tv_daythree_date) TextView tvDayThreeDate;
    @BindView(R.id.tv_dayfour_date) TextView tvDayFourDate;
    @BindView(R.id.tv_dayfive_date) TextView tvDayFiveDate;

    @BindView(R.id.iv_weather_icon) ImageView ivWeatherIcon;

    @BindView(R.id.iv_dayone_icon) ImageView ivDayOneIcon;
    @BindView(R.id.iv_daytwo_icon) ImageView ivDayTwoIcon;
    @BindView(R.id.iv_daythree_icon) ImageView ivDayThreeIcon;
    @BindView(R.id.iv_dayfour_icon) ImageView ivDayFourIcon;
    @BindView(R.id.iv_dayfive_icon) ImageView ivDayFiveIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofitWeatherRequest(OPEN_WEATHER_APP_ID, zipcode, units);
        retrofitForecastRequest(OPEN_WEATHER_APP_ID, zipcode, units);
        retrofitAccuweatherRequest(ACCUWEATHER_APP_ID);
    }

    public void retrofitWeatherRequest(String apikey, String zip, String units) {
        // 1. Declare GiphyService and Init using RetrofitClientInstance
        WeatherService weatherService = RetrofitClientInstance.getWeatherRetrofit().create(WeatherService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        retrofit2.Call<WeatherResponse> weathercall = weatherService.loadWeather(apikey, zip, units);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        weathercall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(retrofit2.Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success " + response.body());

                    Double tempDouble = response.body().getMain().getTemp();
                    int temp = (int) Math.rint(tempDouble);

                    Double windDouble = response.body().getWind().getSpeed();
                    int wind = (int) Math.rint(windDouble);

                    String cityName = response.body().getName();
                    String weatherMain = response.body().getWeather().get(0).getMain();
                    String weatherDescription = response.body().getWeather().get(0).getDescription();


                    tvCityName.setText(cityName);
                    tvTemp.setText(temp + "º");
                    tvWind.setText("Wind: " + wind + " mph");
                    tvWeatherMain.setText(weatherMain);

                    if (weatherDescription.equals("mist")){
                        tvWeatherDescription.setText("light mist");
                    } else {
                        tvWeatherDescription.setText(weatherDescription);
                    }

                    if (weatherDescription.equals("clear sky")) {
                       ivWeatherIcon.setImageResource(R.drawable.sunny);
                    } else if (weatherDescription.equals("few clouds") || weatherDescription.equals("scattered clouds") || weatherDescription.equals("broken clouds")) {
                       ivWeatherIcon.setImageResource(R.drawable.partly_cloudy);
                    } else if (weatherDescription.equals("shower rain") || weatherDescription.equals("rain")){
                       ivWeatherIcon.setImageResource(R.drawable.rain);
                    } else if (weatherDescription.equals("thunderstorm")){
                       ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    } else if (weatherDescription.equals("snow")){
                       ivWeatherIcon.setImageResource(R.drawable.snow);
                    } else if (weatherDescription.equals("mist")){
                       ivWeatherIcon.setImageResource(R.drawable.mist);
                    }


                } else {
                    Log.d(TAG, "onResponse: Fail");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void retrofitForecastRequest(String apikey, String zip, String units) {
        // 1. Declare GiphyService and Init using RetrofitClientInstance
        ForecastService forecastService = RetrofitClientInstance.getForecastRetrofit().create(ForecastService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        retrofit2.Call<ForecastResponse> forecastcall = forecastService.loadForecast(apikey, zip, units);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        forecastcall.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success " + response.body());


                } else {
                    Log.d(TAG, "onResponse: Fail");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ForecastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void retrofitAccuweatherRequest(String apikey) {
        // 1. Declare GiphyService and Init using RetrofitClientInstance
        AccuweatherService accuweatherService = RetrofitClientInstance.getAccuweatherRetrofit().create(AccuweatherService.class);

        // Declare GiphyService Return type and Init using the GiphyService from step 1
        retrofit2.Call<AccuweatherResponse> accuweatherCall = accuweatherService.loadAccuweather(apikey);

        // 3. Use the giphyCall from step 2 and call the .enqueue method
        accuweatherCall.enqueue(new Callback<AccuweatherResponse>() {
            @Override
            public void onResponse(retrofit2.Call<AccuweatherResponse> call, Response<AccuweatherResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Success " + response.body());

//                    tvDayOneDate.setText(String.valueOf(new Date(response.body().getDailyForecasts().get(0).getEpochDate()  * 1000L)).substring(0,3));
//                    tvDayTwoDate.setText(String.valueOf(new Date(response.body().getDailyForecasts().get(1).getEpochDate()  * 1000L)).substring(0,3));
//                    tvDayThreeDate.setText(String.valueOf(new Date(response.body().getDailyForecasts().get(2).getEpochDate()  * 1000L)).substring(0,3));
//                    tvDayFourDate.setText(String.valueOf(new Date(response.body().getDailyForecasts().get(3).getEpochDate()  * 1000L)).substring(0,3));
//                    tvDayFiveDate.setText(String.valueOf(new Date(response.body().getDailyForecasts().get(4).getEpochDate()  * 1000L)).substring(0,3));
//
//                    String dayOneIconNum = String.valueOf(response.body().getDailyForecasts().get(0).getDay().getIcon());
//                    setDayOneIcon(dayOneIconNum);
//
//                    String dayTwoIconNum = String.valueOf(response.body().getDailyForecasts().get(1).getDay().getIcon());
//                    setDayTwoIcon(dayTwoIconNum);
//
//                    String dayThreeIconNum = String.valueOf(response.body().getDailyForecasts().get(2).getDay().getIcon());
//                    setDayThreeIcon(dayThreeIconNum);
//
//                    String dayFourIconNum = String.valueOf(response.body().getDailyForecasts().get(3).getDay().getIcon());
//                    setDayFourIcon(dayFourIconNum);
//
//                    String dayFiveIconNum = String.valueOf(response.body().getDailyForecasts().get(4).getDay().getIcon());
//                    setDayFiveIcon(dayFiveIconNum);
//
//
//
//                    int tempDayOne = (int) Math.rint(response.body().getDailyForecasts().get(0).getTemperature().getMaximum().getValue());
//                    int tempDayTwo = (int) Math.rint(response.body().getDailyForecasts().get(1).getTemperature().getMaximum().getValue());
//                    int tempDayThree = (int) Math.rint(response.body().getDailyForecasts().get(2).getTemperature().getMaximum().getValue());
//                    int tempDayFour = (int) Math.rint(response.body().getDailyForecasts().get(3).getTemperature().getMaximum().getValue());
//                    int tempDayFive = (int) Math.rint(response.body().getDailyForecasts().get(4).getTemperature().getMaximum().getValue());
//
//                    tvDayOneTemp.setText(String.valueOf(tempDayOne));
//                    tvDayTwoTemp.setText(String.valueOf(tempDayTwo));
//                    tvDayThreeTemp.setText(String.valueOf(tempDayThree));
//                    tvDayFourTemp.setText(String.valueOf(tempDayFour));
//                    tvDayFiveTemp.setText(String.valueOf(tempDayFive));





                } else {
                    Log.d(TAG, "onResponse: Fail");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AccuweatherResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void setDayOneIcon( String iconNum){
        if (iconNum.equals("1") || iconNum.equals("2") || iconNum.equals("3") || iconNum.equals("4")){
            ivDayOneIcon.setImageResource(R.drawable.sunny);
        } else if (iconNum.equals("4") || iconNum.equals("5") || iconNum.equals("6") || iconNum.equals("7") || iconNum.equals("8")) {
            ivDayOneIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (iconNum.equals("11")){
            ivDayOneIcon.setImageResource(R.drawable.mist);
        } else if (iconNum.equals("12") || iconNum.equals("13") || iconNum.equals("14") || iconNum.equals("18")){
            ivDayOneIcon.setImageResource(R.drawable.rain);
        } else if (iconNum.equals("15") || iconNum.equals("16") || iconNum.equals("17")){
            ivDayOneIcon.setImageResource(R.drawable.thunderstorm);
        } else if (iconNum.equals("19") || iconNum.equals("20") || iconNum.equals("21") || iconNum.equals("22") || iconNum.equals("23")){
            ivDayOneIcon.setImageResource(R.drawable.snow);
        } else if (iconNum.equals("24") || iconNum.equals("25") || iconNum.equals("26") || iconNum.equals("27") || iconNum.equals("28") || iconNum.equals("29")){
            ivDayOneIcon.setImageResource(R.drawable.ice);
        } else if (iconNum.equals("30")){
            ivDayOneIcon.setImageResource(R.drawable.hot);
        } else if (iconNum.equals("31")){
            ivDayOneIcon.setImageResource(R.drawable.cold);
        } else if (iconNum.equals("32")){
            ivDayOneIcon.setImageResource(R.drawable.wind);
        }
    }

    public void setDayTwoIcon( String iconNum){
        if (iconNum.equals("1") || iconNum.equals("2") || iconNum.equals("3") || iconNum.equals("4")){
            ivDayTwoIcon.setImageResource(R.drawable.sunny);
        } else if (iconNum.equals("4") || iconNum.equals("5") || iconNum.equals("6") || iconNum.equals("7") || iconNum.equals("8")) {
            ivDayTwoIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (iconNum.equals("11")){
            ivDayTwoIcon.setImageResource(R.drawable.mist);
        } else if (iconNum.equals("12") || iconNum.equals("13") || iconNum.equals("14") || iconNum.equals("18")){
            ivDayTwoIcon.setImageResource(R.drawable.rain);
        } else if (iconNum.equals("15") || iconNum.equals("16") || iconNum.equals("17")){
            ivDayTwoIcon.setImageResource(R.drawable.thunderstorm);
        } else if (iconNum.equals("19") || iconNum.equals("20") || iconNum.equals("21") || iconNum.equals("22") || iconNum.equals("23")){
            ivDayTwoIcon.setImageResource(R.drawable.snow);
        } else if (iconNum.equals("24") || iconNum.equals("25") || iconNum.equals("26") || iconNum.equals("27") || iconNum.equals("28") || iconNum.equals("29")){
            ivDayTwoIcon.setImageResource(R.drawable.ice);
        } else if (iconNum.equals("30")){
            ivDayTwoIcon.setImageResource(R.drawable.hot);
        } else if (iconNum.equals("31")){
            ivDayTwoIcon.setImageResource(R.drawable.cold);
        } else if (iconNum.equals("32")){
            ivDayTwoIcon.setImageResource(R.drawable.wind);
        }
    }

    public void setDayThreeIcon( String iconNum){
        if (iconNum.equals("1") || iconNum.equals("2") || iconNum.equals("3") || iconNum.equals("4")){
            ivDayThreeIcon.setImageResource(R.drawable.sunny);
        } else if (iconNum.equals("4") || iconNum.equals("5") || iconNum.equals("6") || iconNum.equals("7") || iconNum.equals("8")) {
            ivDayThreeIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (iconNum.equals("11")){
            ivDayThreeIcon.setImageResource(R.drawable.mist);
        } else if (iconNum.equals("12") || iconNum.equals("13") || iconNum.equals("14") || iconNum.equals("18")){
            ivDayThreeIcon.setImageResource(R.drawable.rain);
        } else if (iconNum.equals("15") || iconNum.equals("16") || iconNum.equals("17")){
            ivDayThreeIcon.setImageResource(R.drawable.thunderstorm);
        } else if (iconNum.equals("19") || iconNum.equals("20") || iconNum.equals("21") || iconNum.equals("22") || iconNum.equals("23")){
            ivDayThreeIcon.setImageResource(R.drawable.snow);
        } else if (iconNum.equals("24") || iconNum.equals("25") || iconNum.equals("26") || iconNum.equals("27") || iconNum.equals("28") || iconNum.equals("29")){
            ivDayThreeIcon.setImageResource(R.drawable.ice);
        } else if (iconNum.equals("30")){
            ivDayThreeIcon.setImageResource(R.drawable.hot);
        } else if (iconNum.equals("31")){
            ivDayThreeIcon.setImageResource(R.drawable.cold);
        } else if (iconNum.equals("32")){
            ivDayThreeIcon.setImageResource(R.drawable.wind);
        }
    }

    public void setDayFourIcon( String iconNum){
        if (iconNum.equals("1") || iconNum.equals("2") || iconNum.equals("3") || iconNum.equals("4")){
            ivDayFourIcon.setImageResource(R.drawable.sunny);
        } else if (iconNum.equals("4") || iconNum.equals("5") || iconNum.equals("6") || iconNum.equals("7") || iconNum.equals("8")) {
            ivDayFourIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (iconNum.equals("11")){
            ivDayFourIcon.setImageResource(R.drawable.mist);
        } else if (iconNum.equals("12") || iconNum.equals("13") || iconNum.equals("14") || iconNum.equals("18")){
            ivDayFourIcon.setImageResource(R.drawable.rain);
        } else if (iconNum.equals("15") || iconNum.equals("16") || iconNum.equals("17")){
            ivDayFourIcon.setImageResource(R.drawable.thunderstorm);
        } else if (iconNum.equals("19") || iconNum.equals("20") || iconNum.equals("21") || iconNum.equals("22") || iconNum.equals("23")){
            ivDayFourIcon.setImageResource(R.drawable.snow);
        } else if (iconNum.equals("24") || iconNum.equals("25") || iconNum.equals("26") || iconNum.equals("27") || iconNum.equals("28") || iconNum.equals("29")){
            ivDayFourIcon.setImageResource(R.drawable.ice);
        } else if (iconNum.equals("30")){
            ivDayFourIcon.setImageResource(R.drawable.hot);
        } else if (iconNum.equals("31")){
            ivDayFourIcon.setImageResource(R.drawable.cold);
        } else if (iconNum.equals("32")){
            ivDayFourIcon.setImageResource(R.drawable.wind);
        }
    }

    public void setDayFiveIcon( String iconNum){
        if (iconNum.equals("1") || iconNum.equals("2") || iconNum.equals("3") || iconNum.equals("4")){
            ivDayFiveIcon.setImageResource(R.drawable.sunny);
        } else if (iconNum.equals("4") || iconNum.equals("5") || iconNum.equals("6") || iconNum.equals("7") || iconNum.equals("8")) {
            ivDayFiveIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (iconNum.equals("11")){
            ivDayFiveIcon.setImageResource(R.drawable.mist);
        } else if (iconNum.equals("12") || iconNum.equals("13") || iconNum.equals("14") || iconNum.equals("18")){
            ivDayFiveIcon.setImageResource(R.drawable.rain);
        } else if (iconNum.equals("15") || iconNum.equals("16") || iconNum.equals("17")){
            ivDayFiveIcon.setImageResource(R.drawable.thunderstorm);
        } else if (iconNum.equals("19") || iconNum.equals("20") || iconNum.equals("21") || iconNum.equals("22") || iconNum.equals("23")){
            ivDayFiveIcon.setImageResource(R.drawable.snow);
        } else if (iconNum.equals("24") || iconNum.equals("25") || iconNum.equals("26") || iconNum.equals("27") || iconNum.equals("28") || iconNum.equals("29")){
            ivDayFiveIcon.setImageResource(R.drawable.ice);
        } else if (iconNum.equals("30")){
            ivDayFiveIcon.setImageResource(R.drawable.hot);
        } else if (iconNum.equals("31")){
            ivDayFiveIcon.setImageResource(R.drawable.cold);
        } else if (iconNum.equals("32")){
            ivDayFiveIcon.setImageResource(R.drawable.wind);
        }
    }
}
