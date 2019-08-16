package com.alumniassociation.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AdressUtils {
	
	private final static String ak = "5cftuaK1kG5jihp8k1WgFhMq9KcGvU1p";

    public static Map<String,Object> getLarLng(String address) throws Exception {
        if(address == null || "".equals(address)){
        	throw new RuntimeException("传入地址参数address不能为空");
        }
        address = address.replaceAll(" ", "%20");
        String addressUrl = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + ak + "&callback=showLocation";

        URL url = new URL(addressUrl);

        InputStream inputStream = url.openStream();

        String string = IOUtils.toString(inputStream,"UTF-8");

        int len = string.length();

        String substring = string.substring(27, len - 1);

        JSONObject jsonObject = JSONObject.parseObject(substring);

        String status = jsonObject.getString("status");

        Double lng = 0.0;

        Double lat = 0.0;

        if (status.equals("0")) {

            lng = jsonObject.getJSONObject("result").getJSONObject("location").getDouble("lng");

            lat = jsonObject.getJSONObject("result").getJSONObject("location").getDouble("lat");
        }

        if (Double.isNaN(lng)) {
            System.out.println(0);
        }
        BigDecimal bd_lng = new BigDecimal(lng);

        double v_lng = bd_lng.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal bd_lat = new BigDecimal(lat);

        double v_lat = bd_lat.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        Map<String,Object> map = new HashMap<>();
        map.put("lng",v_lng);
        map.put("lat",v_lat);
        return map;
    }
    
    
    public static void main(String args[]) throws Exception{
    	getLarLng("佛山万达 物业");
    }
}
