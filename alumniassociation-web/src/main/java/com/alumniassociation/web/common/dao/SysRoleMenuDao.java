package com.alumniassociation.web.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.SysRoleMenu;

/**
 * 角色与菜单对应关系
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
