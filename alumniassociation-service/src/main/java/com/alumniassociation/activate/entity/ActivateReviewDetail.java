package com.alumniassociation.activate.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动回顾表
 * 
 * @author lyz
 *
 */
public class ActivateReviewDetail implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private Integer id;
	 
	private Integer activateId;
	 
	private Integer userId;
	
	private String headImg;
	
	private String nickName;
	 
	private String realName;
	 
	private String comment;
	 
	private Date createTime;

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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
