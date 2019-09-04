package com.alumniassociation.activate.dao;

import java.util.List;
import java.util.Map;

import com.alumniassociation.activate.entity.ActivateInfo;

public interface ActivateInfoMapper{
    int deleteByPrimaryKey(Integer activateId);

    int insert(ActivateInfo record);

    int insertSelective(ActivateInfo record);

    ActivateInfo selectByPrimaryKey(Integer activateId);

    int updateByPrimaryKeySelective(ActivateInfo record);

    int updateByPrimaryKey(ActivateInfo record);

	List<ActivateInfo> findAll();
	
	ActivateInfo selectByUserId(Map<String, Object> paraMap);
	
	void addParticipateNumByKey(Integer id);
	
	void removeParticipateNumByKey(Integer id);
}