package com.alumniassociation.activate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alumniassociation.activate.dao.ActivateInfoMapper;
import com.alumniassociation.activate.entity.ActivateInfo;
import com.alumniassociation.activate.service.ActivateInfoService;

@Service("activateInfoService")
public class ActivateInfoServiceImpl implements ActivateInfoService {
	
	@Resource
	private ActivateInfoMapper activateInfoMapper;

	@Override
	public int addActivateInfo(ActivateInfo activateInfo) {
		return activateInfoMapper.insert(activateInfo);
	}

	@Override
	public ActivateInfo getActivateInfoById(Integer id) {
		return activateInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateActivate(ActivateInfo activateInfo) {
		activateInfoMapper.updateByPrimaryKey(activateInfo);
	}

	@Override
	public void deleteActivateInfo(Integer id) {
		activateInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ActivateInfo> findAll() {
		return activateInfoMapper.findAll();
	}

	@Override
	public void addParticipateNum(Integer id) {
	    activateInfoMapper.addParticipateNumByKey(id);
	}

	@Override
	public void removeParticipateNum(Integer id) {
		activateInfoMapper.removeParticipateNumByKey(id);
	}

}
