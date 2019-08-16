package com.alumniassociation.api.controller;

import com.alumniassociation.api.common.api.interceptor.AuthorizationInterceptor;
import com.alumniassociation.api.common.utils.StringUtil;
import com.alumniassociation.api.entity.LoginTokenInfo;
import com.alumniassociation.api.entity.Token;
import com.alumniassociation.common.FileUploadBaseController;
import com.alumniassociation.common.dao.AntInstBaseDao;
import com.alumniassociation.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyan on 2017/4/17.
 */
public abstract class BaseController extends FileUploadBaseController{

    protected static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AntInstBaseDao antInstBaseDao;
    
    /**
     * 获取token
     *
     * @return
     */
    public final String getToken() {
    	String headerToken = request.getHeader("token");
    	if(StringUtils.isNotEmpty(headerToken)){
    		return headerToken;
    	}
        return request.getParameter("token");
    }
    
    /**
     * 获取当前登陆Token信息
     *
     * @return
     * @throws Exception
     */
    public final Token getCurrentUser() {
    	//当前调用接口需要token验证的情况
    	Token tokenEntity = (Token)request.getAttribute(AuthorizationInterceptor.LOGIN_TOKEN_KEY);
    	
    	//当前调用接口不需要token验证，但是也可能携带token情况
    	if(tokenEntity == null){
    		String token = getToken();
    		if (!StringUtil.isEmpty(token)) {
    			String tokenJson = stringRedisTemplate.opsForValue().get("token:"+token);
    			if(!StringUtil.isEmpty(tokenJson)){
    				tokenEntity = JsonUtils.jsonToPojo(tokenJson, Token.class);
    			}
    		}
    	}
    	return tokenEntity;
    }
    
    /**
     * 获取当前登陆用户电话
     *
     * @return
     * @throws Exception
     */
    public final String getCurrentTelNbr() {
    	Token t = getCurrentUser();
    	return t==null?"":t.getTelNbr();
    }
    
    /**
     * 获取当前登陆用户userId
     *
     * @return
     * @throws Exception
     */
    public final int getCurrentUserId() {
    	Token t = getCurrentUser();
    	return t==null?0:t.getUserId();
    }
    
    /**
     * 获取当前登陆用户openId
     *
     * @return
     * @throws Exception
     */
    public final String getCurrentOpenId() {
    	Token t = getCurrentUser();
    	return t==null?"":t.getMpOpenId();
    }
    
    /**
     * 获取当前登陆用户的真实姓名 realName
     *
     * @return
     * @throws Exception
     */
    public final String getCurrentRealName() {
    	Token t = getCurrentUser();
    	return t==null?"":t.getRealName();
    }
    
    /**
     * 获取当前登陆用户的角色roleNum
     *
     * @return
     * @throws Exception
     */
    public final Integer getCurrentRoleNum() {
    	Token t = getCurrentUser();
    	return t==null?0:t.getRoleNum();
    }

    public AntInstBaseDao getDao(){
    	return this.antInstBaseDao;
    }
    
    public StringRedisTemplate getRedisTemplate(){
    	return this.stringRedisTemplate;
    }
    
    /**
     * 根据电话号码查询对应的推送Id
     * @return
     */
    public String getAliasByTel(String tel){
    	String loginInfoJson = stringRedisTemplate.opsForValue().get("loginInfo:"+tel);
		if(!StringUtil.isEmpty(loginInfoJson)){
			LoginTokenInfo loginTokenInfo = JsonUtils.jsonToPojo(loginInfoJson, LoginTokenInfo.class);
			return loginTokenInfo==null?"":loginTokenInfo.getAlias();
		}
    	return "";
    }

}
