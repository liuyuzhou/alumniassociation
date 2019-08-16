package com.alumniassociation.message.enumresource;

public enum WxMsgTemplateEnum {
	    REGISTER("ZFMEcUqU1BUA5uLqr-EDLo1_TbcqlSmop47wWcr1qcc","注册成功通知",""),
	    ORDER_REMINDER("NaJvm9b1JzzDDE34V3Cele-AF7UJsQgi-lNmjB9jc0Y","新订单提醒",""),
	    ORDER_PAY("1WFtJiILuI_lJGkFIyX7ReVtnduocCpVAb1lqbX1Xp4","交易完成通知",""),
        REFUND("eOA3TH9soGs9g9xitPV5UDy-MTlMJ4yBfzI6AnZdujE","退款通知",""),
        INSTALL_EVALUATE("7POW3exolPhw1Yevj-Q3XOmjj-pkbEfmr360ezMX2hc","安装评价提醒","");
	    private final String code;
	    private final String value;
	    private final String url;
	    private WxMsgTemplateEnum(String code, String value,String url) {
	        this.code = code;
	        this.value = value;
	        this.url = url;
	    }
	    public String getCode() {return code; }
	    public String getValue() { return value; }
	    public String getUrl() { return url; }

	
}
