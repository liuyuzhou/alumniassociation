package com.alumniassociation.common.enumresource;

import com.alumniassociation.common.utils.EnumMessage;

/**
 * @Author: chenyi
 * @Email: 228112142@qq.com
 * @Description: 默认密码
 * @Date: 2017/9/2 21:27
 */
public enum NewsTypeEnum implements EnumMessage {
	
    LATEST_NEWS("1","最新动态"),
    FURNITURE_INFO_NEWS("2","优惠活动"),
    INLINE_NEWS("3","行内新闻");
    private final String code;
    private final String value;
    private NewsTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}