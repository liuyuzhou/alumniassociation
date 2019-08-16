package com.alumniassociation.activate.service;

import java.util.List;

import com.alumniassociation.activate.entity.ActivateReviewDetail;

public interface ActivateReviewDetailService {
	public int addActivateReview(ActivateReviewDetail activateReviewDetail);

	public List<ActivateReviewDetail> findByActivateId(Integer activateId);

}
