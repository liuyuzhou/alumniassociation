package com.alumniassociation.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumniassociation.activate.entity.Activate;
import com.alumniassociation.activate.entity.ActivateInfo;
import com.alumniassociation.activate.entity.ActivatePersonnelList;
import com.alumniassociation.activate.entity.ActivateReviewDetail;
import com.alumniassociation.activate.service.ActivateInfoService;
import com.alumniassociation.activate.service.ActivatePersonnelListService;
import com.alumniassociation.activate.service.ActivateReviewDetailService;
import com.alumniassociation.api.common.api.annotation.LoginUser;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.common.dao.AntInstBaseDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;

/**
 * 活动信息
 * 
 * @author lyz
 *
 */
@Api(tags = "活动信息接口")
@RestController
@RequestMapping("/szxyh/activateInfo")
public class ActivateInfoController {
	
	@Autowired
	private ActivateInfoService activateInfoService;
	
	@Autowired
	private ActivatePersonnelListService activatePersonnelListService;
	
	@Autowired
	private ActivateReviewDetailService activateReviewDetailService;

	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ApiOperation(value = "取得所有活动信息", notes = "展示所有活动")
	@GetMapping(value = "/listAllActivateInfo")
	public DataMsg getActivateInfoList() {
		return DataMsg.ok(activateInfoService.findAll());
	}

