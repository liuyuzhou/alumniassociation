package com.alumniassociation.api.common.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * @author wangyan
 * @date:   2018年11月1日 下午4:22:05
 */
public class WebUtil {
	public static Map<String, Object> getParamMap(HttpServletRequest request) {
		Map<String, String[]> requestMap = request.getParameterMap();
		return getParamMap(requestMap);
	}

	public static Map<String, Object> getParamMap(Map<String, String[]> requestMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (Entry<String, String[]> ent : requestMap.entrySet()) {
			String[] values = requestMap.get(ent.getKey());
			if (values != null) {
				paramMap.put(ent.getKey(), (values.length > 0 ? values[0] : null));
			}
		}
		return paramMap;
	}
	
	/**
	 * 获取请求参数 
	 * x-www-form-urlencoded：格式[参数名=参数值,参数名=参数值.....],参数为空：[]
	 * application/json：[{参数名=参数值,参数名=参数值......}],参数为空:[{}]
	 * @param request
	 * @return
	 */
	public static String getRequestArgs(HttpServletRequest request,Object[] args) {
		String contentType = request.getContentType();
		if(StringUtil.isEmpty(contentType)) return "";
		if(contentType.startsWith("application/x-www-form-urlencoded")){
			StringBuffer buffer = new StringBuffer();
			String key;
			String[] values;
			buffer.append("[");
			Map<String, String[]> requestMap = request.getParameterMap();
			for (Entry<String, String[]> ent : requestMap.entrySet()) {
				key = ent.getKey();
				buffer.append(key+"=");
				//如果请求中涉及到密码等重要信息，不记录日志
				if(key.indexOf("pwd")>-1 || key.indexOf("oldPwd")>-1 || key.indexOf("newPwd")>-1){
					buffer.append("***,");
				}else{
					values = requestMap.get(key);
					buffer.append((StringUtils.isEmpty(values[0]) ? null : values[0]) + ",");
				}
			}
			if(buffer.length()>1){
				buffer.deleteCharAt(buffer.length() - 1);
			}
			buffer.append("]");
			return buffer.toString();
		}
		if(contentType.startsWith("application/json")){
			return Arrays.toString(args);
		}
		return "";
	}
}
