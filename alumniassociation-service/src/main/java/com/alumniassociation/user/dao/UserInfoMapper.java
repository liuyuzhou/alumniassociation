package com.alumniassociation.user.dao;

import java.util.List;

import com.alumniassociation.user.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    /**
     * 通过微信openid搜索用户
     * @param oepnId
     * @return
     */
    UserInfo queryByOpenId(String oepnId); 
    
    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	List<UserInfo> findAll();

	List<UserInfo> findByParam(UserInfo record);
}