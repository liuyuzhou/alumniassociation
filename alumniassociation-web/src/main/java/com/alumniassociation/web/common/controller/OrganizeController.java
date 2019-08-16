package com.alumniassociation.web.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniassociation.common.enumresource.StateEnum;
import com.alumniassociation.common.enumresource.TopMenuEnum;
import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.EnumBean;
import com.alumniassociation.common.utils.PageUtils;
import com.alumniassociation.common.utils.Query;
import com.alumniassociation.common.utils.R;
import com.alumniassociation.common.utils.StringUtil;
import com.alumniassociation.common.utils.ZtreeBean;
import com.alumniassociation.web.common.entity.Organize;
import com.alumniassociation.web.common.service.AreaService;
import com.alumniassociation.web.common.service.OrganizeService;
import com.alumniassociation.web.common.service.SysMenuService;


/**
 * 部门
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 17:39:31
 */
@Controller
@RequestMapping("organize")
public class OrganizeController {
	@Autowired
	private OrganizeService organizeService;
	@Autowired
    private SysMenuService sysMenuService;
	@Autowired
	private AreaService areaService;


    @RequestMapping("/list")
    public String list() {
        return "organize/list";
    }
	/**
	 * 列表
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("organize:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Organize> organizeList = organizeService.queryList(query);
		int total = organizeService.queryTotal(query);
        if (organizeList!=null&&organizeList.size()>0){
            for (int i = 0; i < organizeList.size(); i++) {
                if (TopMenuEnum.TopMenu.getCode().equals(organizeList.get(i).getParentOrgId())){
                    organizeList.get(i).setParentOrgName(TopMenuEnum.TopMenu.getDesc());
                    organizeList.get(i).setParentOrgId("-");
                }
            }
        }

		PageUtils pageUtil = new PageUtils(organizeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    @RequiresPermissions("organize:save")
    public String add(){
        return "/organize/add";
    }
    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping("/select")
    public R select() {

        List<Organize> organizeList = organizeService.getList();

        //添加顶级菜单
        Organize root = new Organize();

        root.setOrgId(TopMenuEnum.TopOrg.getCode());
        root.setOrgName(TopMenuEnum.TopOrg.getDesc());
        root.setParentOrgId("-1");
        root.setOpen(true);
        organizeList.add(root);
        List<ZtreeBean> ztreeBeans = new ArrayList<>();
        for (Organize organize : organizeList) {
            ZtreeBean tree = new ZtreeBean();
            tree.setId(organize.getOrgId() + "");
            tree.setpId(organize.getParentOrgId() + "");
            tree.setName(organize.getOrgName());
            tree.setOpen(organize.isOpen() + "");
            tree.setChkDisabled("false");
            ztreeBeans.add(tree);
        }

        return R.ok().put("data", ztreeBeans);
    }
    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("organize:update")
    public String edit(Model model, @PathVariable("id") String id){
		Organize organize = organizeService.queryObject(id);
		if ("3".equals(organize.getOrgType())){
		    String[] s = organize.getWmsCity().split(",");
		    if (s.length >=3 ){
                organize.setProvinceCode(s[0]);
                organize.setCityCode(s[1]);
                organize.setAreaCode(s[2]);
            }
        }
        model.addAttribute("model",organize);
        return "organize/edit";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{orgId}")
    @RequiresPermissions("organize:info")
    public R info(@PathVariable("orgId") String orgId){
        Organize organize = organizeService.queryObject(orgId);
        return R.ok().put("organize", organize);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存部门")
	@RequestMapping("/save")
	@RequiresPermissions("organize:save")
	public R save(@RequestBody Organize organize){
    	/*//判断部门编号是否存在
        Organize oldOrganize  = organizeService.queryByOrgCode(organize.getOrgCode());
        if (!StringUtil.isNullOrEmpty(oldOrganize)){
            return R.error("部门编号已存在");
        } 
    	
