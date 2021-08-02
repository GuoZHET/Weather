package com.example.weather;

import com.google.gson.Gson;

public class GsonUtil {
    public static WeatherBean getBean(String response){
        try {
            return new Gson().fromJson(response,WeatherBean.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
