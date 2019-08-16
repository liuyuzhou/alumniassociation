package com.alumniassociation.api.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lewp
 * @date 2018-10-25
 */
public interface ApiCommparaService {
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryListByCode(String code);
}
