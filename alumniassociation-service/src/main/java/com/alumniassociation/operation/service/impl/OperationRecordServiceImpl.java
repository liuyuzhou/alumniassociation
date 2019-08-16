package com.alumniassociation.operation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alumniassociation.operation.dao.OperationRecordMapper;
import com.alumniassociation.operation.entity.OperationRecord;
import com.alumniassociation.operation.service.OperationRecordService;

@Service("operationRecordService")
public class OperationRecordServiceImpl implements OperationRecordService {
	@Resource
	private OperationRecordMapper operationRecordMapper;

	@Override
	public int addOperationRecord(OperationRecord operationRecord) {
		return operationRecordMapper.insert(operationRecord);
	}

	@Override
	public List<OperationRecord> findAll() {
		return operationRecordMapper.findAll();
	}

}
