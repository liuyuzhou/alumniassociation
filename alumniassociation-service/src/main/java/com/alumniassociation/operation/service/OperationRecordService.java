package com.alumniassociation.operation.service;

import java.util.List;

import com.alumniassociation.operation.entity.OperationRecord;


public interface OperationRecordService {
	public int addOperationRecord(OperationRecord operationRecord);

	public List<OperationRecord> findAll();
}
