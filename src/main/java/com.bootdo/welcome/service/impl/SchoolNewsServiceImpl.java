package com.bootdo.welcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.dao.SchoolNewsDao;
import com.bootdo.welcome.domain.SchoolNewsDO;
import com.bootdo.welcome.service.SchoolNewsService;



@Service
public class SchoolNewsServiceImpl implements SchoolNewsService {
	@Autowired
	private SchoolNewsDao schoolNewsDao;
	
	@Override
	public SchoolNewsDO get(Long id){
		return schoolNewsDao.get(id);
	}
	
	@Override
	public List<SchoolNewsDO> list(Map<String, Object> map){
		return schoolNewsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolNewsDao.count(map);
	}
	
	@Override
	public int save(SchoolNewsDO schoolNews){
		return schoolNewsDao.save(schoolNews);
	}
	
	@Override
	public int update(SchoolNewsDO schoolNews){
		return schoolNewsDao.update(schoolNews);
	}
	
	@Override
	public int remove(Long id){
		return schoolNewsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolNewsDao.batchRemove(ids);
	}
	
}
