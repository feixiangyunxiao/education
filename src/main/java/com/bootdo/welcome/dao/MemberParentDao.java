package com.bootdo.welcome.dao;

import com.bootdo.welcome.domain.MemberParentDO;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.domain.MemberStudentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:08
 */
@Mapper
public interface MemberParentDao {

	MemberParentDO get(Long id);
	
	List<MemberParentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MemberParentDO memberParent);
	
	int update(MemberParentDO memberParent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	MemberParentDO getByStudyNumber(Integer studyNumber);

	int updateByStudyNumber(Integer studyNumber, String openid);
}
