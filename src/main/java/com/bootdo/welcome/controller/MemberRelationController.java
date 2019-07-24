package com.bootdo.welcome.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.welcome.domain.MemberRelationDO;
import com.bootdo.welcome.service.MemberRelationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:06
 */
 
@Controller
@RequestMapping("/welcome/memberRelation")
public class MemberRelationController {
	@Autowired
	private MemberRelationService memberRelationService;

	@ModelAttribute
	public MemberRelationDO get(@RequestParam(required=false) Long id) {
		if (id != null){
			return memberRelationService.get(id);
		}else{
			return new MemberRelationDO();
		}
	}
	
	@GetMapping()
	@RequiresPermissions("welcome:memberRelation:memberRelation")
	String MemberRelation(){
	    return "welcome/memberRelation/memberRelation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("welcome:memberRelation:memberRelation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberRelationDO> memberRelationList = memberRelationService.list(query);
		int total = memberRelationService.count(query);
		PageUtils pageUtils = new PageUtils(memberRelationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("welcome:memberRelation:edit")
	String add(){
	    return "welcome/memberRelation/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("welcome:memberRelation:add")
	public R save( MemberRelationDO memberRelation){
		if(memberRelationService.save(memberRelation)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("welcome:memberRelation:remove")
	public R remove( Long id){
		if(memberRelationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("welcome:memberRelation:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		memberRelationService.batchRemove(ids);
		return R.ok();
	}
	
}
