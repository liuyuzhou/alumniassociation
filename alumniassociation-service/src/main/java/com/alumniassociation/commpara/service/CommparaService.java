package com.alumniassociation.commpara.service;

import java.util.List;
import java.util.Map;

import com.alumniassociation.commpara.entity.Commpara;

/**
 * 字典管理
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 14:49:28
 */
public interface CommparaService {
	
	Commpara queryObject(Integer paraId);
	
	List<Commpara> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Commpara commpara);
	
	void update(Commpara commpara);
	
	//通过代码CODE 和 值 获取对应的名称
	String getCodeName(String code, String value);
	
	void delete(Integer paraId);
	
	void deleteBatch(Integer[] paraIds);

    void updateState(Integer[] ids, String stateValue);

	List<Commpara> getCodeValues(Map<String, Object> params);
	
	List<Map<String,Object>> getCodeForValues(Map<String, Object> params);
	
    List<Commpara> findByVerify(Commpara commpara);

	List<Map<String, Object>> getApiList(Map<String, Object> map);

    List<Map<String,Object>> getApiListNew(Map<String, Object> map);

	List<Map<String,Object>> getIdValues(Map<String, Object> params);
}
