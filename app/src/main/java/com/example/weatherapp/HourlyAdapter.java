package com.example.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.forecastpojo.ForecastResponse;
import com.example.weatherapp.forecastpojo.ListItem;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private static final String TAG = "HourlyAdapters";

    private Context context;
    List<ListItem> listItems;

    public HourlyAdapter(List<ListItem> listItems) {
        this.listItems = listItems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hourly_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem hourly = listItems.get(position);
        holder.tvHourlyDate.setText(String.valueOf(new Date(hourly.getDt()  * 1000L)).substring(0,3));



        String milTime = String.valueOf(new Date(hourly.getDt() * 1000L)).substring(11,16);
        String hour = milTime.substring(0,2);
        holder.tvHourlyTime.setText(milTime);

        // Get Weather Description to set Icon
        String weatherDescription = hourly.getWeather().get(0).getDescription();

        // Set Icon
        if (weatherDescription.equals("clear sky")) {
            holder.ivHourlyIcon.setImageResource(R.drawable.sunny);
        } else if (weatherDescription.equals("few clouds") || weatherDescription.equals("scattered clouds") || weatherDescription.equals("broken clouds")) {
            holder.ivHourlyIcon.setImageResource(R.drawable.partly_cloudy);
        } else if (weatherDescription.equals("shower rain") || weatherDescription.equals("rain")){
            holder.ivHourlyIcon.setImageResource(R.drawable.rain);
        } else if (weatherDescription.equals("thunderstorm")){
            holder.ivHourlyIcon.setImageResource(R.drawable.thunderstorm);
        } else if (weatherDescription.equals("snow")){
            holder.ivHourlyIcon.setImageResource(R.drawable.snow);
        } else if (weatherDescription.equals("mist")){
            holder.ivHourlyIcon.setImageResource(R.drawable.mist);
        }

        // Get Temp
        Double tempDouble = hourly.getMain().getTemp();
        int temp = (int) Math.rint(tempDouble);
        holder.tvHourlyTemp.setText(temp + "º");


    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_hourly_icon) ImageView ivHourlyIcon;
        @BindView(R.id.tv_hourly_date) TextView tvHourlyDate;
        @BindView(R.id.tv_hourly_time) TextView tvHourlyTime;
        @BindView(R.id.tv_hourly_temp) TextView tvHourlyTemp;
//        ImageView ivHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.findViewById(R.id.iv_hourly_icon);
        }
    }
}
