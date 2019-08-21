package com.alumniassociation.activate.dao;

import java.util.List;
import java.util.Map;

import com.alumniassociation.activate.entity.ActivatePersonnelList;

public interface ActivatePersonnelListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivatePersonnelList record);

    int insertSelective(ActivatePersonnelList record);

    ActivatePersonnelList selectByPrimaryKey(Integer id);
    
    List<ActivatePersonnelList> selectByParam(ActivatePersonnelList record);

    int updateByPrimaryKeySelective(ActivatePersonnelList record);
    
    int cacelJoinedActivate(ActivatePersonnelList record);

    int updateByPrimaryKey(ActivatePersonnelList record);

	List<ActivatePersonnelList> findByActivateId(Integer activateId);
	
	List<ActivatePersonnelList> findByUserId(Integer userId);
	
	List<Map<String, Object>> getJoinedPersonnelListByActivateId(Integer activateId);
}