package com.alumniassociation.activate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.alumniassociation.activate.dao.ActivatePersonnelListMapper;
import com.alumniassociation.activate.entity.ActivateInfo;
import com.alumniassociation.activate.entity.ActivatePersonnelList;
import com.alumniassociation.activate.service.ActivateInfoService;
import com.alumniassociation.activate.service.ActivatePersonnelListService;
import com.alumniassociation.common.exception.MyException;

@Service("activatePersonnelListService")
public class ActivatePersonnelListServiceImpl implements ActivatePersonnelListService {
	@Resource
	private ActivatePersonnelListMapper activatePersonnelListMapper;

	@Resource
	private ActivateInfoService activateInfoService;

	/**
	 * 
	 * 活动报名
	 */
	@Transactional
	@Override
	public int addActivatePersonnelList(ActivatePersonnelList activatePersonnelList) {
		int resultCode = 0;
		ActivateInfo activateInfo = activateInfoService.getActivateInfoById(activatePersonnelList.getActivateId(), activatePersonnelList.getUserId());
		if (activateInfo.getParticipateNum() != null
				&& activateInfo.getCurParticipateNum() >= activateInfo.getParticipateNum()) {// 满员
			throw new MyException("该活动参与人员名额已满。请及时关注我们公众号最新活动讯息，下次更多精彩活动期待您的参与！");
		}
		// 判断报名条件是否满足
		ActivatePersonnelList ap1 = new ActivatePersonnelList();
		ap1.setActivateId(activatePersonnelList.getActivateId());
		ap1.setUserId(activatePersonnelList.getUserId());
		List<ActivatePersonnelList> apList1 = activatePersonnelListMapper.selectByParam(ap1);
		if (apList1 == null || apList1.size() == 0) {
			// 写入报名人员列表
			resultCode = activatePersonnelListMapper.insert(activatePersonnelList);
		} else {
			if ("0".equals(apList1.get(0).getIsParticipate())) {// 存在取消记录，修改为正常报名状态
				resultCode = activatePersonnelListMapper.updateByPrimaryKey(activatePersonnelList);
			}
			throw new MyException("已报名，无需重复报名");
		}
		if (resultCode == 1) {// 更新报名人数
			activateInfoService.addParticipateNum(activateInfo.getActivateId());
		}
		return resultCode;
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
		return activatePersonnelListMapper.findByUserId(userId);
	}

	@Override
	public List<Map<String, Object>> getJoinedPersonnelListByActivateId(Integer activateId) {
		return activatePersonnelListMapper.getJoinedPersonnelListByActivateId(activateId);
	}

	@Transactional
	@Override
	public int cacelJoinedActivate(ActivatePersonnelList activatePersonnelList) {
		int resultCode = activatePersonnelListMapper.cacelJoinedActivate(activatePersonnelList);
		if(resultCode == 1){
			activateInfoService.removeParticipateNum(activatePersonnelList.getActivateId());
		}
		return resultCode;
	}

}
