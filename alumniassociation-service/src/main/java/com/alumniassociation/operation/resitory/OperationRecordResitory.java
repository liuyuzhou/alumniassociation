package com.alumniassociation.operation.resitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumniassociation.operation.entity.OperationRecord;

public interface OperationRecordResitory extends JpaRepository<OperationRecord, Integer> {

}
