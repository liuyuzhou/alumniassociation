package com.alumniassociation.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.alumniassociation.user.dao.UserInfoMapper;
import com.alumniassociation.user.entity.UserInfo;
import com.alumniassociation.user.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getUserInfoById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int addUserInfo(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}

	@Transactional
	@Override
	public void deleteUserInfo(Integer id) {
		userInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKey(userInfo);
	}

	@Override
	public List<UserInfo> findAll() {
		return userInfoMapper.findAll();
	}

	@Override
	public List<UserInfo> findByRealNameAndMajorAndGraduationTime(String realName, String major, String graduationTime) {
		UserInfo user = new UserInfo();
		user.setRealName(realName);
		user.setMajor(major);
		user.setGraduationTime(graduationTime);
		return userInfoMapper.findByParam(user);
	}

	@Override
	public UserInfo queryByOpenId(String openId) {
		return userInfoMapper.queryByOpenId(openId);
	}

	@Transactional
	@Override
	public void insert(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}

	@Transactional
	@Override
	public int updateById(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userInfo);
		
	}

	@Transactional
	@Override
	public void activateUser(UserInfo userInfo, Integer oUserId) {
		//更新数据
		userInfoMapper.updateByPrimaryKey(userInfo);
		//主键删除旧的用户信息
		userInfoMapper.deleteByPrimaryKey(oUserId);
	}
}
