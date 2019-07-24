package com.bootdo.teacher.service;

import com.bootdo.teacher.domain.TeacherDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-07-23 17:00:11
 */
public interface TeacherService {
	
	TeacherDO get(Long id);
	
	List<TeacherDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeacherDO teacher);
	
	int update(TeacherDO teacher);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
