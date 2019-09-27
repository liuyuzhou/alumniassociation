package com.alumniassociation.api.datamock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.dto.WxLoginInfo;
import com.alumniassociation.api.entity.Token;
import com.alumniassociation.api.service.UserTokenManager;
import com.alumniassociation.api.util.IpUtil;
import com.alumniassociation.common.utils.JsonUtils;
import com.alumniassociation.user.entity.UserInfo;
import com.alumniassociation.user.service.UserInfoService;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟登陆
 */
@Component
@Slf4j
public class LoginMock {

	@Autowired
	private WxMaService wxService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	protected StringRedisTemplate stringRedisTemplate;

	public static final String LOGIN_TOKEN_KEY = "X-Alumniassociation-Token";

	public DataMsg loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo,
			com.alumniassociation.api.common.xss.XssHttpServletRequestWrapper request) {
		wxLoginInfo = new WxLoginInfo();
		wxLoginInfo.setCode(UUID.randomUUID() + "");
		com.alumniassociation.api.dto.UserInfo userInfo = new com.alumniassociation.api.dto.UserInfo();
		userInfo.setNickName("江理小星");
		userInfo.setGender((byte) 1);
		userInfo.setAvatarUrl("/mock/pic");
		wxLoginInfo.setUserInfo(userInfo);
		String code = wxLoginInfo.getCode();
		if (code == null || wxLoginInfo.getUserInfo() == null) {
			return DataMsg.badArgument();
		}

		String sessionKey = null;
		String openId = null;
		try {
			sessionKey = "mockSessionKey" + UUID.randomUUID();
			openId = "mockOpenId" + UUID.randomUUID();
		} catch (Exception e) {
			// log.error("登录信息异常", e);
		}

		if (sessionKey == null || openId == null) {
			return DataMsg.fail();
		}

		UserInfo user = userInfoService.queryByOpenId(openId);
		if (user == null) {
			user = new UserInfo();
			user.setWechatImage(wxLoginInfo.getUserInfo().getAvatarUrl());
			user.setOpenId(openId);
			user.setSex(Integer.valueOf(wxLoginInfo.getUserInfo().getGender()));
			user.setNickName(wxLoginInfo.getUserInfo().getNickName());
			user.setLastLoginIp(IpUtil.getIpAddr(request));
			user.setSessionKey(sessionKey);
			userInfoService.insert(user);
		} else {
			user.setWechatImage(wxLoginInfo.getUserInfo().getAvatarUrl());
			user.setNickName(wxLoginInfo.getUserInfo().getNickName());
			user.setLastLoginIp(IpUtil.getIpAddr(request));
			user.setSessionKey(sessionKey);
			if (userInfoService.updateById(user) == 0) {
				return DataMsg.updatedDataFailed();
			}
		}
		String token = UserTokenManager.generateToken(user.getId());
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("token", token);
		result.put("userInfo", user);

		// 设置放置在redis的内容
		Token t = new Token();
		t.setUserId(user.getId());
		t.setRealName(user.getRealName());
		t.setMpOpenId(user.getOpenId());
		stringRedisTemplate.opsForValue().set("token:" + token, JsonUtils.objectToJson(t), 30, TimeUnit.DAYS); // 365天有效时间
		return DataMsg.ok(result);
	}
}
