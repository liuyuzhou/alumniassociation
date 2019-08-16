package com.alumniassociation.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.commpara.service.CommparaService;

import io.swagger.annotations.Api;

import com.alumniassociation.api.common.api.annotation.IgnoreAuth;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.common.utils.StringUtil;
import com.alumniassociation.api.service.ApiCommparaService;

/**
 * Created by lewp 2018/10/14
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/api/getData")
public class ApiGetDataController {

	@Autowired
	private ApiCommparaService apiCommparaService;

	@Autowired
	private CommparaService commparaService;
	/**
	 * @param
	 * @author lewp
	 * @Description 通过表码获取数据列表
	 * @date date 2017-7-20
	 */
	@IgnoreAuth
	@ResponseBody
	@RequestMapping("/getCodeValues")
	public DataMsg getCodeValues(@RequestParam Map<String, Object> params) {
		List<Map<String, Object>> sysCodeList = null;
		String codeName = null;
		if (params.get("codeName") != null && !"".equals(codeName = params.get("codeName").toString())) {
			if (codeName.indexOf(",") >= 0) {
				Map<String, List<Map<String, Object>>> res = new HashMap<String, List<Map<String, Object>>>();
				for (String cn : codeName.split(",")) {
					if (!StringUtil.isBlank(cn)) {
						res.put(cn, apiCommparaService.queryListByCode(cn));
					}
				}
				return DataMsg.ok(res);
			}
			sysCodeList = apiCommparaService.queryListByCode(params.get("codeName").toString());
		}
		return DataMsg.ok(sysCodeList == null ? new ArrayList<>() : sysCodeList);
	}
	
	/**
	 * @param
	 * @author lewp
	 * @Description 获取运营商列表
	 * @date date 2017-7-20
	 */
	@IgnoreAuth
	@ResponseBody
	@RequestMapping("/getOperatorList")
	public DataMsg getOperatorList(@RequestBody Map<String, Object> params) {
		List<Map<String, Object>> res = apiCommparaService.queryListByCode("org_id");
		for(Map<String, Object> commpara : res){
			commpara.put("code", commpara.get("code"));
			commpara.put("value", commpara.get("value"));
			commpara.remove("para_key");
			commpara.remove("para_name");
			commpara.remove("para_code");
		}
		return DataMsg.ok(res);
	}

}
