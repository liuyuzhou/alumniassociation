package com.alumniassociation.activate.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动信息表
 * 
 * @author lyz
 *
 */
public class ActivateInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer activateId;
	
	private String title;
	
	private String address;
	
	private String meetingPlace;
	
	private String activateImage;
	
	private Date actTime;
	
	private Date endTime;
	
	private String actCreator;
	
	private String content;
	
	private Integer participateNum = Integer.valueOf(0);
	
	private Double cost = Double.valueOf(0);
	
	private Date createTime;
	
	private Date updateTime;
	
	private String status;

	public Integer getActivateId() {
		return activateId;
	}

	public void setActivateId(Integer activateId) {
		this.activateId = activateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivateImage() {
		return activateImage;
	}

	public void setActivateImage(String activateImage) {
		this.activateImage = activateImage;
	}

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getActCreator() {
		return actCreator;
	}

	public void setActCreator(String actCreator) {
		this.actCreator = actCreator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(Integer participateNum) {
		this.participateNum = participateNum;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}
	

}
