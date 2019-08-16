package com.alumniassociation.authentication.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业认证表
 * 
 * @author lyz
 *
 */
public class CompanyAuthenticate implements Serializable {
	
	private Integer id;
	
	private Integer userId;
	
	private String companyName;
	
	private String industry;
	
	private String logo;
	
	private String briefIntroduction;
	
	private String otherInfo;
	
	private Boolean authStatus = Boolean.FALSE;
	
	private Date createTime;
	 
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public Boolean getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Boolean authStatus) {
		this.authStatus = authStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
