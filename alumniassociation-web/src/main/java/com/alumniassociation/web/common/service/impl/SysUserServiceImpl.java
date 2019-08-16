package com.alumniassociation.web.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alumniassociation.common.enumresource.DefaultEnum;
import com.alumniassociation.common.exception.MyException;
import com.alumniassociation.common.utils.Constant;
import com.alumniassociation.web.common.dao.SysUserDao;
import com.alumniassociation.web.common.entity.SysUser;
import com.alumniassociation.web.common.service.SysRoleService;
import com.alumniassociation.web.common.service.SysUserRoleService;
import com.alumniassociation.web.common.service.SysUserService;


/**
 * 系统用户
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUser queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}
	
	@Override
	public SysUser queryObject(Long userId) {
		return sysUserDao.queryObject(userId);
	}

	@Override
	public List<SysUser> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUser user) {
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(DefaultEnum.PASSWORD.getCode()).toHex());
		sysUserDao.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUser user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserDao.update(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserDao.deleteBatch(userId);
		sysUserDao.deleteUserRole(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}

	@Override
	public void initPassword(Long[] userIds) {
		for (long userId:userIds) {
			SysUser user=queryObject(userId);
			user.setPassword(new Sha256Hash(DefaultEnum.PASSWORD.getCode()).toHex());
			sysUserDao.update(user);
		}
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUser user){
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new MyException("新增用户所选角色，不是本人创建");
		}
	}

    @Override
    public List<SysUser> queryWorksList(Map<String,Object> map) {
        return sysUserDao.queryWorksList(map);
    }

    @Override
    public int queryWorksCount(Map<String, Object> map) {
        return sysUserDao.queryWorksCount(map);
    }

	@Override
	public void updateStatus(String[] userIds, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("userIds", userIds);
		map.put("status", status);
		sysUserDao.updateStatus(map);
	}

}
