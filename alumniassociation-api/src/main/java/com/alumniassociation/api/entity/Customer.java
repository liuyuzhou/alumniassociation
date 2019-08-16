package com.alumniassociation.api.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2018-10-11 14:34:47
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/****/
	private String customerId;
	/****/
	private String openId;
	/**电话号码**/
	private String phone;
	//密码
	transient private String password;
	/****/
	private String nickName;
	/****/
	private String realName;
	
	private String headImage;
	/**地址**/
	private String address;
	/**性别**/
	private String sex;
	/**国家**/
	private String country;
	/**省份**/
	private String province;
	/**城市**/
	private String city;
	/**用户注册日期**/
	private Date registerTime;

	/**
	 * 设置：
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 获取：
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：国家
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省份
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	
}
