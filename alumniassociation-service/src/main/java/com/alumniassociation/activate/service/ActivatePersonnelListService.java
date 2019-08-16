package com.alumniassociation.activate.service;

import java.util.List;
import java.util.Map;

import com.alumniassociation.activate.entity.ActivatePersonnelList;

public interface ActivatePersonnelListService {
	public int addActivatePersonnelList(ActivatePersonnelList activatePersonnelList);

	public void updateActivatePersonnelList(ActivatePersonnelList activatePersonnelList);

	public List<ActivatePersonnelList> findByActivateId(Integer activateId);
	
	public int cacelJoinedActivate(ActivatePersonnelList activatePersonnelList);
	
	/**
	 * 根据获取ID获取活动参与人列表
	 * @param activateId
	 * @return
	 */
	public List<Map<String, Object>> getJoinedPersonnelListByActivateId(Integer activateId);
	
	public List<ActivatePersonnelList> findByUserId(Integer userId);
}
