package com.alumniassociation.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.authentication.entity.CompanyAuthenticate;
import com.alumniassociation.authentication.resitory.CompanyAuthenticateResitory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 企业认证
 * 
 * @author lyz
 *
 */
@Api(value = "/api", tags = "企业信息接口")
@RestController
@RequestMapping("/szxyh/companyAuthenticate")
public class CompanyAuthenticateController {
	@Autowired
	private CompanyAuthenticateResitory companyAuthenticateResitory;

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ApiOperation(value = "取得所有企业信息", notes = "展示企业信息")
	@GetMapping(value = "/listCompanyAuthenticate")
	public List<CompanyAuthenticate> getCompanyAuthenticateList() {
		return companyAuthenticateResitory.findAll();
	}

	@ApiOperation(value = "添加一个企业信息", notes = "添加一个企业信息")
	@PostMapping(value = "/addCompanyAuthenticate")
	public CompanyAuthenticate addCompanyAuthenticate(@RequestParam("userId") Integer userId,
			@RequestParam("companyName") String companyName, @RequestParam("industry") String industry,
			@RequestParam("logo") String logo, @RequestParam("briefIntroduction") String briefIntroduction,
			@RequestParam("otherInfo") String otherInfo) {
		CompanyAuthenticate companyAuthenticate = new CompanyAuthenticate();
		companyAuthenticate.setUserId(userId);
		companyAuthenticate.setCompanyName(companyName);
		companyAuthenticate.setIndustry(industry);
		companyAuthenticate.setLogo(logo);
		companyAuthenticate.setBriefIntroduction(briefIntroduction);
		companyAuthenticate.setOtherInfo(otherInfo);
		Date dateNow = new Date();
		companyAuthenticate.setCreateTime(dateNow);
		companyAuthenticate.setUpdateTime(dateNow);
		return companyAuthenticateResitory.save(companyAuthenticate);
	}

	@ApiOperation(value = "更新企业信息", notes = "更新企业信息")
	@PutMapping(value = "/updateCompanyAuthenticate/{id}")
	public CompanyAuthenticate updateCompanyAuthenticate(@PathVariable("id") Integer id,
			@RequestParam("userId") Integer userId, @RequestParam("companyName") String companyName,
			@RequestParam("industry") String industry, @RequestParam("logo") String logo,
			@RequestParam("briefIntroduction") String briefIntroduction, @RequestParam("otherInfo") String otherInfo,
			Boolean authStatus) {
		CompanyAuthenticate companyAuthenticate = new CompanyAuthenticate();
		companyAuthenticate.setId(id);
		companyAuthenticate.setUserId(userId);
		companyAuthenticate.setCompanyName(companyName);
		companyAuthenticate.setIndustry(industry);
		companyAuthenticate.setLogo(logo);
		companyAuthenticate.setBriefIntroduction(briefIntroduction);
		companyAuthenticate.setOtherInfo(otherInfo);
		companyAuthenticate.setAuthStatus(authStatus);
		companyAuthenticate.setUpdateTime(new Date());
		return companyAuthenticateResitory.save(companyAuthenticate);
	}

	@ApiOperation(value = "查找指定企业", notes = "查找指定企业")
	@GetMapping(value = "/findCompanyAuthenticate/{id}")
	public CompanyAuthenticate getCompanyAuthenticate(@PathVariable("id") Integer id) {
		return companyAuthenticateResitory.findOne(id);
	}

	@ApiOperation(value = "删除指定企业", notes = "删除指定企业")
	@DeleteMapping(value = "/deleteCompanyAuthenticate/{id}")
	public void deleteCompanyAuthenticate(@PathVariable("id") Integer id) {
		companyAuthenticateResitory.delete(id);
	}
}
