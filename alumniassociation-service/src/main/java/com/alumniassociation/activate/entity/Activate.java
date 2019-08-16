package com.alumniassociation.activate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activate implements Serializable {
	// 活动id
	private Integer activateId;
	// 活动标题
	private String activateTitle;
	// 用户id
	private Integer userId;
	// 用户名称
	private String userName;
	// 活动参与人列表
	private List<Map<String, Object>> activatePersonnelLists;
	// 活动评论
	private List<ActivateReviewDetail> activateReviewDetails;
	// 活动信息
	private List<ActivateInfo> activateInfos;

	public Integer getActivateId() {
		return activateId;
	}

	public void setActivateId(Integer activateId) {
		this.activateId = activateId;
	}

	public String getActivateTitle() {
		return activateTitle;
	}

	public void setActivateTitle(String activateTitle) {
		this.activateTitle = activateTitle;
	}

	public List<Map<String, Object>> getActivatePersonnelLists() {
		return activatePersonnelLists;
	}

	public void setActivatePersonnelLists(List<Map<String, Object>> activatePersonnelLists) {
		this.activatePersonnelLists = activatePersonnelLists;
	}

	public List<ActivateReviewDetail> getActivateReviewDetails() {
		return activateReviewDetails;
	}

	public void setActivateReviewDetails(List<ActivateReviewDetail> activateReviewDetails) {
		this.activateReviewDetails = activateReviewDetails;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ActivateInfo> getActivateInfos() {
		return activateInfos;
	}

	public void setActivateInfos(List<ActivateInfo> activateInfos) {
		this.activateInfos = activateInfos;
	}

}
