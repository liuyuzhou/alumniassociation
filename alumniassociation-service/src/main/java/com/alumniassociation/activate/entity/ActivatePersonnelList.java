package com.alumniassociation.activate.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动参与人列表
 * 
 * @author lyz
 *
 */
public class ActivatePersonnelList implements Serializable{
	
	private Integer id;
	 
	private Integer activateId;
	 
	private Integer userId;
	 
	private String realName;
	 
	private Boolean isDrive = Boolean.FALSE;
	 
	private Boolean isFamily = Boolean.FALSE;
	 
	private Boolean isManned = Boolean.FALSE;
	 
	private String isParticipate;
	
	private Date createTime;
	 
	private Date updateTime;
	
	private String boardingLocation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivateId() {
		return activateId;
	}

	public void setActivateId(Integer activateId) {
		this.activateId = activateId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Boolean getIsDrive() {
		return isDrive;
	}

	public void setIsDrive(Boolean isDrive) {
		this.isDrive = isDrive;
	}

	public Boolean getIsFamily() {
		return isFamily;
	}

	public void setIsFamily(Boolean isFamily) {
		this.isFamily = isFamily;
	}

	public Boolean getIsManned() {
		return isManned;
	}

	public void setIsManned(Boolean isManned) {
		this.isManned = isManned;
	}

	public String getIsParticipate() {
		return isParticipate;
	}

	public void setIsParticipate(String isParticipate) {
		this.isParticipate = isParticipate;
	}

	public String getBoardingLocation() {
		return boardingLocation;
	}

	public void setBoardingLocation(String boardingLocation) {
		this.boardingLocation = boardingLocation;
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
