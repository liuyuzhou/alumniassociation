package com.alumniassociation.web.common.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alumniassociation.web.common.entity.SysUser;
import com.alumniassociation.web.common.shiro.ShiroUtils;

/**
 * Controller公共组件
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUser getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
