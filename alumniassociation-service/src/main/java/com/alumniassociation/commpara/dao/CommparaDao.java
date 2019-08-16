package com.alumniassociation.commpara.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.commpara.entity.Commpara;


/**
 * 字典管理
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 14:49:28
 */
public interface CommparaDao extends BaseDao<Commpara> {

	String getCodeName(Map<String, Object> params);
	
    List<Commpara> getCodeValues(Map<String, Object> params);

    List<Map<String,Object>> getIdValues(Map<String, Object> params);
    
    List<Map<String,Object>> getCodeForValues(Map<String, Object> params);
    
    List<Map<String,Object>> getOperatorList(Map<String, Object> params);

    List<Commpara> findByVerify(Commpara commpara);
    
    List<Map<String,Object>> getApiList(Map<String, Object> params);

    List<Map<String,Object>> getApiListNew(Map<String, Object> params);

    /**
     * 功能描述:
     * 根据参数名获取对应参数值
     * @param space
     * @return java.lang.Integer
     * @author zenghaohui
     * @date 19/4/2019 下午 6:12
     */
    Integer getByParaName(@Param("paraName") String space);
}
