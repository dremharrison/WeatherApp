package com.example.weatherapp.accuweatherpojo;

import com.google.gson.annotations.SerializedName;

public class Night{

	@SerializedName("HasPrecipitation")
	private boolean hasPrecipitation;

	@SerializedName("IconPhrase")
	private String iconPhrase;

	@SerializedName("Icon")
	private int icon;

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

	@Override
 	public String toString(){
		return 
			"Night{" + 
			"hasPrecipitation = '" + hasPrecipitation + '\'' + 
			",iconPhrase = '" + iconPhrase + '\'' + 
			",icon = '" + icon + '\'' + 
			"}";
		}
}