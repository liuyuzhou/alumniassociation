package com.alumniassociation.web.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alumniassociation.web.common.entity.Area;

/**
 * 
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-08-10 16:00:04
 */
public interface AreaService {

	Area queryObject(String areaId);

	List<Area> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(Area area);

	void update(Area area);

	void delete(String areaId);

	void deleteBatch(String[] areaIds);

	List<Area> getAreaListByIsShow(HashMap<String, Object> paraMap);
	
	List<Area> getChildrenListByPid(int pid);

	int getCount(Map<String, Object> params);

	void updateState(String[] ids, String stateValue);

	String getAreaNameStr(String area_id_str);
}
