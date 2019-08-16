package com.alumniassociation.web.common.service;

import java.util.List;
import java.util.Map;

import com.alumniassociation.web.common.entity.File;

/**
 * 地产附件表
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-17 11:52:01
 */
public interface FileService {
	
	File get(String id);
	
	List<File> getList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(File file);
	
	void update(File file);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);

    void updateState(String[] ids, String stateValue);

    List<File> getByRelationId(String relationId);

    List<File> getFileList(String imgUUID);

	List<File> getFileListByUUID(List<String> uuidList);

    void deleteByRelationId(String relationId);
}
