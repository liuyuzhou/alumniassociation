package com.alumniassociation.api.entity;

/**
 * @author wangyan
 * @date:   2018年11月21日 下午5:45:57 
 */
public class LoginTokenInfo {
	
	private String appToken = "";
	
	private String mpToken = "";
	
	private String minappToken = "";
	
	private String alias = "";

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getMpToken() {
		return mpToken;
	}

	public void setMpToken(String mpToken) {
		this.mpToken = mpToken;
	}

	public String getMinappToken() {
		return minappToken;
	}

	public void setMinappToken(String minappToken) {
		this.minappToken = minappToken;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	

}
