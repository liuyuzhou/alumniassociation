package com.alumniassociation.common.datamock;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class MockInterceptor implements ApplicationContextAware {
    @Value("${mock.open:true}")
    private boolean mockOpen;

    private ApplicationContext app = null;

    @Pointcut("@annotation(com.alumniassociation.common.datamock.Mock)")
    public void addAdvice() {

    }


    @Around("addAdvice()&&@annotation(mock)")
    public Object enterDamper(ProceedingJoinPoint joinPoint, Mock mock) throws Throwable {
        if (!mockOpen) {
            return joinPoint.proceed();
        }
        if (!mock.isOpen()) {
            return joinPoint.proceed();
        }
        String methodName = joinPoint.getSignature().getName();
        Class damperClass = mock.mockClass();
        try {

            Object damperBean = app.getBean(damperClass);

            Class[] param = new Class[joinPoint.getArgs().length];
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                param[i] = joinPoint.getArgs()[i].getClass();
            }
            Method method = ReflectionUtils.findMethod(damperClass, methodName, param);
            return method.invoke(damperBean, joinPoint.getArgs());
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        app = applicationContext;
    }
}
