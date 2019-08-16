package com.alumniassociation.common.enumresource;

import com.alumniassociation.common.utils.EnumMessage;

/**
 * @Author: lewp
 * @Date: 2018/11/27 21:27
 */
public enum RedisChannelEnum implements EnumMessage {
	
	PHONE_VALIDATE_CODE("phoneValidateCode", "发送手机验证码"),
	NEWS_UPDATE("newsUpdate", "同步新闻"),
	NEWS_READ_SYCH("newsReadSych", "同步阅读数");
	
    private final String code;
    private final String value;
    
    
    
    private RedisChannelEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}