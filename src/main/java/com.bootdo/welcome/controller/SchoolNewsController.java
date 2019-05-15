package com.bootdo.welcome.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.welcome.domain.SchoolNewsDO;
import com.bootdo.welcome.service.SchoolNewsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-15 17:11:06
 */
 
@Controller
@RequestMapping("/welcome/schoolNews")
public class SchoolNewsController {
	@Autowired
	private SchoolNewsService schoolNewsService;
	
	@GetMapping()
	@RequiresPermissions("welcome:schoolNews:schoolNews")
	String SchoolNews(){
	    return "welcome/schoolNews/schoolNews";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("welcome:schoolNews:schoolNews")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolNewsDO> schoolNewsList = schoolNewsService.list(query);
		int total = schoolNewsService.count(query);
		PageUtils pageUtils = new PageUtils(schoolNewsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("welcome:schoolNews:add")
	String add(){
	    return "welcome/schoolNews/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("welcome:schoolNews:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolNewsDO schoolNews = schoolNewsService.get(id);
		model.addAttribute("schoolNews", schoolNews);
	    return "welcome/schoolNews/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("welcome:schoolNews:add")
	public R save( SchoolNewsDO schoolNews){
		if(schoolNewsService.save(schoolNews)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("welcome:schoolNews:edit")
	public R update( SchoolNewsDO schoolNews){
		schoolNewsService.update(schoolNews);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("welcome:schoolNews:remove")
	public R remove( Long id){
		if(schoolNewsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("welcome:schoolNews:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolNewsService.batchRemove(ids);
		return R.ok();
	}
	
}
