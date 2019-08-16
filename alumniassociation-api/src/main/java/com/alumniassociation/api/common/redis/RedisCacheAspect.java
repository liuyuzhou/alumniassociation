package com.alumniassociation.api.common.redis;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alumniassociation.api.common.utils.FastJsonUtil;

/**
 * @author lewp
 * @date 2018/10/25
 * 方法级别的缓存
 */
@Aspect
@Component
@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class RedisCacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);
    
    private static final String PAGE = "page";
    
    private static final String PAGENO = "limit" ;
    /**
     * 分隔符 
     **/
    private static final String DELIMITER = ":";
    
    private static final String SUFFIX_LIST = "_list";
    /**
     * spring-redis.xml配置连接池、连接工厂、Redis模板
     **/

    @Autowired
    StringRedisTemplate srt;

    /**
     * Service层切点 使用到了我们定义的 RedisCache 作为切点表达式。
     * 第一个参数必须为唯一属性
     */
    @Pointcut("@annotation(com.alumniassociation.api.common.redis.RedisCache)")
    public void redisCacheAspect() {
    }

    /**
     * Service层切点 使用到了我们定义的 RedisEvict 作为切点表达式。
     * 而且我们可以看出此表达式是基于 annotation 的。
     * 并且用于内建属性为非查询的方法之上，用于更新表
     */
    @Pointcut("@annotation(com.alumniassociation.api.common.redis.RedisEvict)")
    public void redisCacheEvict() {
    }
    
    @Around("redisCacheAspect()")
    public Object beforeExec(ProceedingJoinPoint joinPoint) {
    	//result是方法的最终返回结果
        Object result = null;
        // 得到类名、方法名和参数
        Object[] args = joinPoint.getArgs();

        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisCache.class).type();
       // 得到被代理方法的返回值类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        String redisValue = null;
        RedisCache.RedisDataType redisDataType = method.getAnnotation(RedisCache.class).redisDataType();
        if(redisDataType == RedisCache.RedisDataType.STRING){
        	// 如果参数中没有ID。不检查缓存
        	 // key为方法第一个参数ID
        	 String key = null;
        	if(args == null || args.length == 0){//没有参数的方法， key为类名+方法名
        		key = modelType.getName() + DELIMITER + method.getName();
        	}else{
        		key = String.valueOf(args[0]);
        	}
        	redisValue = srt.opsForValue().get(key);
        	try {
                if (null == redisValue) {
                    if (logger.isInfoEnabled()) {
                        logger.info("缓存未命中");
                    }

                    // 调用数据库查询方法
                    result = joinPoint.proceed(args);

                    // 序列化查询结果
                    String json = FastJsonUtil.toJsonString(result);
                    // 序列化结果放入缓存
                    srt.opsForValue().set(key, json);
                } else {

                    // 缓存命中
                    if (logger.isInfoEnabled()) {
                        logger.info("缓存命中, redisValue = " + redisValue);
                    }
                    // 反序列化 从缓存中拿到的json字符串
                    if(returnType != String.class){
                    	result = FastJsonUtil.toObject(redisValue, returnType);
                    }
                    if (logger.isInfoEnabled()) {
                        logger.info("gson反序列化结果 = " + result);
                    }
                }
            } catch (Throwable e) {
                logger.error("解析异常",e);
            }
        }else if(redisDataType == RedisCache.RedisDataType.HASH){
        	// 如果参数中没有ID。不检查缓存
            if(args == null || args.length <= 0){
            	try{
            		result = joinPoint.proceed(args);
            	}catch (Throwable e) {
                    logger.error("解析异常",e);
                }
            	return result;
            }
        	 // 检查Redis中是否有缓存
        	 // key为方法第一个参数ID
            String key = String.valueOf(args[0]);
            if (logger.isInfoEnabled()) {
                logger.info("生成key: " + key);
            }
        	redisValue = (String) srt.opsForHash().get(modelType.getName(), key);
        	try {
                if (null == redisValue) {
                    if (logger.isInfoEnabled()) {
                        logger.info("缓存未命中");
                    }

                    // 调用数据库查询方法
                    result = joinPoint.proceed(args);
                    if(result != null){
                    	// 序列化查询结果
                        String json = FastJsonUtil.toJsonString(result);

                        // 序列化结果放入缓存
                        srt.opsForHash().put(modelType.getName(), key, json);
                    }
                } else {

                    // 缓存命中
                    if (logger.isInfoEnabled()) {
                        logger.info("缓存命中, value = " + redisValue);
                    }
                    // 反序列化 从缓存中拿到的json字符串
                    result = FastJsonUtil.toObject(redisValue, returnType);
                    if (logger.isInfoEnabled()) {
                        logger.info("gson反序列化结果 = " + result);
                    }
                }
            } catch (Throwable e) {
                logger.error("解析异常",e);
            }
        }else if(redisDataType == RedisCache.RedisDataType.LIST){
        	// 如果参数中没有ID。不检查缓存
            if(args == null || args.length <= 0){
            	try{
            		result = joinPoint.proceed(args);
            	}catch (Throwable e) {
                    logger.error("解析异常",e);
                }
            	return result;
            }
        	 // 检查Redis中是否有缓存
        	 // key为方法第一个参数ID
        	if(!(args[0] instanceof Map)){//如果不是map
        		try{
            		result = joinPoint.proceed(args);
            	}catch (Throwable e) {
                    logger.error("解析异常",e);
                }
        		return result;
        	}
        	Map paramMap = (Map)(args[0]);
        	int page = 1;
        	if(paramMap.get(PAGE) != null){
        		try{
        			page = Integer.parseInt(paramMap.get(PAGE).toString());
        		}catch(Exception e){
        			logger.info("page参数异常");
        		}
        	}
        	int pageNo = 10;
        	if(paramMap.get(PAGENO) != null){
        		try{
       			  pageNo = Integer.parseInt(paramMap.get(PAGENO).toString());
       		    }catch(Exception e){
       			  logger.info("PAGENO参数异常");
        	    }
        	}
            List<String> resList = srt.opsForList().range(modelType.getName() + SUFFIX_LIST, (page - 1) * pageNo, page * pageNo - 1);
        	try {
                if (null == resList || resList.size() == 0) {
                    if (logger.isInfoEnabled()) {
                        logger.info(modelType.getName() + "缓存未命中");
                    }
                    //列表数据需与缓存做实时同步， 若缓存未命中，直接返回空
                    return null;
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append("[");
                    for(String id : resList){
                    	sb.append(srt.opsForHash().get(modelType.getName(), id));
                    	sb.append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);//删除'，'号
                    sb.append("]");
                    result = FastJsonUtil.toList(sb.toString(), Map.class);//转成map存储
                    sb = null;
                    resList.clear();
                    if (logger.isInfoEnabled()) {
                        logger.info("gson反序列化结果 = " + result);
                    }
                }
            } catch (Throwable e) {
                logger.error("解析异常",e);
            }
        }else if(redisDataType == RedisCache.RedisDataType.SET){
        	
        }else if(redisDataType == RedisCache.RedisDataType.ZSET){
        }
        
        return result;
    }

}

