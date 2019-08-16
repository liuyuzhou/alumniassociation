package com.alumniassociation.user.service;


import java.util.List;

import com.alumniassociation.user.entity.UserInfo;


public interface UserInfoService {

	public void insert(UserInfo userInfo);
	
	public UserInfo getUserInfoById(Integer id);
	
	public UserInfo queryByOpenId(String openId);

	public int addUserInfo(UserInfo userInfo);

	public void deleteUserInfo(Integer id);

	public void updateUserInfo(UserInfo userInfo);
	
	public int updateById(UserInfo userInfo);
	
	public void activateUser(UserInfo userInfo, Integer oUserId);

	public List<UserInfo> findAll();

	public List<UserInfo> findByRealNameAndMajorAndGraduationTime(String realName, String major, String graduationTime);
}
