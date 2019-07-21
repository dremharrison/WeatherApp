package com.example.weatherapp.accuweatherpojo;


import com.google.gson.annotations.SerializedName;


public class Day{

	@SerializedName("HasPrecipitation")
	private boolean hasPrecipitation;

	@SerializedName("IconPhrase")
	private String iconPhrase;

	@SerializedName("Icon")
	private int icon;

	@SerializedName("PrecipitationType")
	private String precipitationType;

	@SerializedName("PrecipitationIntensity")
	private String precipitationIntensity;

	public void setHasPrecipitation(boolean hasPrecipitation){
		this.hasPrecipitation = hasPrecipitation;
	}

	public boolean isHasPrecipitation(){
		return hasPrecipitation;
	}

	public void setIconPhrase(String iconPhrase){
		this.iconPhrase = iconPhrase;
	}

	public String getIconPhrase(){
		return iconPhrase;
	}

	public void setIcon(int icon){
		this.icon = icon;
	}

	public int getIcon(){
		return icon;
	}

	public void setPrecipitationType(String precipitationType){
		this.precipitationType = precipitationType;
	}

	public String getPrecipitationType(){
		return precipitationType;
	}

	public void setPrecipitationIntensity(String precipitationIntensity){
		this.precipitationIntensity = precipitationIntensity;
	}

	public String getPrecipitationIntensity(){
		return precipitationIntensity;
	}

	@Override
 	public String toString(){
		return 
			"Day{" + 
			"hasPrecipitation = '" + hasPrecipitation + '\'' + 
			",iconPhrase = '" + iconPhrase + '\'' + 
			",icon = '" + icon + '\'' + 
			",precipitationType = '" + precipitationType + '\'' + 
			",precipitationIntensity = '" + precipitationIntensity + '\'' + 
			"}";
		}
}