package com.alumniassociation.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.alumniassociation.common.redis.receiver.RedisReceiver;

@Configuration
public class RedisSubListenerConfig {
	
	private RedisMessageListenerContainer container;
	
	public RedisSubListenerConfig() {
		container = new RedisMessageListenerContainer();
    }
     //初始化监听器
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        container.setConnectionFactory(connectionFactory);
        return container;
    }
    
    public void addMessageListener(RedisReceiver redisReceiver, String channel){
    	MessageListenerAdapter md = new MessageListenerAdapter(redisReceiver);
    	md.afterPropertiesSet();
    	container.addMessageListener(md, new PatternTopic(channel));
    }
    
    //使用默认的工厂初始化redis操作模板
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
    
}