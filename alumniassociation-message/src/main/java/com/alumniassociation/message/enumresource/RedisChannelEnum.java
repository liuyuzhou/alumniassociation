package com.alumniassociation.message.enumresource;

import com.alumniassociation.common.utils.EnumMessage;

/**
 * @Author: lewp
 * @Date: 2018/11/27 21:27
 */
public enum RedisChannelEnum implements EnumMessage {
	
	PHONE_VALIDATE_CODE("phoneValidateCode", "发送手机验证码"),
	GOOD_NEWS_UPDATE("goodNewsUpdate", "蚁装捷报-更新"),
	PRODUCT_UPDATE("productUpdate", "商城-更新"),
	BANNER_UPDATE("bannerUpdate", "Banner-更新"),
	ORDER_STATUS_CHANGE("orderStatusChange", "订单变更事件"),
	GOOD_NEWS("goodNews", "蚁装捷报");
	
	
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