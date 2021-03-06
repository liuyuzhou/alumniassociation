package com.alumniassociation.web.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.SysRole;

/**
 * 角色管理
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:33:33
 */
//@Repository
public interface SysRoleDao extends BaseDao<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

    List<SysRole> findAll(Map<String, Object> params);
    
    String queryOrgId(Long roleId);

    void deleteRoleMenu(Long[] roleIds);

	void deleteUserRole(Long[] roleIds);
}
