package com.alumniassociation.web.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.SysUserRole;

/**
 * 用户与角色对应关系
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:34:46
 */
@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
