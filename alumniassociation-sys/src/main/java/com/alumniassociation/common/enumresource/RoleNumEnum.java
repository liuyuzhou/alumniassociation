package com.alumniassociation.common.enumresource;

/**
 * @Author: lewp
 * @Date: 2019/4/15 21:27
 */
public enum RoleNumEnum  {
	ADMIN(0,"管理员"),
    USER(1,"用户");
    private final int code;
    private final String value;
    private RoleNumEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
    public int getCode() { return code;}
    public String getValue() { return value; }
}