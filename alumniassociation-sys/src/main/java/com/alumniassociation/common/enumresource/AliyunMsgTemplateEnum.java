package com.alumniassociation.common.enumresource;

public enum AliyunMsgTemplateEnum {

	    CODE(0,"验证码发送"),
	    ORDER_RULER(1,"上门量尺通知"),
	    QUOTATION(2,"发送报价单"),
	    AFTER_PAY(3,"支付完成通知"),
	    INSTALL(4,"上门安装通知"),
	    USER_AFTER_SALE(5,"用户售后服务通知"),
	    DO_SALE(6,"处理售后问题的通知"),
	    FINISHED_INSTALL_CODE(7,"发送安装完成确认码到业主手机"),
	    INSTALL_INCOME_CODE(8,"安装单入账通知"),
	    SALE_NEW_ORDER(9,"新订单通知销售"),
	    USER_NEW_RULER(10,"量尺时间更改用户通知"),
	    SALE_AFTER_PAY(11,"支付成功销售通知"),
	    USER_CONTRACT_SIGN(12,"签订合同后用户通知"),
	    USER_FINISHED_INSTALL(13,"安装完成用户通知"),
	    BOSS_CONTRACT_SIGN(14,"销售单合同签订后BOSS通知");
	    private final int code;
	    private final String value;
	    private AliyunMsgTemplateEnum(int code, String value) {
	        this.code = code;
	        this.value = value;
	    }
	    public int getCode() { return code;}
	    public String getValue() { return value; }

	
}
