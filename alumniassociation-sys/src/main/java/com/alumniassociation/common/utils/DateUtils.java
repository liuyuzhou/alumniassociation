package com.alumniassociation.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
	
	public static String getYmdhms() {
		return SDF_YMDHMS.format(new Date());
	}

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    public static String formatYmdhms(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    /**
     * yyyyMMddHHmmss 转yyyy-MM-dd HH:mm:ss
     * @param s
     * @return
     */
    public static String dateStrToStr(String s){
    	String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
    	s = s.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
    	return s;
    }
    
    
}
