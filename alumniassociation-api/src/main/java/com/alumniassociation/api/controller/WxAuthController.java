package com.alumniassociation.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.api.common.api.annotation.IgnoreAuth;
import com.alumniassociation.api.common.api.annotation.LoginUser;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.dto.WxLoginInfo;
import com.alumniassociation.api.entity.Token;
import com.alumniassociation.api.service.UserTokenManager;
import com.alumniassociation.api.util.IpUtil;
import com.alumniassociation.common.utils.JsonUtils;
import com.alumniassociation.user.entity.UserInfo;
import com.alumniassociation.user.service.UserInfoService;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


/**
 * 鉴权服务
 */
@RestController
@Api(tags = "微信小程序鉴权服务")
@RequestMapping("/szxyh/wx/auth")
@Validated
public class WxAuthController {
	
    @Autowired
    private WxMaService wxService;
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    
    public static final String LOGIN_TOKEN_KEY = "X-Alumniassociation-Token";

    /**
     * 微信登录
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request     请求对象
     * @return 登录结果
     */
    @IgnoreAuth
    @ApiOperation(value = "微信授权登陆", notes = "通过微信小程序确认授权并登陆")
	@ApiResponse(response=DataMsg.class, code = 200, message = "接口返回对象参数")
    @PostMapping("login_by_weixin")
    public DataMsg loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        String code = wxLoginInfo.getCode();
        if (code == null || wxLoginInfo.getUserInfo() == null) {
            return DataMsg.badArgument();
        }

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
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
        
        //设置放置在redis的内容
        Token t = new Token();
        t.setUserId(user.getId());
        t.setRealName(user.getRealName());
        t.setMpOpenId(user.getOpenId());
        stringRedisTemplate.opsForValue().set("token:"+token, JsonUtils.objectToJson(t), 365, TimeUnit.DAYS); // 365天有效时间
        return DataMsg.ok(result);
    }

    @PostMapping("logout")
    public DataMsg logout(@LoginUser Integer userId, HttpServletRequest request) {
    	String token = request.getHeader(LOGIN_TOKEN_KEY);
    	stringRedisTemplate.delete("token:"+ token);
        return DataMsg.ok();
    }

}
