package com.alumniassociation.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * 
 * @author lyz
 *
 */
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**用户ID **/
	private Integer id;
	
	/**真实姓名 **/
	private String realName;
	
	/**用户昵称 **/
	private String nickName;
	
	/**用户生日 **/
	private Date birthday;
	
	/**用户性别 **/
	private Integer sex;
	
	/**用户电话号码 **/
	private Long phoneNum;
	
	/**入学时间**/
	private String entranceTime;
	
	/**毕业时间 **/
	private String graduationTime;
	
	/**学院**/
	private String college;
	
	/**专业 **/
	private String major;
	
	/**当前所在地 **/
	private String currLocation;
	
	/**行业技能 **/
	private String industrySkill;
	
	/**兴趣爱好 **/
	private String hobby;
	
	/**个人说明 **/
	private String personalProfile;
	
	private Boolean userStatus = Boolean.FALSE;
	
	/**创建时间 **/
	private Date createTime;
	
	/**信息更新时间 **/
	private Date updateTime;
	
	/**微信头像 **/
	private String wechatImage;
	
	/**微信OPEN ID **/
	private String openId;
	
	private Double score = Double.valueOf(0);
	/**微信登陆会话key **/
	private String sessionKey;
	/**最后一次登陆时间 **/
	private Date lastLoginTime;
	/**最后一次登陆IP**/
	private String lastLoginIp;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(String entranceTime) {
		this.entranceTime = entranceTime;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}

	public String getIndustrySkill() {
		return industrySkill;
	}

	public void setIndustrySkill(String industrySkill) {
		this.industrySkill = industrySkill;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
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

	public String getWechatImage() {
		return wechatImage;
	}

	public void setWechatImage(String wechatImage) {
		this.wechatImage = wechatImage;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

}
