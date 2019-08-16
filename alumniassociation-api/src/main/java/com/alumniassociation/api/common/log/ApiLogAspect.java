package com.alumniassociation.api.common.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alumniassociation.api.common.api.interceptor.AuthorizationInterceptor;
import com.alumniassociation.api.common.utils.StringUtil;
import com.alumniassociation.api.common.utils.WebUtil;
import com.alumniassociation.api.entity.Token;
import com.alumniassociation.api.service.SysLogService;
import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.IPUtils;

/**
 * @author wangyan
 * @date:   2018年11月1日 下午4:20:09
 */
@Aspect
@Component
@Order(5)
public class ApiLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SysLogService logService;

    @Pointcut("execution(public * com.alumniassociation.api.controller..*.*(..)) "
    		+ "|| execution(public * com.alumniassociation.api.*.*.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String ip = IPUtils.getIpAddr(request);
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + ip);
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        String args = WebUtil.getRequestArgs(request,joinPoint.getArgs());
        logger.info("ARGS : " + args);
        //获取方法日志注解
        String value=getControllerMethodDescription(joinPoint);
        
        if(!StringUtil.isEmpty(value)){
        	logger.info("SysLog : " + value);
            // TODO: 2017/12/28  插入数据库......
            com.alumniassociation.api.entity.SysLog sysLog=new com.alumniassociation.api.entity.SysLog();
            sysLog.setCreateDate(new Date());
            sysLog.setIp(ip);
            sysLog.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            sysLog.setOperation(value);
            //请求的参数
            sysLog.setParams(args);

            Token tokenEntity = (Token)request.getAttribute(AuthorizationInterceptor.LOGIN_TOKEN_KEY);
            if(tokenEntity != null){
            	//查询token信息
                sysLog.setUsername(tokenEntity.getRealName());
            }
            logService.save(sysLog);
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
       // logger.info("RESPONSE : " + ret);
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    Annotation annotation= method.getAnnotation(SysLog. class);
                    if(annotation!=null){
                        description = method.getAnnotation(SysLog. class).value();
                    }
                    break;
                }
            }
        }
        return description;
    }



}