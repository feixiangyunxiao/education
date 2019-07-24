package com.bootdo.teacher.dao;

import com.bootdo.teacher.domain.TeacherDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-07-23 17:00:11
 */
@Mapper
public interface TeacherDao {

	TeacherDO get(Long id);
	
	List<TeacherDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TeacherDO teacher);
	
	int update(TeacherDO teacher);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