	@ApiOperation(value = "取得某个用户参加的所有活动信息", notes = "取得某个用户参加的所有活动信息")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "X-Alumniassociation-Token", value = "用户登陆后的令牌", required = true, dataType = "string", paramType = "header"),
	})
	@GetMapping(value = "/listUserAllActivateInfo")
	public DataMsg getActivateInfoByUserId(@LoginUser Integer userId) {
		List<ActivatePersonnelList> activatePersonnelLists = activatePersonnelListService.findByUserId(userId);
		Activate activate = new Activate();
		if (activatePersonnelLists == null || activatePersonnelLists.size() == 0) {
			return DataMsg.ok(activate);
		}
		List<ActivateInfo> activateInfos = new ArrayList<ActivateInfo>();
		for (ActivatePersonnelList activatePersonnelList : activatePersonnelLists) {
			Integer activateId = activatePersonnelList.getActivateId();
			String userName = activatePersonnelList.getRealName();
			ActivateInfo activateInfo = activateInfoService.getActivateInfoById(activateId, userId);
			activate.setUserName(userName);
			activateInfos.add(activateInfo);
		}
		activate.setUserId(userId);
		activate.setActivateInfos(activateInfos);
		return DataMsg.ok(activate);
	}

	@ApiOperation(value = "创建校友活动", notes = "对校友创建的校友活动信息进行数据保存")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "activateImage", value = "活动宣传图", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "address", value = "活动地点", required = true, dataType = "string", paramType = "body"),
		 //@ApiImplicitParam(name = "meetingPlace", value = "出发集合地点", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "actTime", value = "活动时间", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "endTime", value = "活动结束时间", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "actCreator", value = "发起人", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "content", value = "活动内容", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "participateNum", value = "参加人数", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "cost", value = "报名费用", required = true, dataType = "string", paramType = "body"),
	})
	@PostMapping(value = "/addActivate")
	public DataMsg addActivate(@RequestBody ActivateInfo activateInfo) {
		if(StringUtil.isEmpty(activateInfo.getTitle()) || StringUtil.isEmpty(activateInfo.getActivateImage())
				|| StringUtil.isEmpty(activateInfo.getAddress()) || StringUtil.isEmpty(activateInfo.getActivateImage())
				|| activateInfo.getActTime() == null || activateInfo.getEndTime() == null
				|| StringUtil.isEmpty(activateInfo.getActCreator()) || StringUtil.isEmpty(activateInfo.getContent())
				|| activateInfo.getParticipateNum() == null || activateInfo.getParticipateNum() == 0
				|| activateInfo.getCost() == null || activateInfo.getCost() == 0){
			return DataMsg.badArgument();
		}
		Date dateNow = antInstBaseDao.getSysDate();
		activateInfo.setStatus("0");
		activateInfo.setCreateTime(dateNow);
		activateInfo.setUpdateTime(dateNow);
		activateInfoService.addActivateInfo(activateInfo);
		return DataMsg.ok();
	}

	@ApiOperation(value = "校友活动信息修改", notes = "完善活动信息保存")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "activateImage", value = "活动宣传图", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "boardingLocation", value = "上车地点", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "actTime", value = "活动时间", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "endTime", value = "活动结束时间", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "act_creator", value = "发起人", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "content", value = "活动内容", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "participateNum", value = "允许报名总人数", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "cost", value = "报名费用", required = true, dataType = "string", paramType = "body"),
	})
	@PutMapping(value = "/updateActivate")
	public DataMsg updateActivate(@RequestBody ActivateInfo activateInfo) {
		if(activateInfo.getActivateId() == null || activateInfo.getActivateId() == 0){
			return DataMsg.badArgument();
		}
		activateInfo.setUpdateTime(antInstBaseDao.getSysDate());
		activateInfoService.updateActivate(activateInfo);
		return DataMsg.ok();
	}

	@ApiOperation(value = "查找指定活动记录", notes = "查找指定活动记录")
	@GetMapping(value = "/find/{activateId}")
	public DataMsg getUserInfo(@PathVariable("activateId") Integer activateId, @LoginUser Integer userId) {
		return DataMsg.ok(activateInfoService.getActivateInfoById(activateId, userId));
	}

	@ApiOperation(value = "删除指定活动", notes = "删除指定活动")
	@DeleteMapping(value = "/delete/{activateId}")
	public DataMsg deleteUserInfo(@PathVariable("activateId") Integer activateId) {
		activateInfoService.deleteActivateInfo(activateId);
		return DataMsg.ok();
	}

	@ApiOperation(value = "活动报名", notes = "活动报名")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "activateId", value = "活动ID", required = true, dataType = "int", paramType = "body"),
		 @ApiImplicitParam(name = "isDrive", value = "是否开车", required = true, dataType = "boolean", paramType = "body"),
		 @ApiImplicitParam(name = "isFamily", value = "是否携带家属", required = true, dataType = "boolean", paramType = "body"),
		 @ApiImplicitParam(name = "boardingLocation", value = "上车地点", required = true, dataType = "string", paramType = "body"),
		 @ApiImplicitParam(name = "isManned", value = "是否可以载人", required = true, dataType = "boolean", paramType = "body"),
	})
	@PostMapping(value = "/activateSignUp")
	public DataMsg activateSignUp(@RequestBody ActivatePersonnelList activatePersonnelList,
			@LoginUser Integer userId) {
		activatePersonnelList.setUserId(userId);
		activatePersonnelList.setIsParticipate("1");
		activatePersonnelListService.addActivatePersonnelList(activatePersonnelList);
		return DataMsg.ok();
	}
	private AntInstBaseDao antInstBaseDao;

	@ApiOperation(value = "取消报名", notes = "取消报名")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "activateId", value = "活动ID", required = true, dataType = "int", paramType = "query"),
	})
	@PostMapping(value = "/activateCancel")
	public DataMsg activateCancel(@RequestParam("activateId") Integer activateId,
			@LoginUser Integer userId) {
		ActivatePersonnelList activatePersonnelList = new ActivatePersonnelList();
		activatePersonnelList.setActivateId(activateId);
		activatePersonnelList.setUserId(userId);
		activatePersonnelList.setIsParticipate("0");
		activatePersonnelListService.cacelJoinedActivate(activatePersonnelList);
		return DataMsg.ok();
	}

	@ApiOperation(value = "活动评论", notes = "活动评论")
	@ApiImplicitParams({ 
		 @ApiImplicitParam(name = "activateId", value = "活动ID", required = true, dataType = "int", paramType = "body"),
		 @ApiImplicitParam(name = "comment", value = "评论内容", required = true, dataType = "string", paramType = "body"),
	})
	@PostMapping(value = "/addActivateReview")
	public DataMsg addActivateReview(@RequestBody ActivateReviewDetail activateReviewDetail, @LoginUser Integer userId) {
		activateReviewDetail.setUserId(userId);
		activateReviewDetail.setCreateTime(antInstBaseDao.getSysDate());
		activateReviewDetailService.addActivateReview(activateReviewDetail);
		return DataMsg.ok();
	}

	@ApiOperation(value = "活动参与人列表", notes = "活动参与人列表")
	@GetMapping(value = "/personnelLists/{activateId}")
	public DataMsg personnelLists(@PathVariable("activateId") Integer activateId) {
		ActivateInfo activateInfo = activateInfoService.getActivateInfoById(activateId, null);
		if (activateInfo == null) {
			// 异常处理
		}
		String activateTitle = activateInfo.getTitle();
		List<Map<String, Object>> lists = activatePersonnelListService.getJoinedPersonnelListByActivateId(activateId);
		if (lists == null || lists.size() == 0) {
			// 异常处理
		}
		Activate activate = new Activate();
		activate.setActivateId(activateId);
		activate.setActivateTitle(activateTitle);
		activate.setActivatePersonnelLists(lists);
		return DataMsg.ok(activate);
	}

	@ApiOperation(value = "活动回顾", notes = "活动回顾")
	@GetMapping(value = "/reviewList/{activateId}")
	public DataMsg reviewList(@PathVariable("activateId") Integer activateId) {
		ActivateInfo activateInfo = activateInfoService.getActivateInfoById(activateId, null);
		if (activateInfo == null) {
			// 异常处理
		}
		String activateTitle = activateInfo.getTitle();
		List<ActivateReviewDetail> lists = activateReviewDetailService.findByActivateId(activateId);
		if (lists == null || lists.size() == 0) {
			// 异常处理
		}
		Activate activate = new Activate();
		activate.setActivateId(activateId);
		activate.setActivateTitle(activateTitle);
		activate.setActivateReviewDetails(lists);
		return DataMsg.ok(activate);
	}

}
