package com.alumniassociation.web.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.SysMenu;

/**
 * 菜单管理
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends BaseDao<SysMenu> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> queryUserList(Long userId);

    List<SysMenu> findByParentId(Long parentId);

    void deleteRoleMenu(Long[] menuIds);
}
