package com.alumniassociation.activate.dao;

import java.util.List;

import com.alumniassociation.activate.entity.ActivateReviewDetail;

public interface ActivateReviewDetailMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ActivateReviewDetail record);

    int insertSelective(ActivateReviewDetail record);

    ActivateReviewDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivateReviewDetail record);

    int updateByPrimaryKeyWithBLOBs(ActivateReviewDetail record);

    int updateByPrimaryKey(ActivateReviewDetail record);

	List<ActivateReviewDetail> findByActivateId(Integer activateId);
}