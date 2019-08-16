package com.alumniassociation.api.common.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alumniassociation.api.common.api.annotation.IgnoreAuth;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.entity.Token;
import com.alumniassociation.common.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 权限验证
 * @author wangyan
 * @date:   2018年11月16日 下午3:48:22
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    
    public static final String LOGIN_TOKEN_KEY = "X-Alumniassociation-Token";
    
    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	response.setHeader("Content-type", "text/html;charset=UTF-8");
        IgnoreAuth annotation;
        // 客户端请求URL
        String requestURLString = request.getRequestURI();
        
        //异常页面直接放行
        if ("/error".equals(requestURLString)) {
        	mapper.writeValue(response.getOutputStream(), DataMsg.error("请求路径不存在"));
            return false;
        }
        
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        }else{
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if(StringUtils.isBlank(token)){
        	mapper.writeValue(response.getOutputStream(), DataMsg.tokerExpired());
            return false;
        }
        
        String tokenJson = stringRedisTemplate.opsForValue().get("token:"+token);
        if(StringUtils.isEmpty(tokenJson)){
        	mapper.writeValue(response.getOutputStream(), DataMsg.tokerExpired());
            return false;
        }
        Token loginToken = JsonUtils.jsonToPojo(tokenJson, Token.class);
        request.setAttribute("loginUserInfo", loginToken);
        return true;
    }
}
