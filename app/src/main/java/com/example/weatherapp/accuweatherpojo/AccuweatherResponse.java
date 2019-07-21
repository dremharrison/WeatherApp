package com.example.weatherapp.accuweatherpojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class AccuweatherResponse{

	@SerializedName("Headline")
	private Headline headline;

	@SerializedName("DailyForecasts")
	private List<DailyForecastsItem> dailyForecasts;

	public void setHeadline(Headline headline){
		this.headline = headline;
	}

	public Headline getHeadline(){
		return headline;
	}

	public void setDailyForecasts(List<DailyForecastsItem> dailyForecasts){
		this.dailyForecasts = dailyForecasts;
	}

	public List<DailyForecastsItem> getDailyForecasts(){
		return dailyForecasts;
	}

	@Override
 	public String toString(){
		return 
			"AccuweatherResponse{" + 
			"headline = '" + headline + '\'' + 
			",dailyForecasts = '" + dailyForecasts + '\'' + 
			"}";
		}
}