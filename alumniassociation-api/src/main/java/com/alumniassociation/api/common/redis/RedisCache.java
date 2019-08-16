package com.alumniassociation.api.common.redis;

import java.lang.annotation.*;

/**
 * Created by lewp on 2018/10/25
 * 缓存注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
	Class<?> type();//被代理类的全类名，在之后会做为redis hash 的key
	
	RedisDataType redisDataType();
	
    public enum RedisDataType {
    	/**
         * string key-value
         */
        STRING(0),
        /**
         * hash存储
         */
        HASH(1),
        /**
         * 列表存储
         */
        LIST(2),
        /**
         * 散列存储
         */
        SET(3),
    	/**
         * 有序存储
         */
    	ZSET(4);

        private int value;

        private RedisDataType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
