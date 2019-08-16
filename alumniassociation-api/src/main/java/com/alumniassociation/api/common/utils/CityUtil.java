package com.alumniassociation.api.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CityUtil {

	 // 根据经纬度获取所在城市
     public static String getCity(String longitude,String latitude) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL getUrl = new URL("http://api.map.baidu.com/geocoder?output=json&location="+latitude+",%20"+longitude+"&key=8xKgbwWkfc4Q4eGnRHyXTqNDoIbpySg3");
            connection= (HttpURLConnection) getUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line =reader.readLine()) !=null) {
                builder.append(line);
            }
            JSONArray newArray = JSON.parseArray("["+builder.toString()+"]");
            JSONObject obj = newArray.getJSONObject(0);
            JSONObject result = obj.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String city =(String)addressComponent.get("city");
            return city;
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            }catch(IOException e) {
                e.printStackTrace();
            }finally{
                connection.disconnect();
            }
        }
        return null;
    }
     
     public static void main(String args[]) {
    	 System.out.println(getCity("116.331398", "39.897445"));
     }

}
