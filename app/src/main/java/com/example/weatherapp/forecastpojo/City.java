package com.example.weatherapp.forecastpojo;


import com.google.gson.annotations.SerializedName;


public class City{

	@SerializedName("country")
	private String country;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("name")
	private String name;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCoord(Coord coord){
		this.coord = coord;
	}

	public Coord getCoord(){
		return coord;
	}

	public void setTimezone(int timezone){
		this.timezone = timezone;
	}

	public int getTimezone(){
		return timezone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"City{" + 
			"country = '" + country + '\'' + 
			",coord = '" + coord + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}