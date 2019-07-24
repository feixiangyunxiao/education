package com.bootdo.welcome.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.welcome.domain.MemberParentDO;
import com.bootdo.welcome.service.MemberParentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:08
 */
 
@Controller
@RequestMapping("/welcome/memberParent")
public class MemberParentController {
	@Autowired
	private MemberParentService memberParentService;

	@ModelAttribute
	public MemberParentDO get(@RequestParam(required=false) Long id) {
		if (id != null){
			return memberParentService.get(id);
		}else{
			return new MemberParentDO();
		}
	}
	
	@GetMapping()
	@RequiresPermissions("welcome:memberParent:memberParent")
	String MemberParent(){
	    return "welcome/memberParent/memberParent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("welcome:memberParent:memberParent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberParentDO> memberParentList = memberParentService.list(query);
		int total = memberParentService.count(query);
		PageUtils pageUtils = new PageUtils(memberParentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("welcome:memberParent:edit")
	String add(){
	    return "welcome/memberParent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("welcome:memberParent:add")
	public R save( MemberParentDO memberParent){
		if(memberParentService.save(memberParent)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("welcome:memberParent:remove")
	public R remove( Long id){
		if(memberParentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("welcome:memberParent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		memberParentService.batchRemove(ids);
		return R.ok();
	}
	
}
