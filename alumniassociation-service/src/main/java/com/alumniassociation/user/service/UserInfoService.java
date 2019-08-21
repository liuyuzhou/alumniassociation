package com.alumniassociation.user.service;


import java.util.List;

import com.alumniassociation.common.utils.Query;
import com.alumniassociation.user.entity.UserInfo;


public interface UserInfoService {

	
	public List<UserInfo> getList(Query query);
	
	public int getCount(Query query);
	
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
