package com.alumniassociation.authentication.service;

import java.util.List;

import com.alumniassociation.authentication.entity.AuthenticationInfo;

public interface AuthenticationInfoService {
	public int addAuthenticationInfo(AuthenticationInfo authenticationInfo);

	public List<AuthenticationInfo> findByAuthenticatorId(Integer authenticatorId);
}
