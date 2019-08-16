package com.alumniassociation.activate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alumniassociation.activate.dao.ActivateReviewDetailMapper;
import com.alumniassociation.activate.entity.ActivateReviewDetail;
import com.alumniassociation.activate.service.ActivateReviewDetailService;

@Service("activateReviewDetailService")
public class ActivateReviewDetailServiceImpl implements ActivateReviewDetailService {
	@Resource
	private ActivateReviewDetailMapper activateReviewDetailMapper;

	@Override
	public int addActivateReview(ActivateReviewDetail activateReviewDetail) {
		return activateReviewDetailMapper.insert(activateReviewDetail);
	}

	@Override
	public List<ActivateReviewDetail> findByActivateId(Integer activateId) {
		return activateReviewDetailMapper.findByActivateId(activateId);
	}

}
