package com.bootdo.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.teacher.dao.TeacherDao;
import com.bootdo.teacher.domain.TeacherDO;
import com.bootdo.teacher.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public TeacherDO get(Long id){
		return teacherDao.get(id);
	}
	
	@Override
	public List<TeacherDO> list(Map<String, Object> map){
		return teacherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teacherDao.count(map);
	}
	
	@Override
	public int save(TeacherDO teacher){
		if (teacher.getId() != null) {
			return teacherDao.update(teacher);
		}
		return teacherDao.save(teacher);
	}
	
	@Override
	public int update(TeacherDO teacher){
		return teacherDao.update(teacher);
	}
	
	@Override
	public int remove(Long id){
		return teacherDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return teacherDao.batchRemove(ids);
	}
	
}
