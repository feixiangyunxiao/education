package com.bootdo.welcome.dao;

import com.bootdo.welcome.domain.SchoolNewsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-15 17:11:06
 */
@Mapper
public interface SchoolNewsDao {

	SchoolNewsDO get(Long id);
	
	List<SchoolNewsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolNewsDO schoolNews);
	
	int update(SchoolNewsDO schoolNews);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
