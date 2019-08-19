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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.api.common.api.annotation.LoginUser;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.authentication.entity.AuthenticationInfo;
import com.alumniassociation.authentication.service.AuthenticationInfoService;
import com.alumniassociation.user.entity.UserInfo;
import com.alumniassociation.user.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;

/**
 * 用户信息
 * 
 * @author lyz
 *
 */
@Api(value = "/api", tags = "用户信息接口")
@RestController
@RequestMapping("/szxyh/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private AuthenticationInfoService authenticationInfoService;

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ApiOperation(value = "取得所有用户信息", notes = "展示所有信息")
	@GetMapping(value = "/listAll")
	public List<UserInfo> getUserInfoList() {
		return userInfoService.findAll();
	}

	/**
	 * @ApiOperation(value = "添加一个用户", notes = "添加一个用户")
	 * @PostMapping(value = "/add", produces = {
	 *                    "application/json;charset=UTF-8" }) public UserInfo
	 *                    addUser(@RequestParam("realName") String
	 *                    realName, @RequestParam("sex") Integer
	 *                    sex, @RequestParam("phoneNum") Long
	 *                    phoneNum, @RequestParam("entranceTime") Date
	 *                    entranceTime, @RequestParam("graduationTime") Date
	 *                    graduationTime, @RequestParam("college") String
	 *                    college, @RequestParam("major") String
	 *                    major, @RequestParam("currLocation") String
	 *                    currLocation, String industrySkill, String hobby,
	 *                    String personalProfile, @RequestParam("wechatImage")
	 *                    String wechatImage, String openId) { UserInfo userInfo
	 *                    = new UserInfo(); userInfo.setRealName(realName);
	 *                    userInfo.setSex(sex); userInfo.setPhoneNum(phoneNum);
	 *                    userInfo.setEntranceTime(entranceTime);
	 *                    userInfo.setGraduationTime(graduationTime);
	 *                    userInfo.setCollege(college);
	 *                    userInfo.setMajor(major); System.out.println(
	 *                    "major is:" + major);
	 *                    userInfo.setCurrLocation(currLocation);
	 *                    userInfo.setIndustrySkill(industrySkill);
	 *                    userInfo.setHobby(hobby);
	 *                    userInfo.setPersonalProfile(personalProfile); // TODO
	 *                    此时的wechatImage是一个图里路径，需要根据 //
	 *                    wechatImage路径将图片下载，并保存到指定目录下，返回存放路径放入数据库
	 *                    userInfo.setWechatImage(wechatImage); if (openId == ""
	 *                    || openId == null) { openId = " "; }
	 *                    userInfo.setOpenId(openId); Date dateNow = new Date();
	 *                    userInfo.setCreateTime(dateNow);
	 *                    userInfo.setUpdateTime(dateNow); return
	 *                    userInfoResitory.save(userInfo); }
	 **/
	/**
	 * 
	 * 校友自主在小程序登陆注册的 需要完善校友个人基本信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "校友信息完善", notes = "填写基本个人信息，方便进行校友认证")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Alumniassociation-Token", value = "用户登陆后的令牌", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "realName", value = "真实姓名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "sex", value = "性别", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "phoneNum", value = "电话号码", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "entranceTime", value = "入学时间", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "graduationTime", value = "毕业时间", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "college", value = "学院名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "major", value = "专业名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "currLocation", value = "当前所在地", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "industrySkill", value = "专业技能", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "hobby", value = "兴趣爱好", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "personalProfile", value = "个人说明", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "wechatImage", value = "个人头像", required = true, dataType = "string", paramType = "query") })
	@PostMapping(value = "/update")
	public DataMsg updateUser(@LoginUser Integer id, @RequestParam("realName") String realName,
			@RequestParam("sex") Integer sex, @RequestParam("phoneNum") Long phoneNum,
			@RequestParam("entranceTime") String entranceTime, @RequestParam("graduationTime") String graduationTime,
			@RequestParam("college") String college, @RequestParam("major") String major,
			@RequestParam("currLocation") String currLocation, @RequestParam("industrySkill") String industrySkill,
			@RequestParam("hobby") String hobby, @RequestParam("personalProfile") String personalProfile,
			@RequestParam("wechatImage") String wechatImage) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setRealName(realName);
		userInfo.setSex(sex);
		userInfo.setPhoneNum(phoneNum);
		userInfo.setEntranceTime(entranceTime);
		userInfo.setGraduationTime(graduationTime);
		userInfo.setCollege(college);
		userInfo.setMajor(major);
		userInfo.setIndustrySkill(industrySkill);
		userInfo.setCurrLocation(currLocation);
		userInfo.setHobby(hobby);
		userInfo.setPersonalProfile(personalProfile);
		userInfo.setWechatImage(wechatImage);
		userInfo.setUpdateTime(new Date());
		userInfoService.updateById(userInfo);
		return DataMsg.ok();
	}

	@ApiOperation(value = "查找当前登陆用户信息", notes = "获取用户详细用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Alumniassociation-Token", value = "用户登陆后的令牌", required = true, dataType = "string", paramType = "header"), })
	@GetMapping(value = "/get")
	public DataMsg getCurrUserInfo(@LoginUser Integer id) {
		return DataMsg.ok(userInfoService.getUserInfoById(id));
	}

	@ApiOperation(value = "查找指定用户", notes = "查找指定用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "query"), })
	@GetMapping(value = "/find/{id}")
	public DataMsg getUserInfo(@PathVariable("id") Integer id) {
		return DataMsg.ok(userInfoService.getUserInfoById(id));
	}

	@ApiOperation(value = "删除指定用户", notes = "删除指定用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "query"), })
	@DeleteMapping(value = "/delete/{id}")
	public DataMsg deleteUserInfo(@PathVariable("id") Integer id) {
		userInfoService.deleteUserInfo(id);
		return DataMsg.ok();

	}

	/**
	 * 后台导入的校友数据， 需要进入校友会小程序内激活，用户信息绑定openid
	 * 
	 * @param userName
	 * @param major
	 * @param graduationTime
	 * @param openId
	 * @return
	 */
	@ApiOperation(value = "用户激活", notes = "指定用户激活")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "realName", value = "用户名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "major", value = "专业名称", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "graduationTime", value = "毕业时间", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "openId", value = "微信openid", required = true, dataType = "string", paramType = "query"), })
	@PostMapping(value = "/activateUser")
	public DataMsg activateUser(@LoginUser Integer userId, @RequestParam("realName") String realName,
			@RequestParam("major") String major, @RequestParam("graduationTime") String graduationTime,
			@RequestParam("openId") String openId) {
		// 判断待激活用户是否在表中，根据userName、major、graduationTime三个字段唯一识别
		List<UserInfo> userInfoList = userInfoService.findByRealNameAndMajorAndGraduationTime(realName, major, graduationTime);

		if (userInfoList == null) {
			return DataMsg.error("该校友信息未录入，无法激活，请自主完善信息");
		}
		if (userInfoList.size() != 1) {
			return DataMsg.error("数据异常");
		}
		UserInfo userInfo = userInfoList.get(0);
		// 判断待激活用户是否已经激活，已经激活的不需要再激活
		if (userInfo.getUserStatus() == Boolean.TRUE) {
			return DataMsg.error("该校友已被激活，无法再次激活");
		}

		// 当前登陆用户
		UserInfo curUser = userInfoService.getUserInfoById(userId);
		// 做数据迁移 已存在的真实头像，则覆盖微信头像
		if (!StringUtil.isEmpty(userInfo.getWechatImage())) {
			curUser.setWechatImage(curUser.getWechatImage());
		}
		curUser.setCollege(userInfo.getCollege());
		curUser.setCurrLocation(userInfo.getCurrLocation());
		curUser.setEntranceTime(userInfo.getEntranceTime());
		curUser.setBirthday(userInfo.getBirthday());
		curUser.setGraduationTime(userInfo.getGraduationTime());
		curUser.setHobby(userInfo.getHobby());
		curUser.setIndustrySkill(userInfo.getIndustrySkill());
		curUser.setMajor(userInfo.getMajor());
		curUser.setPersonalProfile(userInfo.getPersonalProfile());
		curUser.setRealName(userInfo.getRealName());
		curUser.setPhoneNum(userInfo.getPhoneNum());
		curUser.setScore(userInfo.getScore());
		curUser.setSex(userInfo.getSex());
		// 激活用户并更新openId字段
		curUser.setUserStatus(Boolean.TRUE);
		userInfoService.activateUser(curUser, userInfo.getId());
		return DataMsg.ok();
	}

	/**
	 * 协助认证
	 * 
	 * @param certifiedUserId
	 * @param authenticatorId
	 */
	@ApiOperation(value = "协助校友认证", notes = "当前登陆人为已认证校友， 协助待认证校友进行认证操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "authenticatorId", value = "待认证校友ID", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "X-Alumniassociation-Token", value = "校友登陆token", required = true, dataType = "string", paramType = "henader"), })
	@PostMapping(value = "/certifie")
	public DataMsg userCertifie(@LoginUser Integer certifiedUserId, // 自动注入登陆用户的userid
			@RequestParam("authenticatorId") Integer authenticatorId) {
		List<AuthenticationInfo> authenticationInfoList = authenticationInfoService
				.findByAuthenticatorId(authenticatorId);
		if (authenticationInfoList.size() >= 2) {
			return DataMsg.error("已经存在大于两条认证信息，无需添加更多");
		}
		addAuthenticationInfo(certifiedUserId, authenticatorId);
		List<AuthenticationInfo> infoList = authenticationInfoService.findByAuthenticatorId(authenticatorId);
		if (infoList.size() < 2) {
			return DataMsg.error("不足两条认证信息， 未达到完成认证条件");
		}
		UserInfo userInfo = userInfoService.getUserInfoById(authenticatorId);
		userInfo.setUserStatus(Boolean.TRUE);
		userInfo.setUpdateTime(new Date());
		userInfoService.updateById(userInfo);
		return DataMsg.ok();
	}

	@ApiOperation(value = "添加一条用户认证信息", notes = "添加一条用户认证信息")
	@PostMapping(value = "/addAuthenticationInfo")
	public DataMsg addAuthenticationInfo(@RequestParam("certifiedUserId") Integer certifiedUserId,
			@RequestParam("authenticatorId") Integer authenticatorId) {
		AuthenticationInfo authenticationInfo = new AuthenticationInfo();
		authenticationInfo.setCertifiedUserId(certifiedUserId);
		authenticationInfo.setAuthenticatorId(authenticatorId);
		authenticationInfo.setCertifieTime(new Date());
		authenticationInfoService.addAuthenticationInfo(authenticationInfo);
		return DataMsg.ok();
	}

}
