package com.alumniassociation.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alumniassociation.commpara.service.CommparaService;
import com.alumniassociation.api.service.ApiCommparaService;
import com.alumniassociation.common.config.RedisSubListenerConfig;
import com.alumniassociation.common.redis.receiver.RedisReceiver;
import com.alumniassociation.common.utils.FastJsonUtil;

@Service("apiCommparaService")
@Transactional
public class ApiCommparaServiceImpl implements ApiCommparaService, RedisReceiver  {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommparaServiceImpl.class);
	
	@Autowired
	private CommparaService commparaService;
	
	@Autowired
	StringRedisTemplate srt;
	
	@Autowired
	RedisSubListenerConfig redisSubListenerConfig;
	
	@Override
	public List<Map<String, Object>> queryListByCode(String code) {
		Object obj = srt.opsForHash().get(this.getClass().getName(), code);
		if(obj != null){
			return JSON.parseObject(obj.toString(), new TypeReference<List<Map<String, Object>>>(){});
		}
		//缓存未命中，则查询数据库
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paraCode", code);
		List<Map<String, Object>> allCommpara = commparaService.getApiListNew(paramMap);
		return allCommpara;
	}
	
	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		load();
		// 添加捷报信道监听
		//redisSubListenerConfig.addMessageListener(this, RedisChannelEnum.COMMPARA_UPDATE.getCode());
				
	}
	
	private void load(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", 1);// 有效
		List<Map<String, Object>> allCommpara = commparaService.getApiListNew(paramMap);
		final String hash_key = this.getClass().getName();
		srt.delete(hash_key);
		List<Map<String, Object>> oneCommpara = null;
		String prevCode = null;
		for (Map<String, Object> pt : allCommpara) {
			if(prevCode == null || !prevCode.equals(pt.get("para_code"))){
                if(prevCode != null){
                	srt.opsForHash().put(hash_key, prevCode, FastJsonUtil.toJsonString(oneCommpara));
                	oneCommpara.clear();
				}
				oneCommpara = new ArrayList<Map<String, Object>>();
				prevCode = pt.get("para_code").toString();
			}
			oneCommpara.add(pt);
		}
		if(oneCommpara != null){
			srt.opsForHash().put(hash_key, prevCode, FastJsonUtil.toJsonString(oneCommpara));
			oneCommpara.clear();
		}
		allCommpara.clear();
	}

	@Override
	public void handleMessage(String message) {
		load();
	}


}
