package com.example.weatherapp.accuweatherpojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class DailyForecastsItem{

	@SerializedName("Temperature")
	private Temperature temperature;

	@SerializedName("Night")
	private Night night;

	@SerializedName("EpochDate")
	private int epochDate;

	@SerializedName("Day")
	private Day day;

	@SerializedName("Sources")
	private List<String> sources;

	@SerializedName("Date")
	private String date;

	@SerializedName("Link")
	private String link;

	@SerializedName("MobileLink")
	private String mobileLink;

	public void setTemperature(Temperature temperature){
		this.temperature = temperature;
	}

	public Temperature getTemperature(){
		return temperature;
	}

	public void setNight(Night night){
		this.night = night;
	}

	public Night getNight(){
		return night;
	}

	public void setEpochDate(int epochDate){
		this.epochDate = epochDate;
	}

	public int getEpochDate(){
		return epochDate;
	}

	public void setDay(Day day){
		this.day = day;
	}

	public Day getDay(){
		return day;
	}

	public void setSources(List<String> sources){
		this.sources = sources;
	}

	public List<String> getSources(){
		return sources;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setMobileLink(String mobileLink){
		this.mobileLink = mobileLink;
	}

	public String getMobileLink(){
		return mobileLink;
	}

	@Override
 	public String toString(){
		return 
			"DailyForecastsItem{" + 
			"temperature = '" + temperature + '\'' + 
			",night = '" + night + '\'' + 
			",epochDate = '" + epochDate + '\'' + 
			",day = '" + day + '\'' + 
			",sources = '" + sources + '\'' + 
			",date = '" + date + '\'' + 
			",link = '" + link + '\'' + 
			",mobileLink = '" + mobileLink + '\'' + 
			"}";
		}
}