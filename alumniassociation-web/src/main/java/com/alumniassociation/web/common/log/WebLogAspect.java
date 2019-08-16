package com.alumniassociation.web.common.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
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
import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.IPUtils;
import com.alumniassociation.common.utils.StringUtil;
import com.alumniassociation.web.common.entity.SysLogToDb;
import com.alumniassociation.web.common.service.SysLogService;
import com.alumniassociation.web.common.shiro.ShiroUtils;

/**
 * 日志切入器
 * @author wangyan
 * @date:   2018年12月11日 下午6:22:49
 */
@Aspect
@Component
@Order(5)
public class WebLogAspect {

    @Autowired
    private SysLogService logService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.alumniassociation.web..*Controller.*(..))")
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
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取方法日志注解
        String value=getControllerMethodDescription(joinPoint);

        if(!StringUtil.isEmpty(value)){
        	logger.info("SysLog : " + value);
            SysLogToDb sysLog = new SysLogToDb();
            sysLog.setCreateDate(new Date());
            sysLog.setIp(ip);
            sysLog.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            sysLog.setOperation(value);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            if(args.length>0){
            	String params = JSON.toJSONString(args[0]);
                sysLog.setParams(params);
            }
            sysLog.setUsername(ShiroUtils.getUserName());
            logService.save(sysLog);
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        //logger.info("RESPONSE : " + ret);

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
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
                    Annotation annotation= method.getAnnotation(SysLog.class);
                    if(annotation!=null){
                        description = method.getAnnotation(SysLog.class).value();
                    }
                    break;
                }
            }
        }
        return description;
    }



}