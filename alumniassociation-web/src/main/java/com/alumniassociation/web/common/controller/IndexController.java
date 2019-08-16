package com.alumniassociation.web.common.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniassociation.common.utils.Constant;
import com.alumniassociation.common.utils.Query;
import com.alumniassociation.common.utils.R;


@Controller
@RequestMapping("index")
public class IndexController extends AbstractController{
	
	
	
	@RequestMapping(value="/ordersAnalysis",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public R ordersAnalysis(String startDate_orders,String endDate_orders) throws ParseException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
//		System.out.println("startDate_orders = "+startDate_orders);
//		System.out.println("endDate_orders = "+endDate_orders);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date finalEndDate = sdf.parse(endDate_orders);
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();  
        c.setTime(finalEndDate);  
        c.add(Calendar.DAY_OF_MONTH, 1);
        finalEndDate = c.getTime();
        
		
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("orgIds", getUser().getChildOrgIds());
		}
		
		params.put("startDate_orders", startDate_orders);
//		params.put("endDate_orders", endDate_orders);
		params.put("endDate_orders", f.format(finalEndDate));
		
		//查询列表数据
		Query query = new Query(params);
		
//		System.out.println("orgIds = = = "+getUser().getChildOrgIds());
		
//		System.out.println("salesData = "+ordersData);
//		System.out.println("series = "+ordersData.get("series"));
//		System.out.println("category = "+ordersData.get("category"));
		
		return R.ok();
		
	}
	
    @RequestMapping(value="/salesAnalysis",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public R salesAnalysis(String startDate_sales,String endDate_sales) throws ParseException {
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date finalEndDate = sdf.parse(endDate_sales);
       
        Format f = new SimpleDateFormat("yyyy-MM-dd");
       
        Calendar c = Calendar.getInstance();  
        c.setTime(finalEndDate);  
        c.add(Calendar.DAY_OF_MONTH, 1);	//利用Calendar 实现 Date日期+1天  
        
        finalEndDate = c.getTime();
    	
    	//只有超级管理员，才能查看所有管理员列表
  		if(getUserId() != Constant.SUPER_ADMIN){
  			params.put("orgIds", getUser().getChildOrgIds());
  		}
  		
  		params.put("startDate_sales", startDate_sales);
//    	params.put("endDate_sales", endDate_sales);
    	params.put("endDate_sales", f.format(finalEndDate));
    	
		//查询列表数据
        Query query = new Query(params);
    	
//    	System.out.println("orgIds = = = "+getUser().getChildOrgIds());
    	
//    	System.out.println("salesData = "+salesData);
//    	System.out.println("series = "+salesData.get("series"));
//    	System.out.println("category = "+salesData.get("category"));
    	
    	return R.ok();
    	
    }
     
}