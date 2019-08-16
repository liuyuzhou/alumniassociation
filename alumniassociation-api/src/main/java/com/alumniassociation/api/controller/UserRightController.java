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

import com.alumniassociation.user.entity.UserRight;
import com.alumniassociation.user.resitory.UserRightResitory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户权限
 * 
 * @author lyz
 *
 */
@Api(value = "/api", tags = "用户权限接口")
@RestController
@RequestMapping("/szxyh/userright")
public class UserRightController {
	@Autowired
	private UserRightResitory userRightResitory;

	@ApiOperation(value = "取得所有用户权限", notes = "展示所有用户权限")
	@GetMapping(value = "/listAllProJurisdiction")
	public List<UserRight> getUserRightList() {
		return userRightResitory.findAll();
	}

	@ApiOperation(value = "取得指定用户所有权限", notes = "取得指定用户所有权限 ")
	@GetMapping(value = "/getUserRightByUserId")
	public List<UserRight> getUserRightByUserId(@RequestParam("userId") Integer userId) {
		return userRightResitory.findByUserId(userId);
	}

	@ApiOperation(value = "用户权限申请", notes = "用户权限申请")
	@PostMapping(value = "/addUserRight")
	public UserRight addUserRight(@RequestParam("jurisdictionId") Integer jurisdictionId,
			@RequestParam("jurisdictionName") String jurisdictionName,
			@RequestParam("userId") Integer userId) {
		UserRight userRight = new UserRight();
		userRight.setJurisdictionId(jurisdictionId);
		userRight.setJurisdictionName(jurisdictionName);
		userRight.setUserId(userId);
		Date dateNow = new Date();
		userRight.setCreateTime(dateNow);
		userRight.setUpdateTime(dateNow);
		return userRightResitory.save(userRight);
	}

	@ApiOperation(value = "用户权限审核", notes = "用户权限审核")
	@PutMapping(value = "/authUserRight/{id}")
	public UserRight authUserRight(@PathVariable("id") Integer id,
			@RequestParam("jurisdictionId") Integer jurisdictionId,
			@RequestParam("jurisdictionName") String jurisdictionName, @RequestParam("userId") Integer userId,
			@RequestParam("authUserId") Integer authUserId, @RequestParam("jurStatus") Boolean jurStatus) {
		UserRight userRight = new UserRight();
		userRight.setId(id);
		userRight.setJurisdictionId(jurisdictionId);
		userRight.setJurisdictionName(jurisdictionName);
		userRight.setUserId(userId);
		userRight.setAuthUserId(authUserId);
		userRight.setJurStatus(jurStatus);
		Date dateNow = new Date();
		userRight.setCreateTime(dateNow);
		userRight.setUpdateTime(dateNow);
		return userRightResitory.save(userRight);
	}

	@ApiOperation(value = "用户权限禁用", notes = "用户权限禁用")
	@PutMapping(value = "/forbittenUserRight/{id}")
	public UserRight forbittenUserRight(@PathVariable("id") Integer id, @RequestParam("authUserId") Integer authUserId,
			@RequestParam("jurStatus") Boolean jurStatus) {
		UserRight userRight = new UserRight();
		userRight.setAuthUserId(authUserId);
		userRight.setJurStatus(jurStatus);
		Date dateNow = new Date();
		userRight.setCreateTime(dateNow);
		userRight.setUpdateTime(dateNow);
		return userRightResitory.save(userRight);
	}

	@ApiOperation(value = "查找指定权限", notes = "查找指定权限")
	@GetMapping(value = "/findUserRight/{id}")
	public UserRight getUserRight(@PathVariable("id") Integer id) {
		return userRightResitory.findOne(id);
	}

	@ApiOperation(value = "删除指定用户权限", notes = "删除指定用户权限")
	@DeleteMapping(value = "/deleteProJurisdiction/{id}")
	public void deleteUserRight(@PathVariable("id") Integer id) {
		userRightResitory.delete(id);
	}
}
