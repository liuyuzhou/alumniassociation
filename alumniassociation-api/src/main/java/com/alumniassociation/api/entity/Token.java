package com.alumniassociation.api.entity;

import java.io.Serializable;


/**
 * 用户Token
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-03-23 15:22:07
 */
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	//电话号码
	private String telNbr;
	/**
	 * 角色 3.用户  2.销售  1.安装工
	 */
	private Integer roleNum;
	
	private int userId;
	
	/**
	 * 用户姓名
	 */
	private String realName;
	
    /**
     * 公众号openId
     */
	private String mpOpenId;

	public String getTelNbr() {
		return telNbr;
	}
	public void setTelNbr(String telNbr) {
		this.telNbr = telNbr;
	}
	public Integer getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMpOpenId() {
		return mpOpenId;
	}
	public void setMpOpenId(String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}
}
