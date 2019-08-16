package com.alumniassociation.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限
 * 
 * @author lyz
 *
 */
public class UserRight implements Serializable{
	
	private Integer id;
	
	private Integer jurisdictionId;
	
	private String jurisdictionName;
	
	private Integer userId;
	
	private Integer authUserId;
	
	private Boolean jurStatus = Boolean.FALSE;
	
	private Date createTime;
	
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJurisdictionId() {
		return jurisdictionId;
	}

	public void setJurisdictionId(Integer jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}

	public String getJurisdictionName() {
		return jurisdictionName;
	}

	public void setJurisdictionName(String jurisdictionName) {
		this.jurisdictionName = jurisdictionName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(Integer authUserId) {
		this.authUserId = authUserId;
	}

	public Boolean getJurStatus() {
		return jurStatus;
	}

	public void setJurStatus(Boolean jurStatus) {
		this.jurStatus = jurStatus;
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
