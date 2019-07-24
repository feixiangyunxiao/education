package com.bootdo.welcome.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.welcome.domain.MemberStudentDO;
import com.bootdo.welcome.service.MemberStudentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:02
 */
 
@Controller
@RequestMapping("/welcome/memberStudent")
public class MemberStudentController {
	@Autowired
	private MemberStudentService memberStudentService;

	@ModelAttribute
	public MemberStudentDO get(@RequestParam(required=false) Long id) {
		if (id != null){
			return memberStudentService.get(id);
		}else{
			return new MemberStudentDO();
		}
	}
	
	@GetMapping()
	@RequiresPermissions("welcome:memberStudent:memberStudent")
	String MemberStudent(){
	    return "welcome/memberStudent/memberStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("welcome:memberStudent:memberStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberStudentDO> memberStudentList = memberStudentService.list(query);
		int total = memberStudentService.count(query);
		PageUtils pageUtils = new PageUtils(memberStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("welcome:memberStudent:edit")
	String add(){
	    return "welcome/memberStudent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("welcome:memberStudent:add")
	public R save( MemberStudentDO memberStudent){
		if(memberStudentService.save(memberStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("welcome:memberStudent:remove")
	public R remove( Long id){
		if(memberStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("welcome:memberStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		memberStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
