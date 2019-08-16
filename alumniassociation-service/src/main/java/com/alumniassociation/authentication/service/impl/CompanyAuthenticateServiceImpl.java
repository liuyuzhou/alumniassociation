package com.alumniassociation.authentication.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alumniassociation.authentication.dao.CompanyAuthenticateMapper;
import com.alumniassociation.authentication.entity.CompanyAuthenticate;
import com.alumniassociation.authentication.service.CompanyAuthenticateService;

@Service("companyAuthenticateService")
public class CompanyAuthenticateServiceImpl implements CompanyAuthenticateService {
	@Resource
	private CompanyAuthenticateMapper companyAuthenticateMapper;

	@Override
	public int addCompanyAuthenticate(CompanyAuthenticate companyAuthenticate) {
		return companyAuthenticateMapper.insert(companyAuthenticate);
	}

	@Override
	public void updateCompanyAuthenticate(CompanyAuthenticate companyAuthenticate) {
		companyAuthenticateMapper.updateByPrimaryKey(companyAuthenticate);
	}

	@Override
	public CompanyAuthenticate getById(Integer id) {
		return companyAuthenticateMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteCompanyAuthenticate(Integer id) {
		companyAuthenticateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CompanyAuthenticate> findAll() {
		return companyAuthenticateMapper.findAll();
	}

}
