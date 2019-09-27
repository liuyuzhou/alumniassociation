package com.alumniassociation.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信用户信息
 * 
 * @author lewp
 *
 */
@Data
@Accessors(chain = true)
public class UserInfo {

	/** 用户昵称 **/
	private String nickName;

	/** 用户头像地址 **/
	private String avatarUrl;

	/** 国家 **/
	private String country;

	/** 省份 **/
	private String province;

	/** 城市 **/
	private String city;

	/** 语言 **/
	private String language;

	/** 性别 **/
	private Byte gender;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

}
