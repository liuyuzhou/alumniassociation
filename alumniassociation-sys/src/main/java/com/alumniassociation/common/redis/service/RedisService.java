package com.alumniassociation.common.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	protected static final Logger LOG = LoggerFactory.getLogger(RedisService.class);
	
	
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public final static String CHANNEL_PRVE = "channel_";
    
    //向通道发送消息的方法
    public void sendChannelMess(String channel, String message) {
    	stringRedisTemplate.opsForList().leftPush(CHANNEL_PRVE+channel, message);
    	stringRedisTemplate.convertAndSend(channel, CHANNEL_PRVE+channel);
    }
    
   //向通道发送消息的方法
    public void sendChannelMess(String channel, String[] message) {
    	stringRedisTemplate.opsForList().leftPushAll(CHANNEL_PRVE+channel, message);
    	stringRedisTemplate.convertAndSend(channel, CHANNEL_PRVE+channel);
    }
    
}