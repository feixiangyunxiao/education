package com.bootdo.welcome.service;

import com.bootdo.welcome.domain.MycarouselDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-14 15:29:13
 */
public interface MycarouselService {
	
	MycarouselDO get(Long id);
	
	List<MycarouselDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MycarouselDO mycarousel);
	
	int update(MycarouselDO mycarousel);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
