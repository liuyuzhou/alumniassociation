package com.alumniassociation.web.common.service;

import java.util.List;
import java.util.Map;

import com.alumniassociation.web.common.entity.SysLogToDb;

/**
 * 系统日志
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService {
	
	SysLogToDb queryObject(Long id);
	
	List<SysLogToDb> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogToDb sysLog);
	
	void update(SysLogToDb sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
