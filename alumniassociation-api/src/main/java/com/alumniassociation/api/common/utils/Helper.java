package com.alumniassociation.api.common.utils;

public class Helper {
	
    public static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }

    public static String encode(String secretKey, String str){
        String enStr = "";
        try {
            enStr = AESUtil.encrypt(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return enStr;
    }

    public static String decode(String secretKey, String str) {
        String deStr = "";
        try {
            deStr = AESUtils.decrypt(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deStr;
    }
    
}