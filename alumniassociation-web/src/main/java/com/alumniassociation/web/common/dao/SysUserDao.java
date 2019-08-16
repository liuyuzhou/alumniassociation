package com.alumniassociation.web.common.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.SysUser;

/**
 * 系统用户
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

    void deleteUserRole(Long[] userId);

    List<SysUser> queryWorksList(Map<String,Object> map);

    int queryWorksCount(Map<String, Object> map);
    
    /**
     * 批量更新用户状态
     * @param map
     */
    void updateStatus(Map<String, Object> map);

}
