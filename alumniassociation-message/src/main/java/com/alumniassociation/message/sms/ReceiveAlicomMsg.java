package com.alumniassociation.message.sms;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.alumniassociation.common.utils.StringUtil;
import com.google.gson.Gson;

/**
 * 阿里云短信回执监听器
 * @author wangyan
 * @date:   2019年1月6日 下午3:43:58
 */
@Component
public class ReceiveAlicomMsg {
	private static final Logger logger = LoggerFactory.getLogger(ReceiveAlicomMsg.class);
	
	private static final String MESSAGETYPE = "SmsReport";
	
	private static final String QUEUENAME = "Alicom-Queue-1951839140510716-SmsReport";
	
	@Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;
    
    @Value("${aliyun.sms.receiveStatus}")
    private boolean receiveStatus;
    
	class MyMessageListener implements MessageListener{
		private Gson gson=new Gson();

		@Override
		public boolean dealMessage(Message message) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//消息的几个关键值
			System.err.println("message receiver time from mns:" + format.format(new Date()));
			System.err.println("message handle: " + message.getReceiptHandle());
            System.err.println("message body: " + message.getMessageBodyAsString());
            System.err.println("message id: " + message.getMessageId());
            System.err.println("message dequeue count:" + message.getDequeueCount());
            System.err.println("Thread:" + Thread.currentThread().getName());
            try{
            	Map<String,Object> contentMap=gson.fromJson(message.getMessageBodyAsString(), HashMap.class);

            	String outId = (String) contentMap.get("out_id");//回执信息
            	if(!StringUtil.isEmpty(outId)){
            		Boolean success = (Boolean) contentMap.get("success");//用户是否接受成功
            		String bizId = (String) contentMap.get("biz_id");//短信回执ID
            		String errMsg = (String) contentMap.get("err_msg");//回执提示
            		Map<String, Object> paramsMap = new HashMap<>();
            		paramsMap.put("bizId", bizId);
            		paramsMap.put("smsStatus", success?1:0);
            		paramsMap.put("errMsg", errMsg);
            		//做消息队列处理 ， redis 新的发出消息
            	}

            }catch(com.google.gson.JsonSyntaxException e){
            	logger.error("error_json_format:"+message.getMessageBodyAsString(),e);
				//理论上不会出现格式错误的情况，所以遇见格式错误的消息，只能先delete,否则重新推送也会一直报错
				return true;
            } catch (Throwable e) {
				//您自己的代码部分导致的异常，应该return false,这样消息不会被delete掉，而会根据策略进行重推
            	logger.error("短信回执处理异常!:"+message.getMessageBodyAsString(),e);
				return false;
			}

			//消息处理成功，返回true, SDK将调用MNS的delete方法将消息从队列中删除掉
			return true;
		}

	}

	@PostConstruct
	public void init() throws Exception, ParseException {
        if(!receiveStatus){
        	return;
        }
		DefaultAlicomMessagePuller puller=new DefaultAlicomMessagePuller();

		//设置异步线程池大小及任务队列的大小，还有无数据线程休眠时间
		puller.setConsumeMinThreadSize(6);
		puller.setConsumeMaxThreadSize(16);
		puller.setThreadQueueSize(200);
		puller.setPullMsgThreadSize(1);
		//和服务端联调问题时开启,平时无需开启，消耗性能
		puller.openDebugLog(false);
		
		puller.startReceiveMsg(accessKeyId,accessKeySecret, MESSAGETYPE, QUEUENAME, new MyMessageListener());
    }

	
	
}
