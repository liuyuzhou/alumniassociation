package com.alumniassociation.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.operation.entity.ProJurisdiction;
import com.alumniassociation.operation.resitory.ProJurisdictionResitory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 项目权限信息
 * 
 * @author lyz
 *
 */
@Api(value = "/api", tags = "项目权限接口")
@RestController
@RequestMapping("/szxyh/projurisdiction")
public class ProJurisdictionController {
	@Autowired
	private ProJurisdictionResitory proJurisdictionResitory;

	@ApiOperation(value = "取得所有项目权限", notes = "展示所有项目权限")
	@GetMapping(value = "/listAllProJurisdiction")
	public List<ProJurisdiction> getProJurisdictionList() {
		return proJurisdictionResitory.findAll();
	}

	@ApiOperation(value = "添加一个权限", notes = "添加一个权限")
	@PostMapping(value = "/addProJurisdiction")
	public ProJurisdiction addProJurisdiction(@RequestParam("jurisdictionName") String jurisdictionName) {
		ProJurisdiction proJurisdiction = new ProJurisdiction();
		proJurisdiction.setJurisdictionName(jurisdictionName);
		Date dateNow = new Date();
		proJurisdiction.setCreateTime(dateNow);
		proJurisdiction.setUpdateTime(dateNow);
		return proJurisdictionResitory.save(proJurisdiction);
	}

	@ApiOperation(value = "更新一个权限", notes = "更新一个权限")
	@PutMapping(value = "/updateProJurisdiction/{id}")
	public ProJurisdiction updateProJurisdiction(@PathVariable("id") Integer id,
			@RequestParam("jurisdictionName") String jurisdictionName) {
		ProJurisdiction proJurisdiction = new ProJurisdiction();
		proJurisdiction.setId(id);
		proJurisdiction.setJurisdictionName(jurisdictionName);
		proJurisdiction.setUpdateTime(new Date());
		return proJurisdictionResitory.save(proJurisdiction);
	}

	@ApiOperation(value = "查找指定权限", notes = "查找指定权限")
	@GetMapping(value = "/findProJurisdiction/{id}")
	public ProJurisdiction getProJurisdiction(@PathVariable("id") Integer id) {
		return proJurisdictionResitory.findOne(id);
	}

	@ApiOperation(value = "删除指定权限", notes = "删除指定权限")
	@DeleteMapping(value = "/deleteProJurisdiction/{id}")
	public void deleteProJurisdiction(@PathVariable("id") Integer id) {
		proJurisdictionResitory.delete(id);
	}

}
