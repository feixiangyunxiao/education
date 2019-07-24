package com.bootdo.welcome.dao;

import com.bootdo.welcome.domain.MemberRelationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:06
 */
@Mapper
public interface MemberRelationDao {

	MemberRelationDO get(Long id);
	
	List<MemberRelationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MemberRelationDO memberRelation);
	
	int update(MemberRelationDO memberRelation);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
