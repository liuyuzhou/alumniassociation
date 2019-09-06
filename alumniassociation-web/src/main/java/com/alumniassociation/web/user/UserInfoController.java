package com.alumniassociation.web.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.PageUtils;
import com.alumniassociation.common.utils.Query;
import com.alumniassociation.common.utils.R;
import com.alumniassociation.user.entity.UserInfo;
import com.alumniassociation.user.service.UserInfoService;

@Controller
@RequestMapping("userinfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	/**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    public String list() {
        return "userinfo/list";
    }
    
	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserInfo> userInfoList = userInfoService.getList(query);
		int total = userInfoService.getCount(query);
		
		PageUtils pageUtil = new PageUtils(userInfoList, total, query.getLimit(), query.getPage());
		return R.ok().put("page",  pageUtil);
    }
    
    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    public String add(){
        return "userinfo/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
    	UserInfo userInfo = userInfoService.getUserInfoById(id);
        model.addAttribute("model",userInfo);
        return "userinfo/edit";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
    	UserInfo userInfo = userInfoService.getUserInfoById(id);
        return R.ok().put("userInfo", userInfo);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存校友信息")
	@RequestMapping("/save")
	public R save(@RequestBody UserInfo userInfo){
		userInfoService.insert(userInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改校友信息")
	@RequestMapping("/update")
	public R update(@RequestBody UserInfo userInfo){
		userInfoService.updateById(userInfo);
		return R.ok();
	}
	
}
