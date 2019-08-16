package com.alumniassociation.activate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alumniassociation.activate.dao.ActivatePersonnelListMapper;
import com.alumniassociation.activate.entity.ActivatePersonnelList;
import com.alumniassociation.activate.service.ActivatePersonnelListService;

@Service("activatePersonnelListService")
public class ActivatePersonnelListServiceImpl implements ActivatePersonnelListService {
	@Resource
	private ActivatePersonnelListMapper activatePersonnelListMapper;

	@Override
	public int addActivatePersonnelList(ActivatePersonnelList activatePersonnelList) {
		return activatePersonnelListMapper.insert(activatePersonnelList);
	}

	@Override
	public void updateActivatePersonnelList(ActivatePersonnelList activatePersonnelList) {
		activatePersonnelListMapper.updateByPrimaryKey(activatePersonnelList);
	}

	@Override
	public List<ActivatePersonnelList> findByActivateId(Integer activateId) {
		return activatePersonnelListMapper.findByActivateId(activateId);
	}

	@Override
	public List<ActivatePersonnelList> findByUserId(Integer userId) {
		return activatePersonnelListMapper.findByUserId(userId) ;
	}

	@Override
	public List<Map<String, Object>> getJoinedPersonnelListByActivateId(Integer activateId) {
		return activatePersonnelListMapper.getJoinedPersonnelListByActivateId(activateId);
	}

	@Override
	public int cacelJoinedActivate(ActivatePersonnelList activatePersonnelList) {
		return activatePersonnelListMapper.cacelJoinedActivate(activatePersonnelList);
	} 

}
