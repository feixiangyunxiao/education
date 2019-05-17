package com.bootdo.welcome.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.welcome.domain.MycarouselDO;
import com.bootdo.welcome.service.MycarouselService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-14 15:29:13
 */
 
@Controller
@RequestMapping("/welcome/mycarousel")
public class MycarouselController {
	@Autowired
	private MycarouselService mycarouselService;
	
	@GetMapping()
	@RequiresPermissions("welcome:mycarousel:mycarousel")
	String Mycarousel(){
	    return "welcome/mycarousel/mycarousel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("welcome:mycarousel:mycarousel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MycarouselDO> mycarouselList = mycarouselService.list(query);
		int total = mycarouselService.count(query);
		PageUtils pageUtils = new PageUtils(mycarouselList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("welcome:mycarousel:add")
	String add(){
	    return "welcome/mycarousel/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("welcome:mycarousel:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MycarouselDO mycarousel = mycarouselService.get(id);
		model.addAttribute("mycarousel", mycarousel);
	    return "welcome/mycarousel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("welcome:mycarousel:add")
	public R save(MycarouselDO mycarousel){
		/*String suffix = picfile.getOriginalFilename().substring(picfile.getOriginalFilename().lastIndexOf("."));
		String prefix = UUID.randomUUID().toString();
		String fileName = "E:/Download/" + prefix + suffix;
		try {
			picfile.transferTo(new File(fileName));
			mycarousel.setPicname(fileName);
			if(mycarouselService.save(mycarousel)>0){
				return R.ok();
			}
		} catch (IOException e) {
			System.out.println("出错了");
			e.printStackTrace();
		}*/
		if(mycarouselService.save(mycarousel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("welcome:mycarousel:edit")
	public R update( MycarouselDO mycarousel){
		mycarouselService.update(mycarousel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("welcome:mycarousel:remove")
	public R remove( Long id){
		if(mycarouselService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("welcome:mycarousel:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mycarouselService.batchRemove(ids);
		return R.ok();
	}
	
}
