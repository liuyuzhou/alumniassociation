package com.alumniassociation.web.news;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniassociation.news.entity.News;
import com.alumniassociation.news.service.NewsService;
import com.alumniassociation.common.enumresource.StateEnum;
import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.PageUtils;
import com.alumniassociation.common.utils.Query;
import com.alumniassociation.common.utils.R;
import com.alumniassociation.web.common.shiro.ShiroUtils;


/**
 * 新闻资讯
 * 
 * @author lewp
 * @email kunda@qq.com
 * @date 2018-10-16 09:30:49
 */
@Controller
@RequestMapping("news")
public class NewsController {
	@Autowired
	private NewsService newsService;
	
    /**
     * 跳转到列表页
     */
    @RequestMapping("/list")
    @RequiresPermissions("news:list")
    public String list() {
        return "news/list";
    }
    
	/**
	 * 列表数据
	 */
    @ResponseBody
	@RequestMapping("/listData")
	@RequiresPermissions("news:list")
	public R listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<News> newsList = newsService.getList(query);
		int total = newsService.getCount(query);
		
		PageUtils pageUtil = new PageUtils(newsList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

    /**
     * 跳转到新增页面
     **/
    @RequestMapping("/add")
    public String add(){
        return "news/add";
    }

    /**
     *   跳转到修改页面
     **/
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id){
		News news = newsService.get(id);
        model.addAttribute("model",news);
        return "news/edit";
    }

	/**
	 * 信息
	 */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        News news = newsService.get(id);
        return R.ok().put("news", news);
    }

    /**
	 * 保存
	 */
    @ResponseBody
    @SysLog("保存新闻资讯")
	@RequestMapping("/save")
	public R save(@RequestBody News news){
    	news.setReleaseUserId(ShiroUtils.getUserId());
		news.setCreateUserId(ShiroUtils.getUserId());
		newsService.save(news);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
    @ResponseBody
    @SysLog("修改新闻资讯")
	@RequestMapping("/update")
	public R update(@RequestBody News news){
    	news.setUpdateUserId(ShiroUtils.getUserId());
		newsService.update(news);
		return R.ok();
	}

    /**
     * 启用
     */
    @ResponseBody
    @SysLog("启用新闻资讯")
    @RequestMapping("/enable")
    public R enable(@RequestBody String[] ids){
        String stateValue=StateEnum.ENABLE.getCode();
		newsService.updateState(ids,stateValue);
        return R.ok();
    }
    /**
     * 禁用
     */
    @ResponseBody
    @SysLog("禁用新闻资讯")
    @RequestMapping("/limit")
    public R limit(@RequestBody String[] ids){
        String stateValue=StateEnum.LIMIT.getCode();
		newsService.updateState(ids,stateValue);
        return R.ok();
    }
	
	/**
	 * 删除
	 */
    @ResponseBody
    @SysLog("删除新闻资讯")
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		newsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
