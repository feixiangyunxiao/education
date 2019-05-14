package com.bootdo.welcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.dao.MycarouselDao;
import com.bootdo.welcome.domain.MycarouselDO;
import com.bootdo.welcome.service.MycarouselService;



@Service
public class MycarouselServiceImpl implements MycarouselService {
	@Autowired
	private MycarouselDao mycarouselDao;
	
	@Override
	public MycarouselDO get(Long id){
		return mycarouselDao.get(id);
	}
	
	@Override
	public List<MycarouselDO> list(Map<String, Object> map){
		return mycarouselDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mycarouselDao.count(map);
	}
	
	@Override
	public int save(MycarouselDO mycarousel){
		return mycarouselDao.save(mycarousel);
	}
	
	@Override
	public int update(MycarouselDO mycarousel){
		return mycarouselDao.update(mycarousel);
	}
	
	@Override
	public int remove(Long id){
		return mycarouselDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return mycarouselDao.batchRemove(ids);
	}
	
}
