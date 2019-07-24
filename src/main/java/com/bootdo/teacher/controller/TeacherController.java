package com.bootdo.teacher.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.teacher.domain.TeacherDO;
import com.bootdo.teacher.service.TeacherService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-07-23 17:00:11
 */
 
@Controller
@RequestMapping("/teacher/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@ModelAttribute
	public TeacherDO get(@RequestParam(required=false) Long id) {
		if (id != null){
			return teacherService.get(id);
		}else{
			return new TeacherDO();
		}
	}
	
	@GetMapping()
	@RequiresPermissions("teacher:teacher:teacher")
	String Teacher(){
	    return "teacher/teacher/teacher";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("teacher:teacher:teacher")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TeacherDO> teacherList = teacherService.list(query);
		int total = teacherService.count(query);
		PageUtils pageUtils = new PageUtils(teacherList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("teacher:teacher:edit")
	String add(){
	    return "teacher/teacher/teacheredit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("teacher:teacher:add")
	public R save( TeacherDO teacher){
		if(teacherService.save(teacher)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("teacher:teacher:remove")
	public R remove( Long id){
		if(teacherService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("teacher:teacher:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		teacherService.batchRemove(ids);
		return R.ok();
	}
	
}
