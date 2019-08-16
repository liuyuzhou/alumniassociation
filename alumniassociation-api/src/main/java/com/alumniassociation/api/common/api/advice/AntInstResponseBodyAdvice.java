package com.alumniassociation.api.common.api.advice;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alumniassociation.api.common.api.annotation.SerializedField;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.common.utils.Helper;
import com.alumniassociation.common.utils.DateUtil;
import com.alumniassociation.common.utils.FastJsonUtil;

@Order(1)
@ControllerAdvice(basePackages = "com.alumniassociation.api.controller")
public class AntInstResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	
	@Value("${yizhuang.appId}")
	private String appId;
	
	private String[] includes = {};
	
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return methodParameter.getMethod().isAnnotationPresent(SerializedField.class);
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
			ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		includes = new String[] {};
		String message = MessageFormat.format("appId={0}&nonce={1}&signType=MD5&timestamp={2}", appId, serverHttpRequest.getHeaders().getFirst("nonce"), serverHttpRequest.getHeaders().getFirst("timestamp"));
		String secretKey = DigestUtils.md5DigestAsHex(message.getBytes()).substring(0,16).toLowerCase();
		if (secretKey == null || o == null) {
			return null;
		}
		
		if (methodParameter.getMethod().isAnnotationPresent(SerializedField.class)) {
			SerializedField serializedField = methodParameter.getMethodAnnotation(SerializedField.class);
			includes = serializedField.includes();
			Object retObj = null;
			if (o instanceof DataMsg) {
				retObj = handleSingleObject(secretKey, o);
			} else{
				retObj = o;
			}
			return retObj;
		}else{
			return o;
		}
	}

	private Object handleSingleObject(String secretKey, Object o) {
		Map<String, Object> map = new HashMap<String, Object>();

		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (includes.length == 0) {
				String newVal = getNewVal(o, field, secretKey);
				map.put(field.getName(), newVal);
			} else if (includes.length > 0) {
				if (Helper.isStringInArray(field.getName(), includes)) {
					String newVal = getNewVal(o, field, secretKey);
					map.put(field.getName(), newVal);
				}else{
					String val = null;
					boolean is_stc = Modifier.isStatic(field.getModifiers());
					if(is_stc) {
						continue;
					}
					try {
						field.setAccessible(true);
						Object v = field.get(o);
						if(v != null){
							val = v.toString();
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					map.put(field.getName(), val);
				}
			}
		}
		return map;
	}

	private String getNewVal(Object o, Field field, String secretKey) {
		String newVal = "";
		try {
			field.setAccessible(true);
			Object val = field.get(o);

			if (val != null) {
				newVal = Helper.encode(secretKey, FastJsonUtil.toJsonString(val));
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return newVal;
	}
	
}