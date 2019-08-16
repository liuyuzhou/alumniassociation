package com.alumniassociation.common.redis.receiver;

public interface RedisReceiver {
	
	//这里是收到通道的消息之后执行的方法
    public void handleMessage(String message);
}