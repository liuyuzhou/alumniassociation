package com.alumniassociation.web.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.Area;

/**
 * 
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-08-11 10:52:35
 */
public interface AreaDao extends BaseDao<Area> {

    List<Area> getAreaListByIsShow(HashMap<String, Object> paraMap);
    
    List<Area> getChildrenListByPid(int pid);

    int getCount(Map<String, Object> params);

    List<Area> findByParentId(String pId);
}