        String[] parentOrgIds = organize.getParentOrgIds();
        
        String parentOrgId = "";
        for(String parentId : parentOrgIds){
        	if(StringUtils.isNotBlank(parentId)){
        		parentOrgId=parentId;
        	}
        }
        
        organize.setParentOrgId(parentOrgId);*/
    	
    	//获取页面提交的区域Id集合
    	List<String> areaList = organize.getAreaCodes();
    	
    	if("1".equals(organize.getOrgType()) && StringUtil.isNullOrEmpty(areaList)){
    		return R.error("请选择区域");
    	}
    	
    	if(!StringUtil.isNullOrEmpty(areaList)){
    		String areaName = "";
        	String orgId = "";//被其他运营商占用的区域编码
        	String orgIds = "";//被其他运营商占用的区域编码集
        	StringBuilder orgCode = new StringBuilder("");
    		organize.setAreaCode(orgCode.deleteCharAt(orgCode.length()-1).toString());
    	}
        
        organizeService.save(organize);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改部门")
	@RequestMapping("/update")
	@RequiresPermissions("organize:update")
	public R update(@RequestBody Organize organize){
    	//获取页面提交的区域Id集合
    	List<String> areaList = organize.getAreaCodes();
    	
    	if("1".equals(organize.getOrgType()) && StringUtil.isNullOrEmpty(areaList)){
    		return R.error("请选择区域");
    	}
    	
    	if(!StringUtil.isNullOrEmpty(areaList)){
    		Organize o = organizeService.queryObject(organize.getOrgId());
        	//原有的经营区域集
        	String orgCode = o.getAreaCode();
        	List<String> codeList = new ArrayList<>();
        	if(!StringUtil.isEmpty(orgCode)){
        		String[] orgCodes = orgCode.split(",");
            	for (String codes : orgCodes) {
            		codeList.add(codes);
        		}
        	}
        	codeList = (List<String>) CollectionUtils.subtract(areaList, codeList);
        	String orgIds = "";
    		for (String areaId : areaList) {
    			orgIds = orgIds + areaId + ",";
    		}
    		
    		organize.setAreaCode(orgIds.substring(0, orgIds.length()-1));
    	}

		organizeService.update(organize);
		
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用部门")
    @RequestMapping("/enable")
    @RequiresPermissions("organize:update")
    public R enable(@RequestBody String[] ids){
        String stateValue= StateEnum.ENABLE.getCode();
		organizeService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用部门")
    @RequestMapping("/limit")
    @RequiresPermissions("organize:update")
    public R limit(@RequestBody String[] ids){
        String stateValue=StateEnum.LIMIT.getCode();
		organizeService.updateState(ids,stateValue);
        return R.ok();
    }

	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除部门")
	@RequestMapping("/delete")
	@RequiresPermissions("organize:delete")
	public R delete(@RequestBody String[] orgIds){

        for (String orgId : orgIds){
            organizeService.delete(orgId);
        }
		
		return R.ok();
	}
	
    
    /**
     * 获取下级部门
     */
    @ResponseBody
    @RequestMapping("normalList/{parentOrgId}")
    public R normalList(@PathVariable String parentOrgId) {
        List<EnumBean> list = new ArrayList<>();
        List<Organize> orgList = organizeService.queryByParentId(parentOrgId);
        if (orgList != null && orgList.size() > 0) {
            for (int i = 0; i < orgList.size(); i++) {
                EnumBean bean = new EnumBean();
                bean.setCode(orgList.get(i).getOrgId());
                bean.setValue(orgList.get(i).getOrgName());
                list.add(bean);
            }
        }
        return R.ok().put("data", list);
    }

    /**
     * 列表数据
     */
    @ResponseBody
    @RequestMapping("/selectList")
    public R selectList(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Organize> organizeList = organizeService.selectList(query);
        return R.ok().put("data", organizeList);
    }
}
