package com.alumniassociation.activate.service;

import java.util.List;

import com.alumniassociation.activate.entity.ActivateInfo;

public interface ActivateInfoService {
	public int addActivateInfo(ActivateInfo activateInfo);

	public ActivateInfo getActivateInfoById(Integer id, Integer userId);

	public void updateActivate(ActivateInfo activateInfo);
	
	public void addParticipateNum(Integer id);
	
	public void removeParticipateNum(Integer id);

	public void deleteActivateInfo(Integer id);

	public List<ActivateInfo> findAll();

}
