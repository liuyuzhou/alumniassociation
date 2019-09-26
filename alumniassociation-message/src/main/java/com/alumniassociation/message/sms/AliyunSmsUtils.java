package com.alumniassociation.message.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Component
public class AliyunSmsUtils {

	private static final Logger logger = LoggerFactory.getLogger(AliyunSmsUtils.class);
	
	//产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";
    
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;
    
    @Value("${aliyun.sms.msgSign}")
    private String msgSign;
    
    @Value("${aliyun.sms.codeTemplate}")
    private String codeTemplate;
    
    @Value("${aliyun.sms.orderTakTemplate}")
    private String orderTakTemplate;
    
    @Value("${aliyun.sms.quotationTemplate}")
    private String quotationTemplate;
    
    @Value("${aliyun.sms.afterPayTemplate}")
    private String afterPayTemplate;
    
    @Value("${aliyun.sms.installTemplate}")
    private String installTemplate;
    
    @Value("${aliyun.sms.userAfterSaleTemplate}")
    private String userAfterSaleTemplate;
    
    @Value("${aliyun.sms.saleAfterSaleTemplate}")
    private String saleAfterSaleTemplate;
    
    @Value("${aliyun.sms.customerCodeTemplate}")
    private String customerCodeTemplate;
    
    @Value("${aliyun.sms.installIncomeTemplate}")
    private String installIncomeTemplate;
    
    @Value("${aliyun.sms.saleOrderTemplate}")
    private String saleOrderTemplate;
    
    @Value("${aliyun.sms.userNewRulerTemplate}")
    private String userNewRulerTemplate;
    
    @Value("${aliyun.sms.saleAfterPayTemplate}")
    private String saleAfterPayTemplate;
    
    @Value("${aliyun.sms.userContractSignTemplate}")
    private String userContractSignTemplate;
    
    @Value("${aliyun.sms.userFinishedInstallTemplate}")
    private String userFinishedInstallTemplate;
    
    @Value("${aliyun.sms.bossContractSignTemplate}")
    private String bossContractSignTemplate;
    
    @Value("${aliyun.sms.evaCodeTemplate}")
    private String evaCodeTemplate;

    private IAcsClient acsClient;
    
    private boolean isLoaded = false;
    
    private synchronized void init(){
    	if(isLoaded){
    		return;
    	}
    	 //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
        	 DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (Exception e) {
			logger.error("阿里云短信发送服务初始化失败！");
		}
        acsClient = new DefaultAcsClient(profile);
        isLoaded = true;
        logger.info("阿里云短信发送服务初始化成功！");
    }

    /**
     * 通过阿里云发送短信
     * @throws ClientException 
     * @throws ServerException 
     */
    public SendSmsResponse sendSmsMsg(String tel, int msgNo, String[] args) throws ServerException, ClientException {
    	if(!isLoaded){
    		init();
    	}
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(tel);
        request.setSignName(msgSign);
        
        if(msgNo == 7){
        	request.setOutId("INSTALL_CONFIRM_CODE");//设置安装确认码
        }
        
        request.setTemplateCode(getTemplateCode(msgNo));
        request.setTemplateParam(getTemplateParam(msgNo,args));
        return acsClient.getAcsResponse(request);
    }
    
    public String getTemplateCode(int msgNo){
        switch(msgNo){
	        case 0:
	            return codeTemplate;
	        case 1:
		    	return orderTakTemplate;
		    case 2:
		    	return quotationTemplate;
		    case 3:
		    	return afterPayTemplate;
		    case 4:
		    	return installTemplate;
		    case 5:
		    	return userAfterSaleTemplate;
		    case 6:
		    	return saleAfterSaleTemplate;
		    case 7:
		    	return customerCodeTemplate;
		    case 8:
		    	return installIncomeTemplate;
		    case 9:
		    	return saleOrderTemplate;
		    case 10:
		    	return userNewRulerTemplate;
		    case 11:
		    	return saleAfterPayTemplate;
		    case 12:
		    	return userContractSignTemplate;
		    case 13:
		    	return userFinishedInstallTemplate;
		    case 14:
		    	return bossContractSignTemplate;
		    case 15:
		    	return evaCodeTemplate;
	        default:
	            return "";	
        }
    }
    
    public String getTemplateParam(int msgNo,String[] args){
		switch(msgNo){
		    case 0:
		        return "{\"code\":\""+args[0]+"\"}";
		    case 1:
		    	return "{\"saleName\":\""+args[0]+"\",\"phone\":\""+args[1]+"\"}";
		    case 2:
		    	return "{\"orderNo\":\""+args[0]+"\",\"amount\":\""+args[1]+"\"}";
		    case 3:
		    	return "{\"orderNo\":\""+args[0]+"\"}";
		    case 4:
		    	return "{\"installerMaster\":\""+args[0]+"\",\"phone\":\""+args[1]+"\"}";
		    case 5:
		    	return "";
		    case 6:
		    	return "{\"orderNo\":\""+args[0]+"\",\"user\":\""+args[1]+"\",\"descript\":\""+args[2]+"\"}";
		    case 7:
		    	return "{\"masterName\":\""+args[0]+"\",\"installTime\":\""+args[1]+"\",\"code\":\""+args[2]+"\",\"servicePhone\":\""+args[3]+"\"}";
		    case 8:
		    	return "{\"masterName\":\""+args[0]+"\",\"orderId\":\""+args[1]+"\",\"installFee\":\""+args[2]+"\"}";
		    case 9:
		    	return "";
		    case 10:
		    	return "{\"newTime\":\""+args[0]+"\"}";
		    case 11:
		    	return "{\"orderNo\":\""+args[0]+"\"}";
		    case 12:
		    	return "{\"orderNO\":\""+args[0]+"\"}";
		    case 13:
		    	return "";
		    case 14:
		    	return "{\"name\":\""+args[0]+"\",\"saleName\":\""+args[1]+"\",\"signTime\":\""+args[2]+"\",\"customerName\":\""+args[3]+"\",\"orderNo\":\""+args[4]+"\",\"amount\":\""+args[5]+"\"}";
		    case 15:
		        return "{\"code\":\""+args[0]+"\"}";
		    default:
		        return "";	
		}
    	
    }
}
