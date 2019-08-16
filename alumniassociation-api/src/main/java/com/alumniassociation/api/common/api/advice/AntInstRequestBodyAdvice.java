package com.alumniassociation.api.common.api.advice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alumniassociation.api.common.api.annotation.Encrypt;
import com.alumniassociation.api.common.utils.AESUtil;
import com.alumniassociation.common.utils.StringUtil;

@ControllerAdvice(basePackages = "com.alumniassociation.api.controller")
public class AntInstRequestBodyAdvice implements RequestBodyAdvice {

	@Value("${yizhuang.appId}")
	private String appId;
	
	@Override
	public Object afterBodyRead(Object arg0, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,
			Class<? extends HttpMessageConverter<?>> aClass) {
		return arg0;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,
			Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
		return new HttpInputMessage(){
			@Override
			public HttpHeaders getHeaders() {
				String nonce = httpInputMessage.getHeaders().getFirst("nonce");
				String timestamp = httpInputMessage.getHeaders().getFirst("timestamp");
				//生成密钥
				String message = MessageFormat.format("appId={0}&nonce={1}&signType=MD5&timestamp={2}", appId, nonce, timestamp);
				String secretKey = DigestUtils.md5DigestAsHex(message.getBytes()).substring(0,16).toLowerCase();
				httpInputMessage.getHeaders().add("secretKey", secretKey);
				return httpInputMessage.getHeaders();
			}

			@Override
			public InputStream getBody() throws IOException {
				String body = StringUtil.inputStream2String(httpInputMessage.getBody(), "utf-8");
				if(StringUtil.isEmpty(body)){
					return null;
				}
				String secretKey = getHeaders().getFirst("secretKey");
				return new ByteArrayInputStream(AESUtil.decrypt(secretKey, body).getBytes(StandardCharsets.UTF_8));  
			}
		};
	}

	@Override
	public Object handleEmptyBody(Object arg0, HttpInputMessage arg1, MethodParameter arg2, Type arg3,
			Class<? extends HttpMessageConverter<?>> arg4) {
		return arg0;
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Type arg1, Class<? extends HttpMessageConverter<?>> arg2) {
		return methodParameter.hasParameterAnnotation(Encrypt.class);
		//判断解密。必须使用@Encrypt注解才进行解密
	}


}
