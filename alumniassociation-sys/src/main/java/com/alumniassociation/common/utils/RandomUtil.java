package com.alumniassociation.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类
 * Created by Eastern unbeaten on 2016/1/25 10:36.
 */
public interface RandomUtil {
    /**
     * 获取随机数
     *
     * @param count 多少位
     * @return
     */
    public static String randomString(int count) {
        return generateString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", count);
    }

    /**
     * 获取随机数字
     *
     * @param count
     * @return
     */
    public static String randomNum(int count) {
        return generateString("0123456789", count);
    }

    /**
     * 获取UUID,处理特殊符号
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String getIndentNo() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date()) + randomNum(5);
    }

    public static String generateString(String str, int count) {
        StringBuffer sValue = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sValue.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sValue.toString();
    }
}
