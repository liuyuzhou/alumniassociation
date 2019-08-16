package com.alumniassociation.web.common.dao;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.Organize;

import java.util.List;
import java.util.Map;

/**
 * 记录组织机构管理信息
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 17:39:31
 */
public interface OrganizeDao extends BaseDao<Organize> {

    List<Organize> getList();

    List<Organize> queryByParentId(String orgId);

    Organize queryByOrgCode(String orgCode);
    
    String queryChildByParentId(String orgId);
    
    /**
     * 保存运营商
     */
    void saveOperator(Organize o);
    
    /**
     * 保存工厂
     */
    void saveFactory(Organize o);

    /**
     * 保存仓库
     */
    void saveRespository(Organize o);

    /**
     * 修改运营商
     */
    void updateOperator(Organize o);
    
    /**
     * 修改工厂
     */
    void updateFactory(Organize o);

    /**
     * 修改仓库
     * @param o
     */
    void updateRespository(Organize o);

    List<Organize> selectList(Map<String, Object> map);
    
    List<Organize> queryListByBean(Organize organEntity);
}
