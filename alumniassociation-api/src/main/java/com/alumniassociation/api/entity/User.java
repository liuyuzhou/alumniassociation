package com.alumniassociation.api.entity;

import java.io.Serializable;


/**
 * 移动用户
 * @author wangyan
 * @date:   2018年11月1日 下午7:58:37
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户ID
	private String userId;
	//真实姓名
	private String realName;
	//电话号码
	private String phone;
	//密码
	transient private String password;
	
	private Integer roleNum;
	
	private String roleName;
	//图像路径
	private String headImage;
	//头像原图路径
	private String headOriginalImage;



	//图像路径
	private String headImgUrl;

	private String totalAmount;
	
	private String availableAmount;

	private String orgId;
	
	private String mpOpenId;

	private String storeId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Integer getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}
	public String getMpOpenId() {
		return mpOpenId;
	}
	public void setMpOpenId(String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}
	public String getHeadOriginalImage() {
		return headOriginalImage;
	}
	public void setHeadOriginalImage(String headOriginalImage) {
		this.headOriginalImage = headOriginalImage;
	}


    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
}
